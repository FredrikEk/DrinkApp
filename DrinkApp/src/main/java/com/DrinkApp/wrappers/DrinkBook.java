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
/*
    @Override
    public List searchByNameAndIngredient(String drinkname, List<String> ingredients) {
        Query tq = em.createNamedQuery("Drink.searchByNameAndIngredient").setParameter("drinkname", drinkname).setParameter("ingredients", ingredients);
        return tq.getResultList();
    }
*/
}
