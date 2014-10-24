package nu.drinkapp.wrappers;

import nu.drinkapp.auth.User;
import nu.drinkapp.persistence.AbstractDAO;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * Book including the users
 */

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
