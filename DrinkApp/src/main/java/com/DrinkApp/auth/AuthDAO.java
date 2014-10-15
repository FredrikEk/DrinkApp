package com.DrinkApp.auth;

import com.DrinkApp.persistence.AbstractDAO;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Stateless
public class AuthDAO extends AbstractDAO<User, String> {
    private static final Logger LOG = Logger.getLogger(AuthDAO.class.getName());

    @PersistenceContext
    protected EntityManager em;

    public AuthDAO() {
        super(User.class);
    }

     @PostConstruct
    public void post() {
        LOG.log(Level.INFO, "authDAO alive");
    }
    
    @Override
    public EntityManager getEntityManager() {
        return em;
    }
    
}
