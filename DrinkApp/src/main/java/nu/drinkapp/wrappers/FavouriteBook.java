/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nu.drinkapp.wrappers;

import nu.drinkapp.core.Drink;
import nu.drinkapp.core.Favourite;
import nu.drinkapp.auth.User;
import nu.drinkapp.persistence.AbstractDAO;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class FavouriteBook extends AbstractDAO<Favourite, Long>
        implements IFavouriteBook {

    @PersistenceContext
    private EntityManager em;

    public FavouriteBook() {
        super(Favourite.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public static IFavouriteBook newInstance() {
        return new FavouriteBook();
    }

    @Override
    public List<Favourite> findByUser(String username) {
        try {
            TypedQuery<User> tq1 = em.createNamedQuery("User.findByUsername", User.class).setParameter("username", username);
            User user = tq1.getSingleResult();
            TypedQuery<Favourite> tq = em.createNamedQuery("Favourite.userFavourite", Favourite.class).setParameter("user", user);
            return tq.getResultList();
        } catch (NoResultException nre) {
            return null;
        }
    }

    @Override
    public Favourite findByDrinkAndUser(Drink drink, User user) {
        try {
            TypedQuery<Favourite> tq = em.createNamedQuery("Favourite.userAndDrink", Favourite.class).setParameter("drink", drink).setParameter("user", user);
            return tq.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    @Override
    public void deleteFavourites(Drink drink) {
        em.createNamedQuery("Favourite.deleteDrinkFavourites").setParameter("drink", drink).executeUpdate();
    }

    @Override
    public void deleteByUserAndDrink(User user, Drink drink) {
        em.createNamedQuery("Favourite.deleteByUserAndDrink").setParameter("drink", drink).setParameter("user", user).executeUpdate();
    }

}
