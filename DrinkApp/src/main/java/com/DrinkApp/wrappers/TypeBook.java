/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.DrinkApp.wrappers;

import com.DrinkApp.Core.Type;
import com.DrinkApp.persistence.AbstractDAO;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class TypeBook extends AbstractDAO<Type, Long> 
                implements ITypeBook{

    @PersistenceContext
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public TypeBook() {
        super(Type.class);
    }
    
    public static ITypeBook newInstance() {
        return new TypeBook();
    }

    @Override
    public Type findByName(String name) {
        try {
            TypedQuery<Type> tq = em.createQuery("SELECT t FROM Type t WHERE t.name = \"" + name + "\"", Type.class);
            return tq.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }
    
}

