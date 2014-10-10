/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.DrinkApp.bb;

import com.DrinkApp.Core.Ingredient;
import com.DrinkApp.Core.Step;
import com.DrinkApp.Core.Type;
import com.DrinkApp.auth.User;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

@Named
@RequestScoped
public class DrinkBB {
	
	@NotNull
	private String drinkname;
	@NotNull
	private User user;
	@NotNull
	private String comment;
	@NotNull
	private List<Ingredient> ingredients;
	@NotNull
	private List<Type> types;
	@NotNull
	private List<Step> steps;
	
	@PostConstruct
    public void post(){
        LOG.log(Level.INFO, "The drink is alive ");
    }
    private static final Logger LOG = Logger.getLogger(RegisterBB.class.getName());
    
    /*
    public void setUsername(String username){
        this.username = username;
    }
    */
    public void setDrinkname(String drinkname){
        this.drinkname = drinkname;
    }
    
    public void setComment(String comment){
        this.comment = comment;
    }
    
    public void setIngredients(List<Ingredient> ingredients){
        this.ingredients = ingredients;
    }
	
	public void setSteps(List<Step> steps){
		this.steps = steps;
	}
	
	public void setTypes(List<Type> types) {
		this.types = types;
	}
	
    /*
    public String getUsername(){
        return username;
    }
    */
    public String getDrinkname(){
        return this.drinkname;
    }
    
    public String getComment(){
        return this.comment;
    }
    
    public List<Ingredient> getIngredients(){
        return this.ingredients;
    }
	
	
    public List<Step> getSteps(){
		return this.steps;
	}
	
	public List<Type> getTypes() {
		return this.types;
	}
}
