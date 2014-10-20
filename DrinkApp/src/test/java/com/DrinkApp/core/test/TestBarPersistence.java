package com.DrinkApp.core.test;

import com.DrinkApp.Core.Bar;
import com.DrinkApp.Core.Drink;
import java.util.List;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
public class TestBarPersistence {

    @Inject
    Bar bar;

    @Inject
    UserTransaction utx;  // This is not an EJB so have to handle transactions

    @Deployment
    public static Archive<?> createDeployment() {
        return ShrinkWrap.create(WebArchive.class)
                // Add all classes
                .addPackage("com.DrinkApp.core")
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
    public void testPersistADrink() throws Exception {
        Drink d = new Drink();
        bar.getDrinkBook().create(d);
        List<Drink> ds = bar.getDrinkBook().findAll();
        assertTrue(ds.size() > 0);
    }
    
//    @Test
//    public void testPersistDelete() throws Exception {
//        Product p1 = new Product("aaa", 999);
//        Product p2 = new Product("bbb", 888);
//        shop.getProductCatalogue().create(p1);
//        shop.getProductCatalogue().create(p2);
//        shop.getProductCatalogue().delete(p1.getId());
//        List<Product> ps = shop.getProductCatalogue().findAll();
//        assertTrue(ps.size() == 1);
//        assertTrue(ps.get(0).getName().equals(p2.getName()));
//    }
//    
//    @Test
//    public void testPersistUpdate() throws Exception {
//        Product p1 = new Product("aaa", 999);
//        Product p2 = new Product(p1.getId(), "bbb", 888);
//        shop.getProductCatalogue().update(p2);
//        List<Product> ps = shop.getProductCatalogue().findAll();
//        assertTrue(ps.size() == 1);
//        assertTrue(ps.get(0).getName().equals(p2.getName()) && 
//                        ps.get(0).getId() == p1.getId());
//    }
//    
//    @Test
//    public void testFind() throws Exception {
//        Product p1 = new Product("aaa", 999);
//        shop.getProductCatalogue().create(p1);
//        Product p2 = shop.getProductCatalogue().find(p1.getId());
//        assertTrue(p2.toString().equals(p1.toString()));
//    }
//    
//    
//    @Test
//    public void testCount() throws Exception {
//        Product p1 = new Product("aaa", 999);
//        Product p2 = new Product("bbb", 999);
//        Product p3 = new Product("ccc", 999);
//        Product p4 = new Product("ddd", 999);
//        shop.getProductCatalogue().create(p1);
//        shop.getProductCatalogue().create(p2);
//        shop.getProductCatalogue().create(p3);
//        shop.getProductCatalogue().create(p4);
//        int c = shop.getProductCatalogue().count();
//        assertTrue(c == 4);
//    }
//    
//    @Test
//    public void testGetByName() {
//        Product p = new Product("aaa", 999);
//        shop.getProductCatalogue().create(p);
//        List<Product> p2 = shop.getProductCatalogue().getByName("aaa");
//        assertTrue(p2.get(0).toString().equals(p.toString()));
//    }
//    
//    @Test
//    public void testGetByPrice() {
//        Product p = new Product("aaa", 999);
//        shop.getProductCatalogue().create(p);
//        List<Product> p2 = shop.getProductCatalogue().getByPrice(999);
//        assertTrue(p2.get(0).toString().equals(p.toString()));
//    }
//    
//    @Test
//    public void testGetById() {
//        Product p = new Product("aaa", 999);
//        shop.getProductCatalogue().create(p);
//        Product p2 = shop.getProductCatalogue().getById(p.getId());
//        assertTrue(p2.toString().equals(p.toString()));
//    }
    

    // Need a standalone em to remove testdata between tests
    // No em accessible from interfaces
    @PersistenceContext(unitName = "DrinkApp_Test")
    @Produces
    @Default
    EntityManager em;

    // Order matters
    private void clearAll() throws Exception {  
        utx.begin();  
        em.joinTransaction();
        em.createQuery("delete from Drinks").executeUpdate();
//        em.createQuery("delete from Customer").executeUpdate();
//        em.createQuery("delete from Product").executeUpdate();
        utx.commit();
    }

}
