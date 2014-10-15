/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.DrinkApp.Core;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/*
 * The Type model. Stores different types that drinks can have
 * e.g sour, sweet, strong etc
 */

@Entity
public class Type implements Serializable {

    @Column
    @Id
    private String name;
    
    public Type(){
        this.name = "";
     
    }
    
    public Type(String name) {
        this.name = name;
        }
    
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Type{ name=" + name  + '}';
    }
}
