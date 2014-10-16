/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.DrinkApp.bb;

import com.DrinkApp.Core.DrinkIngredient;
import com.DrinkApp.Core.Ingredient;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

@Named
@ViewScoped
public class IngredientBB implements Serializable{
	
    @NotNull
    private String name;
    @NotNull
    private String quantity;

    @PostConstruct
    public void post(){
        LOG.log(Level.INFO, "The ingredient is alive ");
    }
    private static final Logger LOG = Logger.getLogger(RegisterBB.class.getName());
    
    public IngredientBB() {
        
    }
    
    public IngredientBB(Ingredient i, String quantity) {
        this.name = i.getName();
        this.quantity = quantity;
    }
    
    public IngredientBB(DrinkIngredient di) {
        this.name = di.getIngredient().getName();
        this.quantity = di.getQuantity();
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public void setQuantity(String quantity){
        this.quantity = quantity;
    }
    
    public String getName(){
        return this.name;
    }
    
    public String getQuantity(){
        return this.quantity;
    }
    
}
