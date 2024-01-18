/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.datafusion.infra.user;

import com.mongodb.client.MongoCollection;

/**
 *
 * @author Matheus
 */
public class TesteMongo {
    
    public static void main(String[] args) {
        
        try {
             MongoCollection<UserMongo> _users = MongoConnection.getDatabase().getCollection("user", UserMongo.class);
            
            
            //MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
            
            //MongoDatabase database = mongoClient.getDatabase("teste_java");
            //MongoCollection usuariosCollection = database.getCollection("user");

            
            UserMongo novoUsuario = new UserMongo();
            novoUsuario.setNome("Matheus");
            novoUsuario.setIdade(21);
            novoUsuario.setEmail("matheus@example.com");
            novoUsuario.setSenha("senhaforte2913810");

            //Document sampleDoc = new Document("_id", 1).append("username", "Matheus").append("userage", 22);
            
            _users.insertOne(novoUsuario);

            System.out.println("Usuário inserido com sucesso!");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
}
