/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.DrinkApp.auth;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/*
 * The User model. Stores users in the database with an email, 
 * username and password.
 */

@Entity
@Table(name = "Users")
@NamedQueries( {@NamedQuery(name = "User.findByUsername", query = "SELECT u FROM User u WHERE u.username = :username"), @NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email = :email"), @NamedQuery(name = "User.findByPassword", query = "SELECT u FROM User u WHERE u.password = :password")})
public class User implements Serializable {
    
   
    @Column(nullable = false)
    @Id
    private String username;
    @Column(nullable = false)
    protected String password;
    @Column(nullable = false)
    private String email;
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "USERS_GROUPS", 
            joinColumns = @JoinColumn(name = "username"))
    @Enumerated(EnumType.STRING)
    protected List<Groups> groups = new ArrayList<>();

    public User() {
        this.username = "";
        this.email = "";
        this.password = "";
    }
    
    public User(String username, String email, String password, Groups group) {
        this.username = username;
        this.email    = email;
        this.password = password;
        groups.add(group);
        }
    
    /*
    * Setters if we want to modify, like for example change password etc. in the future?
    */

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }
    
    public String getPassword() {
        return password;
    }
    
    public List<Groups> getGroups(){
        return groups;
    }
    
    public void removeGroup(Groups group) {
        groups.remove(group);
    }

    @Override
    public String toString() {
        return "User{username=" + username + ", email=" + email + '}';
    }
    
}
