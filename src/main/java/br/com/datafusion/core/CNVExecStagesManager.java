/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.datafusion.core;

import br.com.datafusion.core.entity.INFEntityDefinition;
import br.com.datafusion.database.query.INFQuery;
import br.com.generator.view.prototipoMigracao.infra.database.exceptions.INFQueryException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author matheus.buschermoehl
 * 
 *  Classe abstrata que gerencia todas as etapas de migração registradas.
 */

public abstract class CNVExecStagesManager extends CNVExecStage {

    protected INFQuery queryOrigin;
    protected INFQuery queryDestination;

    protected Integer userCode;
    protected String sourceMainSQL;
    protected Integer recordsAmount = 0;
    protected Integer recordsAmountRefreshFrame = 1000;
    protected Integer currentRecord = 0;

    protected abstract String getStepConversionMainSQL();

    protected abstract Boolean beforeGetStepConversionSQL();

    protected abstract Boolean beforeProcessRecordStepConversion();
    protected abstract Boolean processRecordStepConversion();
    protected abstract Boolean afterProcessRecordStepConversion();
    protected abstract Boolean beforeProcessNextRecordStepConversion();
    
    protected abstract Boolean beforeProcessStepConversion();
    
    protected abstract Boolean beforeProcessSQLStepConversion();
    protected abstract Boolean processSQLStepConversion();
    protected abstract Boolean afterProcessSQLStepConversion();
    
    
    
    protected abstract Boolean beforeExecuteConversion();

    protected abstract INFEntityDefinition createInstanceDefine();

    protected INFQuery getQueryOrigin() {
        return null;
    }

    @Override
    protected final Boolean executeConversion() {

        if (stepIsReady()) {
            if (define == null) {
                define = createInstanceDefine();
            }

            validatePendingIssues();

            boolean executed = super.executeConversion();
            if(!executed) {
                // estoura exceção.
            }
            
            return finishConversion();
            
        } else {
            // estoura exceção etapa nao está pronta para execução.
            return false;
        }
    }

    @Override
    protected Boolean processStepConversion() {

        boolean executed = true;
        
        if (queryOrigin == null) {
            try {

                // Instanciar nova query
                queryOrigin = newQueryOrigin();

                // Executa tratamentos antes da execução da migração
                beforeExecuteConversion();

                // Executa tratamentos antes da execução do SQL da etapa
                beforeGetStepConversionSQL();

                queryOrigin.close();

                // Apresenta mensagem ('Buscando informações da tabela de origem para conversão.')
                sourceMainSQL = getStepConversionMainSQL();

                // Preparar a query com o SQL da etapa
                queryOrigin.setStatement(sourceMainSQL);
                logSQL(sourceMainSQL);

                // Apresenta mensagem ('Setando quantidade de registros encontrados na tabela de origem.')
                getRecordsAmount();
                
                queryOrigin.open();
                log("Record amount: " + recordsAmount);
                
                // Executa manipulações antes de entrar no loop de conversão dos registros.
                beforeProcessStepConversion();
                
                // Envia notificação da quantidade de registros para a tela.
                
                // Intervalo de atualização da barra de progresso.
                setRecordsAmountRefreshFrame(getRecordsAmountRefreshFrame());
                
                
            } catch (INFQueryException ex) {
                Logger.getLogger(CNVExecStagesManager.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(CNVExecStagesManager.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        
        // Exibir a mensagem de informações de conversão da etapa

        beforeProcessSQLStepConversion();
               

        return executed;
    }
    
    @Override
    protected Boolean finishConversion() {
        return super.finishConversion();
    }
    
    protected Boolean scrollQueryRecords() {
        boolean executed = queryOrigin.eof();
        
        while(!queryOrigin.eof()) {
            executed = beforeProcessRecordStepConversion();
            executed = processRecordStepConversion();
            executed = afterProcessRecordStepConversion();
            
            if(executed) {
                // incrementa registro e propaga para a tela.
                incrementCurrentRecord();
                beforeProcessNextRecordStepConversion();
            } else {
                break;
            }
        }
        
        return executed;
    }
    
    protected Integer getRecordsAmount() throws INFQueryException {
        if(recordsAmount != 0) {
            recordsAmount = queryOrigin.getRecordCount();
        }
        return recordsAmount;
    }

    protected String getEntityName() {
        return define.getEntity().getEntityName();
    }

    public Integer getRecordsAmountRefreshFrame() {
        return recordsAmountRefreshFrame;
    }

    public void setRecordsAmountRefreshFrame(Integer recordsAmountRefreshFrame) {
        this.recordsAmountRefreshFrame = recordsAmountRefreshFrame;
    }
    
    private void incrementCurrentRecord() {
        this.currentRecord += 1;
        // notificar observadores
    }

}

