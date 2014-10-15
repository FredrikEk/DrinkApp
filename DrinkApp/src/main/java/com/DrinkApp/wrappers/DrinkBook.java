/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.DrinkApp.wrappers;

import com.DrinkApp.Core.Drink;
import com.DrinkApp.persistence.AbstractDAO;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Fredrik
 */
@Stateless
public class DrinkBook extends AbstractDAO<Drink, Long>
        implements IDrinkBook {

    @PersistenceContext
    private EntityManager em;
    
    public DrinkBook() {
        super(Drink.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }
    
    // Factory method
    public static IDrinkBook newInstance() {
        return new DrinkBook();
    }
    
    @Override
    public Drink getById(Long id) {
        return find(id);
    }
    
    @Override
    public List<Drink> getByName(String name) {
        TypedQuery<Drink> tq = em.createQuery("SELECT d FROM Drink d WHERE d.name = \"" + name + "\"", Drink.class);
        return tq.getResultList();
    }
	
    @Override
    public List<Drink> getByUser(String username) {
            TypedQuery<Drink> tq = em.createQuery("SELECT d FROM Drink d WHERE d.user = \"" + username + "\"", Drink.class);
            return tq.getResultList();
    }

    @Override
    public List<Drink> getByIngredient(String ingredient) {
            TypedQuery<Drink> tq = em.createQuery("SELECT d FROM DrinkIngredient di WHERE di.ingredient = \"" + ingredient + "\"", Drink.class);
            return tq.getResultList();
    }
}
