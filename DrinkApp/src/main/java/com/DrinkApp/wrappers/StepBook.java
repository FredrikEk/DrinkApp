/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.DrinkApp.wrappers;

import com.DrinkApp.Core.Drink;
import com.DrinkApp.Core.Step;
import com.DrinkApp.persistence.AbstractDAO;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Fredrik
 */
@Stateless
public class StepBook extends AbstractDAO<Step, Long> 
                implements IStepBook{

    @PersistenceContext
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public StepBook() {
        super(Step.class);
    }
    
    public static IStepBook newInstance() {
        return new StepBook();
    }

    @Override
    public void deleteAllByDrink(Drink drink) {
        em.createNamedQuery("Step.deletAllByDrink").setParameter("drink", drink).executeUpdate();
    }
    
}