/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.DrinkApp.wrappers;

import com.DrinkApp.auth.User;
import com.DrinkApp.persistence.AbstractDAO;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class UserBook extends AbstractDAO<User, Long> 
                implements IUserBook{

    @PersistenceContext
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public UserBook() {
        super(User.class);
    }
    
    public static IUserBook newInstance() {
        return new UserBook();
    }
    

    @Override
    public User findByName(String username) {
        TypedQuery<User> tq = em.createQuery("SELECT u FROM User u WHERE u.username = \"" + username + "\"", User.class);
        return tq.getSingleResult();
    }

    @Override
    public User findByMail(String email) {
        TypedQuery<User> tq = em.createQuery("SELECT u FROM User u WHERE u.email = \"" + email + "\"", User.class);
        return tq.getSingleResult();
    }
    
    
}
