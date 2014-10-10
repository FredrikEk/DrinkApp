/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.DrinkApp.auth;

import com.DrinkApp.persistence.AbstractEntity;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;


/*
 * The User model. Stores users in the database with an email, 
 * username and password.
 */

@Entity
public class User extends AbstractEntity {
    
   
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    protected String password;
    @Column(nullable = false)
    private String email;

    public User() {
        username = "";
        email = "";
        password = "";
    }
    
    public User(String username, String email, String password) {
        this.username = username;
        this.email    = email;
        this.password = password;
        }
    
    public User(Long id, String username, String email, String password) {
        super(id);
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
        return "User{" + "id=" + getId() + ", username=" + username + ", email=" + email + '}';
    }

    Object getPasswd() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
