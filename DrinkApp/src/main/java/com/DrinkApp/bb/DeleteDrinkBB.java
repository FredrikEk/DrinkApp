/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.DrinkApp.bb;

import java.io.Serializable;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Michael
 */
@Named
@ViewScoped
public class DeleteDrinkBB implements Serializable {
    
    @NotNull
    private String drinkName;
    
    public DeleteDrinkBB(){
        
    }
    
    public void setDrinkName(String drinkName){
        this.drinkName = drinkName;
    }
    
    public String getDrinkName(){
        return drinkName;
    }
    
}
