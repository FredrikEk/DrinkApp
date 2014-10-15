/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.DrinkApp.wrappers;

import com.DrinkApp.Core.Ingredient;
import com.DrinkApp.persistence.AbstractDAO;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Fredrik
 */
@Stateless
public class IngredientBook extends AbstractDAO<Ingredient, Long> 
                implements IIngredientBook{

    @PersistenceContext
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public IngredientBook() {
        super(Ingredient.class);
    }
    
    public static IIngredientBook newInstance() {
        return new IngredientBook();
    }

    @Override
    public Ingredient findByName(String name) {
        try {
            TypedQuery<Ingredient> tq = em.createQuery("SELECT i FROM Ingredient i WHERE i.name = '" + name + "'", Ingredient.class);
            return tq.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }
    
}
