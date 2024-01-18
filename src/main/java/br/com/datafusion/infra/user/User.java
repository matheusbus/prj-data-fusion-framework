/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.datafusion.infra.user;

import java.lang.annotation.Documented;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Matheus
 */
@Entity
@Table(schema = "migration", name = "tbuser")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userid")
    private Integer id;
    
    @Column(name = "username", length = 100)
    private String username;
    
    @Column(name = "userpassword", length = 100)
    private String password;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "usercreateddate", nullable = false)
    private LocalDateTime createdDate;
    
    @Column(name = "userstatus")
    private Short status;

    public User() {
    }
    
    public User(Integer id, String username, String password, LocalDateTime createdDate, UserStatus status) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.createdDate = createdDate;
        this.status = status.getCode();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public UserStatus getStatus() {
        return UserStatus.valueOf(status);
    }

    public void setStatus(UserStatus status) {
        this.status = status.getCode();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        return Objects.equals(this.id, other.id);
    }
    
    
    
}
