/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.DrinkApp.Core;

import com.DrinkApp.persistence.AbstractEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 *
 * @author Fredrik
 */
@Entity
public class DrinkIngredient extends AbstractEntity{
    
    @ManyToOne
    private Drink drink;
    @ManyToOne
    private Ingredient ingredient;
    @Column
    private int quantity;
    
    public DrinkIngredient(){
        this.drink      = new Drink();
        this.ingredient = new Ingredient();
        this.quantity   = 0;
    }
    
    public DrinkIngredient(Drink drink, Ingredient ingredient, int quantity) {
        this.drink      = drink;
        this.ingredient = ingredient;
        this.quantity   = quantity;
        }
    
    public DrinkIngredient(Long id, Drink drink, Ingredient ingredient, int quantity) {
        super(id);
        this.drink      = drink;
        this.ingredient = ingredient;
        this.quantity   = quantity;
    }
    
    public Drink getDrink() {
        return drink;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }
    
    public int getQuantity() {
        return quantity;
    }
    
    @Override
    public String toString() {
        return "DrinkIngredient{" + "id=" + getId() + ", Ingredient=" + ingredient.getName() + ", Drink=" + drink.getName() + '}';
    }
}
