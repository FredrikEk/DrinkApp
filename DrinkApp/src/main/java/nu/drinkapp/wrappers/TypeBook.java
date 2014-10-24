package nu.drinkapp.wrappers;

import nu.drinkapp.core.Type;
import nu.drinkapp.persistence.AbstractDAO;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * Book including the type of drink
 */

@Stateless
public class TypeBook extends AbstractDAO<Type, Long>
        implements ITypeBook {

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

    @Override
    public void deleteDrinkTypes(String username, String drinkname) {
        em.createNativeQuery("DELETE FROM Drink_type WHERE user_username = '" + username + "'" + " AND drinkname = '" + drinkname + "'").executeUpdate();
    }
}
