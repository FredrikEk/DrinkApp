/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.DrinkApp.Core;

import com.DrinkApp.persistence.AbstractEntity;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/*
 * The Drink model. Stores drinks in the database with a user
 * who submitted the drink, a name, a list of ingredients and a 
 * list of steps of how to make the drink. 
 */

@Entity
public class Drink extends AbstractEntity {
    
    @ManyToOne
    private User user;
    @Column
    private String name;
    @ManyToMany
    private List<Ingredient> ingredients;
    @OneToMany
    private List<Step> steps;
    @ManyToMany
    private List<Type> types;

    public Drink() {
        this.user        = new User();
        this.name        = "";
        this.ingredients = new ArrayList();
        this.steps       = new ArrayList();
        this.types       = new ArrayList();
    }
    
    public Drink(String name, User user, List<Ingredient> ingredients, List<Step> steps, List<Type> types) {
        this.name        = name;
        this.user        = user;
        this.ingredients = ingredients;
        this.steps       = steps;
        this.types       = types;
        }
    
    public Drink(Long id, String username, String name, User user, List<Ingredient> ingredients, List<Step> steps, List<Type> types) {
        super(id);
        this.name        = name;
        this.user        = user;
        this.ingredients = ingredients;
        this.steps       = steps;
        this.types       = types;
    }

    public User getUser() {
        return user;
    }

    public String getName() {
        return name;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }
    
    public List<Step> getSteps() {
        return steps;
    }
    
    public List<Type> getTypes() {
        return types;
    }
    
    @Override
    public String toString() {
        return "Drink{" + "id=" + getId() + ", name=" + name + '}';
    }
}
