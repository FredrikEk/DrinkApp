/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nu.drinkapp.wrappers;

import nu.drinkapp.core.Drink;
import nu.drinkapp.core.Step;
import nu.drinkapp.persistence.AbstractDAO;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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