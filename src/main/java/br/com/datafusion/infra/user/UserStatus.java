/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.datafusion.infra.user;

/**
 *
 * @author Matheus
 */
public enum UserStatus {

    ACTIVE((short) 1),
    INACTIVE((short)2);
    
    private Short code;

    private UserStatus(Short code) {
        this.code = code;
    }

    public Short getCode() {
        return code;
    }
    
    public static UserStatus valueOf(Short code) {
        for(UserStatus us : values()) {
            if(us.getCode() == code) {
                return us;
            }
        }
        throw new IllegalArgumentException("Code: " + code + " is not a valid code for UserStatus.");
    }
    
}
