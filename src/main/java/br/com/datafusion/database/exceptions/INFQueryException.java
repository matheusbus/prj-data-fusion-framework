/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.generator.view.prototipoMigracao.infra.database.exceptions;

import java.sql.SQLException;

/**
 *
 * @author matheus.buschermoehl
 */
public class INFQueryException extends SQLException {

    public INFQueryException(String reason) {
        super(reason);
    }

}
