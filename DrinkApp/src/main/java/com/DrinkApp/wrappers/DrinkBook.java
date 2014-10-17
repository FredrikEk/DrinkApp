/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.DrinkApp.wrappers;

import com.DrinkApp.Core.Drink;
import com.DrinkApp.auth.User;
import com.DrinkApp.persistence.AbstractDAO;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Fredrik
 */
@Stateless
public class DrinkBook extends AbstractDAO<Drink, Long>
        implements IDrinkBook {
    private static final Logger LOG = Logger.getLogger(DrinkBook.class.getName());

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
        TypedQuery<Drink> tq = em.createQuery("SELECT d.* FROM Drink d WHERE d.name = '" + name + "'", Drink.class);
        return tq.getResultList();
    }
	
    @Override
    public List<Drink> findByUser(String username) {
            TypedQuery<Drink> tq = em.createQuery("SELECT d.* FROM Drink d WHERE d.user = \"" + username + "\"", Drink.class);
            
            return tq.getResultList();
    }

    @Override
    public List<Drink> findByIngredient(String ingredient) {
            TypedQuery<Drink> tq = em.createQuery("SELECT di.drink FROM DrinkIngredient di WHERE di.ingredient = \"" + ingredient + "\"", Drink.class);
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
        //return tq.getSingleResult();
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
        LOG.log(Level.INFO, s, this);
        Query tq = em.createNativeQuery("SELECT d.drinkname, d.user_username, (ipd.NROFINGREDIENTS - da.drinkcount) AS Counters \n" +
                                        "FROM (SELECT di.DRINKNAME AS Drinkname, COUNT(*) AS drinkCount \n" +
                                              "FROM DrinkIngredient di \n" +
                                              "WHERE di.INGREDIENT_NAME IN (" + s + ") \n" +
                                              "GROUP BY di.DRINKNAME) da, INGREDIENTSPERDRINK ipd, Drink d \n" +
                                        "WHERE da.drinkname LIKE '%" + drinkname + "%' AND da.drinkname = ipd.DRINKNAME AND d.drinkname = da.drinkname \n" +
                                        "ORDER BY Counters ASC");
        //Query tq = em.createNamedQuery("Drink.searchByNameAndIngredient").setParameter("drinkname", "Mojito").setParameter("ingredients", "(Rum)");
        return tq.getResultList();

    }
/*
    @Override
    public List searchByNameAndIngredient(String drinkname, List<String> ingredients) {
        Query tq = em.createNamedQuery("Drink.searchByNameAndIngredient").setParameter("drinkname", drinkname).setParameter("ingredients", ingredients);
        return tq.getResultList();
    }
*/

    @Override
    public Drink findByUsernameAndDrinkname(String username, String drinkname) {
        TypedQuery<User> tq1 = em.createNamedQuery("User.findByUsername", User.class).setParameter("username", username);
        User user = tq1.getSingleResult();
        TypedQuery<Drink> tq2 = em.createNamedQuery("Drink.findByUserAndDrinkname", Drink.class).setParameter("username", user).setParameter("drinkname", drinkname);
        return tq2.getSingleResult();
    }
}
