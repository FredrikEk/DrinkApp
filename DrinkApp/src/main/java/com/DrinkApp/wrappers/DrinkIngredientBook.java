/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.DrinkApp.wrappers;

import com.DrinkApp.Core.DrinkIngredient;
import com.DrinkApp.persistence.AbstractDAO;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Fredrik
 */
@Stateless
public class DrinkIngredientBook extends AbstractDAO<DrinkIngredient, Long> 
                implements IDrinkIngredientBook{

    @PersistenceContext
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public DrinkIngredientBook() {
        super(DrinkIngredient.class);
    }
    
    public static IDrinkIngredientBook newInstance() {
        return new DrinkIngredientBook();
    }
    
}

