package nu.drinkapp.wrappers;

import nu.drinkapp.core.Drink;
import nu.drinkapp.core.Step;
import nu.drinkapp.persistence.AbstractDAO;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Book including the steps to make a drink
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