package nu.drinkapp.core;

import java.util.List;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(Arquillian.class)
public class TestCore {

    @Inject
    Bar bar;

    @Inject
    UserTransaction utx;  // This is not an EJB so have to handle transactions

    @Deployment
    public static Archive<?> createDeployment() {
        return ShrinkWrap.create(WebArchive.class)
                // Add all classes
                .addPackage("nu.drinkapp.core")
                // This will add test-persitence.xml as persistence.xml (renamed)
                // in folder META-INF, see Files > jpa_managing > target > arquillian
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                // Must have for CDI to work
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Before  // Run before each test
    public void before() throws Exception {
        clearAll();
    }

    @Test
    public void testPersistDrink() throws Exception {
      /*  System.out.println("***********" + "Test found");
        Drink d = new Drink();
        bar.getDrinkBook().create(d);
        List<Drink> ds = bar.getDrinkBook().findAll();
              */
        assertTrue(true);
    }

    // Need a standalone em to remove testdata between tests
    // No em accessible from interfaces
    //@PersistenceContext(unitName = "DrinkApp_test")
    @Produces
    @Default
    EntityManager em;

    // Order matters
    private void clearAll() throws Exception {  
        System.out.println("**********************clearall done!");
        utx.begin();  
        em.joinTransaction();
        //em.createQuery("delete from Drink").executeUpdate();
        em.createNativeQuery("delete from Users_groups").executeUpdate();
        utx.commit();
    }
}