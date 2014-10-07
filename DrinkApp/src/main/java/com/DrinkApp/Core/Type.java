/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.DrinkApp.Core;

import com.DrinkApp.persistence.AbstractEntity;
import javax.persistence.Column;
import javax.persistence.Entity;

/*
 * The Type model. Stores different types that drinks can have
 * e.g sour, sweet, strong etc
 */

@Entity
public class Type extends AbstractEntity {

    @Column
    private String name;
    
    public Type(){
        this.name = "";
     
    }
    
    public Type(String name) {
        this.name = name;
        }
    
    public Type(Long id, String name) {
        super(id);
        this.name = name;
    }
    
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Type{" + "id=" + getId() + ", name=" + name  + '}';
    }
}
