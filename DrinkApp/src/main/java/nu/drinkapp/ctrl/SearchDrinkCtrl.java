/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nu.drinkapp.ctrl;

import nu.drinkapp.core.Bar;
import nu.drinkapp.core.Drink;
import nu.drinkapp.core.Rating;
import nu.drinkapp.auth.User;
import nu.drinkapp.bb.DrinkBB;
import nu.drinkapp.bb.DrinkSearchBB;
import nu.drinkapp.bb.LoginBB;
import nu.drinkapp.wrappers.IDrinkBook;
import nu.drinkapp.wrappers.IRatingBook;
import nu.drinkapp.wrappers.IUserBook;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import static org.eclipse.persistence.expressions.ExpressionOperator.log;

@Named
@RequestScoped
public class SearchDrinkCtrl{

    @Inject
    private Bar bar;
    
    private DrinkSearchBB drinkSearchBB;
    private LoginBB loginBB;
    private static final Logger LOG = Logger.getLogger(AddDrinkCtrl.class.getName());
    
    protected SearchDrinkCtrl() {
        // Must have for CDI
    }

    @PostConstruct
    public void post() {
        //LOG.log(Level.INFO, "EditProductCtrl alive {0}", this);
    }

    @PreDestroy
    public void pre() {
        //LOG.log(Level.INFO, "EditProductCtrl to be destroyed {0}", this);
    }

    //@Inject
    //public IngredientListCtrl(SingletonShop shop) {
    //    this.pc = shop.getShop().getProductCatalogue();
    //}
    
    @Inject
    public void setDrinkSearchBB(DrinkSearchBB drinkSearchBB) {
        this.drinkSearchBB = drinkSearchBB;
    }

    @Inject
    public void setLoginBB(LoginBB loginBB) {
        this.loginBB = loginBB;
    }
    
    public void updateDrinks() {
        LOG.log(Level.INFO, "Testing" + drinkSearchBB.getSearchstring(), this);
        if(drinkSearchBB != null && drinkSearchBB.getSearchstring() != null) {
            IDrinkBook db = bar.getDrinkBook();
            /*List<Drink> ld = db.searchByName(drinkSearchBB.getSearchstring());
            List<DrinkBB> ldbb = new ArrayList();
            for(Drink d : ld) {
                ldbb.add(new DrinkBB(d));
            }*/
            List<DrinkBB> ldbb = new ArrayList();
            LOG.log(Level.INFO, "Testing_drinkIngredients " + drinkSearchBB.getIngredients().toString(), this);
            if(drinkSearchBB.getIngredients().isEmpty()) {
                List<Drink> ld = db.searchByName(drinkSearchBB.getSearchstring());
                for(Drink d : ld) {
                    ldbb.add(new DrinkBB(d));
                }
            } else {
                List<Object[]> lobj = db.searchByNameAndIngredient(drinkSearchBB.getSearchstring(), drinkSearchBB.getIngredients());
                for(Object[] obj : lobj) {
                    String drinkname = (String) obj[0];
                    String username = (String) obj[1];
                    Drink d = db.findByUsernameAndDrinkname(username, drinkname);
                    Integer q = (Integer) obj[2];
                    LOG.log(Level.INFO, "Drink: " + drinkname + " : " + q, this);
                    ldbb.add(new DrinkBB(d));
                }
            }
            drinkSearchBB.setDrinks(ldbb);
        }
    }

    public List<DrinkBB> getDrinks() {
        updateDrinks();
        return drinkSearchBB.getDrinks();
    }

    public void updateRating(int rate) {
        DrinkBB dbb = drinkSearchBB.getDrinkBB();
        if(dbb != null && loginBB != null && loginBB.getUsername() != null && !loginBB.getUsername().equals("")) {
            IDrinkBook db = bar.getDrinkBook();
            IUserBook ub = bar.getUserBook();
            IRatingBook rb = bar.getRatingBook();
            User drinkOwner = ub.findByName(dbb.getUsername());
            Drink d = db.findByUserAndDrinkname(drinkOwner, dbb.getDrinkname());
            User user = ub.findByName(loginBB.getUsername());
            Rating rating = rb.findByDrinkAndUser(d, user);
            if(rating != null) {
                Rating updatedRating = new Rating(user, d, rate);
                rb.update(updatedRating);
            } else {
                LOG.log(Level.INFO, "user " + user.getUsername(), this);
                LOG.log(Level.INFO, "drink " + drinkSearchBB.getDrinkBB().getDrinkname(), this);
                LOG.log(Level.INFO, "rate " + rate, this);
                Rating newRating = new Rating(user, d, rate);
                rb.create(newRating);
            }
        }
    }
    
    public List<Integer> getEmptyStars() {
        DrinkBB dbb = drinkSearchBB.getDrinkBB();
        List<Integer> integerList = new ArrayList();
        if(dbb != null && loginBB != null && loginBB.getUsername() != null && !loginBB.getUsername().equals("")) {
            IDrinkBook db = bar.getDrinkBook();
            IUserBook ub = bar.getUserBook();
            IRatingBook rb = bar.getRatingBook();
            User drinkOwner = ub.findByName(dbb.getUsername());
            Drink d = db.findByUserAndDrinkname(drinkOwner, dbb.getDrinkname());
            User user = ub.findByName(loginBB.getUsername());
            Rating rating = rb.findByDrinkAndUser(d, user);
            if(rating != null) {
                int noOfStars = 5;
                for(int i = rating.getRating() + 1; i <= noOfStars; i++) {
                    integerList.add(i);
                }
            } else {
                int noOfStars = 5;
                for(int i = 1; i <= noOfStars; i++) {
                    integerList.add(i);
                }
            }
        }
        return integerList;
    }
    
    public List<Integer> getFilledStars() {
        DrinkBB dbb = drinkSearchBB.getDrinkBB();
        List<Integer> integerList = new ArrayList();
        if(dbb != null && loginBB != null && loginBB.getUsername() != null && !loginBB.getUsername().equals("")) {
            IDrinkBook db = bar.getDrinkBook();
            IUserBook ub = bar.getUserBook();
            IRatingBook rb = bar.getRatingBook();
            User drinkOwner = ub.findByName(dbb.getUsername());
            Drink d = db.findByUserAndDrinkname(drinkOwner, dbb.getDrinkname());
            User user = ub.findByName(loginBB.getUsername());
            Rating rating = rb.findByDrinkAndUser(d, user);
            if(rating != null) {
                int noOfStars = rating.getRating();
                for(int i = 1; i <= noOfStars; i++) {
                    integerList.add(i);
                }
            } 
        }
        return integerList;
    }
    
    public String getAvgRating() {
        DrinkBB dbb = drinkSearchBB.getDrinkBB();
        if(dbb != null) {
            IDrinkBook db = bar.getDrinkBook();
            IUserBook ub = bar.getUserBook();
            IRatingBook rb = bar.getRatingBook();
            User drinkOwner = ub.findByName(dbb.getUsername());
            Drink d = db.findByUserAndDrinkname(drinkOwner, dbb.getDrinkname());
            Double avgRating = rb.getAverageRating(d);
            if(avgRating < 0.5) {
                return "Unrated";
            } else {
                return "Avg (" + Double.toString(avgRating) + "/5)";
            }
        }
        return null;
    }
}
