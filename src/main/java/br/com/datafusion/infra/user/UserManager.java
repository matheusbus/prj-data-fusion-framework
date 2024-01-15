/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.datafusion.infra.user;

/**
 *
 * @author Matheus
 */
public class UserManager {
    
    private static UserManager instance;
    private final User LOGGED_USER;
    
    public static UserManager getInstance(User loggedUser) {
        if(instance == null) {
            instance = new UserManager(loggedUser);
        }
        return instance;
    }
    
    private UserManager(User loggedUser) {
        LOGGED_USER = loggedUser;
    }

    public User getLOGGED_USER() {
        return LOGGED_USER;
    }
}
