/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.DrinkApp.wrappers;

import com.DrinkApp.Core.Drink;
import com.DrinkApp.Core.Rating;
import com.DrinkApp.auth.User;
import com.DrinkApp.persistence.AbstractDAO;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

@Stateless
public class RatingBook extends AbstractDAO<Rating, Long> 
                implements IRatingBook{

    @PersistenceContext
    private EntityManager em;
   
    public RatingBook() {
        super(Rating.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public static IRatingBook newInstance() {
        return new RatingBook();
    }

  
    @Override
    public List<Rating> findByDrink(Drink drink) {
        try{
            TypedQuery<Rating> tq = em.createNamedQuery("Rating.averageRating", Rating.class).setParameter("drink", drink);
            return tq.getResultList();
        } catch (NoResultException nre) {
            return null;
        }
    }

    @Override
    public List<Rating> findByUser(User user) {
        try{
            TypedQuery<Rating> tq = em.createNamedQuery("Rating.userRating", Rating.class).setParameter("user", user);
            return tq.getResultList();
        } catch (NoResultException nre) {
            return null;
        }
    }

    @Override
    public Rating findByDrinkAndUser(Drink drink, User user) {
        try {
            TypedQuery<Rating> tq = em.createNamedQuery("Rating.userAndDrink", Rating.class).setParameter("drink", drink).setParameter("user", user);
            return tq.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    @Override
    public Double getAverageRating(Drink drink) {
        try {
            Query q = em.createNamedQuery("Rating.averageRating").setParameter("drink", drink);
            List<Object[]> objectList = q.getResultList();
            Object[] obj = objectList.get(0);
            Long noOfStars = (Long) obj[0];
            Long noOfRaters = (Long) obj[1];
            return (1.0 * noOfStars) / (1.0 * noOfRaters);
        } catch (NoResultException nre) {
            return null;
        }
    }
}
    
