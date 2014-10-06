/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.DrinkApp.Core;

import com.DrinkApp.persistence.AbstractEntity;
import javax.persistence.Column;
import javax.persistence.Embedded;

public class User extends AbstractEntity {
    
   
    @Column
    private String username;
    @Column
    private String email;

    public User() {
        username = "";
        email = "";
    }
    
    public User(String username, String email) {
        this.username = username;
        this.email    = email;
        }
    
    public User(Long id, String username, String email) {
        super(id);
        this.username = username;
        this.email    = email;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + getId() + ", username=" + username + ", email=" + email + '}';
    }
}
