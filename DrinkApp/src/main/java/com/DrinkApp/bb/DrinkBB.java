/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.DrinkApp.bb;

import com.DrinkApp.Core.Drink;
import com.DrinkApp.Core.DrinkIngredient;
import com.DrinkApp.Core.Step;
import com.DrinkApp.Core.Type;
import com.DrinkApp.auth.User;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

@Named
@ViewScoped
public class DrinkBB implements Serializable{
	
    @NotNull
    private String drinkname;
    @NotNull
    private String username;
    @NotNull
    private String comment;
    @NotNull
    private List<IngredientBB> ingredients;
    @NotNull
    private List<TypeBB> types;
    @NotNull
    private List<StepBB> steps;

    @PostConstruct
    public void post(){
        LOG.log(Level.INFO, "The drink is alive ");
        if(ingredients == null) {
            ingredients = new ArrayList();
        }
        if(types == null) {
            types = new ArrayList();
        }
        if(steps == null) {
            steps = new ArrayList();
        }
    }
    
    public DrinkBB() {
        
    }
    
    public DrinkBB(Drink drink) {
        this.drinkname = drink.getName();
        this.comment = drink.getComment();
        List<TypeBB> ltbb = new ArrayList();
        List<StepBB> lsbb = new ArrayList();
        List<IngredientBB> libb = new ArrayList();
        for(Type t : drink.getTypes()) {
            ltbb.add(new TypeBB(t));
        }
        for(Step s : drink.getSteps()) {
            lsbb.add(new StepBB(s));
        }
        for(DrinkIngredient di : drink.getIngredients()) {
            libb.add(new IngredientBB(di));
        }
        this.ingredients = libb;
        this.steps = lsbb;
        this.types = ltbb;
        this.username = drink.getUser().getUsername();
    }
    
    private static final Logger LOG = Logger.getLogger(RegisterBB.class.getName());
    
    public void setDrinkname(String drinkname){
        this.drinkname = drinkname;
    }
    
    public void setComment(String comment){
        this.comment = comment;
    }
    
    public void setIngredients(List<IngredientBB> ingredients){
        this.ingredients = ingredients;
    }
	
    public void setSteps(List<StepBB> steps){
            this.steps = steps;
    }

    public void setTypes(List<TypeBB> types) {
            this.types = types;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }

    public String getDrinkname(){
        return this.drinkname;
    }
    
    public String getComment(){
        return this.comment;
    }
    
    public String getUsername() {
        return this.username;
    }
    
    public List<IngredientBB> getIngredients(){
        return this.ingredients;
    }
	
	
    public List<StepBB> getSteps(){
            return this.steps;
    }

    public List<TypeBB> getTypes() {
            return this.types;
    }
}
