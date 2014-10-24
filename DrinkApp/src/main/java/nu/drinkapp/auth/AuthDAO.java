package nu.drinkapp.auth;

import nu.drinkapp.persistence.AbstractDAO;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Stateless
public class AuthDAO extends AbstractDAO<User, String> {

    @PersistenceContext
    protected EntityManager em;

    public AuthDAO() {
        super(User.class);
    }

     @PostConstruct
    public void post() {
    }
    
    @Override
    public EntityManager getEntityManager() {
        return em;
    }
    
}
