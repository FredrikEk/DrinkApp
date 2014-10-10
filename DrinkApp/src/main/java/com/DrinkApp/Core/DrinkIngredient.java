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
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;


@Entity @IdClass(DrinkIngredientId.class)
public class DrinkIngredient implements Serializable{
    
    @ManyToOne
    @Id
    private Drink drink;
    @ManyToOne
    @Id
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
        return "DrinkIngredient{ Ingredient=" + ingredient.getName() + ", Drink=" + drink.getName() + '}';
    }
}
