package nu.drinkapp.wrappers;

import nu.drinkapp.core.Drink;
import nu.drinkapp.auth.User;
import nu.drinkapp.persistence.AbstractDAO;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 * A book including drinks
 * Methods to find drink and user
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
    public Drink findById(Long id) {
        return find(id);
    }
    
    @Override
    public List<Drink> findByName(String name) {
        TypedQuery<Drink> tq = em.createNamedQuery("Drink.findByName", Drink.class).setParameter("drinkname", name);
        return tq.getResultList();
    }
	
    @Override
    public List<Drink> findByUser(String username) {
            TypedQuery<User> tq1 = em.createNamedQuery("User.findByUsername", User.class).setParameter("username", username);
            User user = tq1.getSingleResult();
            TypedQuery<Drink> tq = em.createNamedQuery("Drink.searchByUser", Drink.class).setParameter("user", user);
            return tq.getResultList();
    }

    @Override
    public Drink findByUserAndDrinkname(User user, String drinkname) {
        try {
            return em.createNamedQuery("Drink.findByUserAndDrinkname", Drink.class).
                    setParameter("username", user).setParameter("drinkname", drinkname).getSingleResult();
           
        } catch (NoResultException nre) {
            return null;
        }
    }

    @Override
    public List<Drink> searchByName(String drinkname) {
        TypedQuery<Drink> tq = em.createNamedQuery("Drink.searchByName", Drink.class).setMaxResults(10).setParameter("drinkname", "%" + drinkname + "%");
        return tq.getResultList();
    }
    
    @Override
    public List<Object[]> searchByNameAndIngredient(String drinkname, List<String> ingredients){
        String s = "";
        for(int i = 0; i < ingredients.size(); i++) {
            s += "'" + ingredients.get(i) + "'";
            if(i != ingredients.size() - 1) {
                s += ",";
            }
        }
        //The Drinksearch query with subqueries.
        //The main query that finds out if a drink contains all the submitted ingredients
        //and/or how many ingredients that are missing
        Query tq = em.createNativeQuery("SELECT d.drinkname, d.user_username, (ipd.NROFINGREDIENTS - da.drinkcount) AS Counters \n" +
                                               //Subquery to find which of the submitted ingredients a drink contains.
                                        "FROM (SELECT di.DRINKNAME AS Drinkname, COUNT(*) AS drinkCount \n" +
                                              "FROM DrinkIngredient di \n" +
                                              "WHERE di.INGREDIENT_NAME IN (" + s + ") \n" +
                                              "GROUP BY di.DRINKNAME) da, \n" + 
                                               //Subquery to find total number of ingredients per drink
                                              "(SELECT di.DRINKNAME AS Drinkname, COUNT(*) AS nrOfIngredients \n" +
                                              "FROM DrinkIngredient di \n" +
                                              "GROUP BY di.DRINKNAME) ipd, Drink d \n" +
                                        "WHERE da.drinkname LIKE '%[" + drinkname + "]%' AND da.drinkname = ipd.DRINKNAME AND d.drinkname = da.drinkname \n" +
                                        "ORDER BY Counters ASC");
        return tq.getResultList();

    }

    @Override
    public Drink findByUsernameAndDrinkname(String username, String drinkname) {
        TypedQuery<User> tq1 = em.createNamedQuery("User.findByUsername", User.class).setParameter("username", username);
        User user = tq1.getSingleResult();
        TypedQuery<Drink> tq2 = em.createNamedQuery("Drink.findByUserAndDrinkname", Drink.class).setParameter("username", user).setParameter("drinkname", drinkname);
        return tq2.getSingleResult();
    }
    
    @Override
    public void deleteDrink(Drink drink){
        em.createNamedQuery("Drink.deleteDrink").setParameter("drinkname", drink.getName()).setParameter("user", drink.getUser()).executeUpdate();
    } 
}
