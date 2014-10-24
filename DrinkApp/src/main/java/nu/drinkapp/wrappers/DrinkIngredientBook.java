package nu.drinkapp.wrappers;

import nu.drinkapp.core.Drink;
import nu.drinkapp.core.DrinkIngredient;
import nu.drinkapp.core.Ingredient;
import nu.drinkapp.persistence.AbstractDAO;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
/**
 * Book including the drink ingredients
 * Methods do find a drink by ingredients
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

    @Override
    public DrinkIngredient findByDrinkAndIngredient(Ingredient i, Drink d) {
        try {
            return (DrinkIngredient) em.createNamedQuery("DrinkIngredient.findByIngredientAndDrink").
                    setParameter("ingredient", i).setParameter("drink", d).getSingleResult();
           
        } catch (NoResultException nre) {
            return null;
        }
    }

    @Override
    public void deleteDrinkIngredientByDrink(Drink drink) {
        em.createNamedQuery("DrinkIngredient.deleteAllDrinkIngredient").setParameter("drink", drink).executeUpdate();
    } 
}

