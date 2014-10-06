/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.DrinkApp.Core;

import com.DrinkApp.persistence.AbstractEntity;
import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Ingredient extends AbstractEntity{
    
    @Column
    private String name;
    
    public Ingredient(){
        this.name = "";
     
    }
    
    public Ingredient(String name) {
        this.name = name;
        }
    
    public Ingredient(Long id, String name) {
        super(id);
        this.name = name;
    }
    
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Ingredient{" + "id=" + getId() + ", name=" + name  + '}';
    }
}
