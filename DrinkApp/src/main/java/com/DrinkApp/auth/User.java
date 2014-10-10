/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.DrinkApp.auth;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/*
 * The User model. Stores users in the database with an email, 
 * username and password.
 */

@Entity
@Table(name = "Users")
public class User implements Serializable {
    
   
    @Column(nullable = false)
    @Id
    private String username;
    @Column(nullable = false)
    protected String password;
    @Column(nullable = false)
    private String email;

    public User() {
        this.username = "";
        this.email = "";
        this.password = "";
    }
    
    public User(String username, String email, String password) {
        this.username = username;
        this.email    = email;
        this.password = password;
        }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }
    
    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "User{username=" + username + ", email=" + email + '}';
    }

    Object getPasswd() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
