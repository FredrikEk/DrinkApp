package nu.drinkapp.ctrl;

import nu.drinkapp.core.Bar;
import nu.drinkapp.core.Drink;
import nu.drinkapp.core.DrinkIngredient;
import nu.drinkapp.wrappers.IDrinkBook;
import nu.drinkapp.core.Step;
import nu.drinkapp.core.Type;
import nu.drinkapp.auth.User;
import nu.drinkapp.bb.ChosenDrinkBB;
import nu.drinkapp.bb.IngredientBB;
import nu.drinkapp.bb.StepBB;
import nu.drinkapp.bb.TypeBB;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import nu.drinkapp.bb.DrinkBB;
import nu.drinkapp.bb.LoginBB;
import nu.drinkapp.core.Favourite;
import nu.drinkapp.core.Rating;
import nu.drinkapp.wrappers.IFavouriteBook;
import nu.drinkapp.wrappers.IRatingBook;
import nu.drinkapp.wrappers.IUserBook;

@Named
@RequestScoped
public class ChosenDrinkCtrl {

    @Inject
    private Bar bar;
    private ChosenDrinkBB chosenDrinkBB;
    private static final Logger LOG = Logger.getLogger(EditDrinkCtrl.class.getName());
	private LoginBB loginBB;
	
	
    protected ChosenDrinkCtrl() {
        // Must have for CDI
    }

    @Inject
    public void setDrinkBB(ChosenDrinkBB chosenDrinkBB) {
        this.chosenDrinkBB = chosenDrinkBB;
    }

	@Inject
	public void setLoginBB(LoginBB loginBB) {
		this.loginBB = loginBB;
	}
	
    public void init() {
        IDrinkBook idb = bar.getDrinkBook();
        LOG.log(Level.INFO, chosenDrinkBB.getUsername() + ":" + chosenDrinkBB.getDrinkname(), this);
        User user = bar.getUserBook().findByName(chosenDrinkBB.getUsername());
        Drink d = idb.findByUserAndDrinkname(user, chosenDrinkBB.getDrinkname());
        List<DrinkIngredient> drinkIngredients = d.getIngredients();
        List<IngredientBB> libb = new ArrayList();
        for (DrinkIngredient di : drinkIngredients) {
            IngredientBB ibb = new IngredientBB();
            ibb.setName(di.getIngredient().getName());
            ibb.setQuantity(di.getQuantity());
            libb.add(ibb);
        }
        List<StepBB> lsbb = new ArrayList();
        for (Step s : d.getSteps()) {
            StepBB sbb = new StepBB();
            sbb.setName(s.getDescription());
            lsbb.add(sbb);
        }
        List<TypeBB> ltbb = new ArrayList();
        for (Type t : d.getTypes()) {
            TypeBB tbb = new TypeBB();
            tbb.setName(t.getName());
            ltbb.add(tbb);
        }

        chosenDrinkBB.setComment(d.getComment());
        chosenDrinkBB.setIngredients(libb);
        chosenDrinkBB.setSteps(lsbb);
        chosenDrinkBB.setTypes(ltbb);
    }
	
	
	public void updateRating(int rate) {
        if (chosenDrinkBB != null && loginBB != null && loginBB.getUsername() != null && !loginBB.getUsername().equals("")) {
            IDrinkBook db = bar.getDrinkBook();
            IUserBook ub = bar.getUserBook();
            IRatingBook rb = bar.getRatingBook();
            User drinkOwner = ub.findByName(chosenDrinkBB.getUsername());
            Drink d = db.findByUserAndDrinkname(drinkOwner, chosenDrinkBB.getDrinkname());
            User user = ub.findByName(loginBB.getUsername());
            Rating rating = rb.findByDrinkAndUser(d, user);
            if (rating != null) {
                Rating updatedRating = new Rating(user, d, rate);
                rb.update(updatedRating);
            } else {
                LOG.log(Level.INFO, "user " + user.getUsername(), this);
                LOG.log(Level.INFO, "drink " + chosenDrinkBB.getDrinkname(), this);
                LOG.log(Level.INFO, "rate " + rate, this);
                Rating newRating = new Rating(user, d, rate);
                rb.create(newRating);
            }
        }
    }

    public void updateFavourite() {
        if (chosenDrinkBB != null && loginBB != null && loginBB.getUsername() != null && !loginBB.getUsername().equals("")) {
            IDrinkBook db = bar.getDrinkBook();
            IUserBook ub = bar.getUserBook();
            IFavouriteBook fb = bar.getFavouriteBook();
            User drinkOwner = ub.findByName(chosenDrinkBB.getUsername());
            Drink d = db.findByUserAndDrinkname(drinkOwner, chosenDrinkBB.getDrinkname());
            User user = ub.findByName(loginBB.getUsername());
            Favourite favourite = fb.findByDrinkAndUser(d, user);
            if (favourite != null) {//If you click when it's already marke as favourite, you remove it...
                fb.deleteByUserAndDrink(user, d);
            } else {
                LOG.log(Level.INFO, "user " + user.getUsername(), this);
                LOG.log(Level.INFO, "drink " + chosenDrinkBB.getDrinkname(), this);
                Favourite newFavourite = new Favourite(user, d);
                fb.create(newFavourite);
            }
        }
    }

    public List<Integer> getEmptyStars() {
        List<Integer> integerList = new ArrayList();
        if (chosenDrinkBB != null && loginBB != null && loginBB.getUsername() != null && !loginBB.getUsername().equals("")) {
            IDrinkBook db = bar.getDrinkBook();
            IUserBook ub = bar.getUserBook();
            IRatingBook rb = bar.getRatingBook();
            User drinkOwner = ub.findByName(chosenDrinkBB.getUsername());
            Drink d = db.findByUserAndDrinkname(drinkOwner, chosenDrinkBB.getDrinkname());
            User user = ub.findByName(loginBB.getUsername());
            Rating rating = rb.findByDrinkAndUser(d, user);
            if (rating != null) {
                int noOfStars = 5;
                for (int i = rating.getRating() + 1; i <= noOfStars; i++) {
                    integerList.add(i);
                }
            } else {
                int noOfStars = 5;
                for (int i = 1; i <= noOfStars; i++) {
                    integerList.add(i);
                }
            }
        }
        return integerList;
    }

	
	
    public List<Integer> getFilledStars() {
        List<Integer> integerList = new ArrayList();
        if (chosenDrinkBB != null && loginBB != null && loginBB.getUsername() != null && !loginBB.getUsername().equals("")) {
            IDrinkBook db = bar.getDrinkBook();
            IUserBook ub = bar.getUserBook();
            IRatingBook rb = bar.getRatingBook();
            User drinkOwner = ub.findByName(chosenDrinkBB.getUsername());
            Drink d = db.findByUserAndDrinkname(drinkOwner, chosenDrinkBB.getDrinkname());
            User user = ub.findByName(loginBB.getUsername());
            Rating rating = rb.findByDrinkAndUser(d, user);
            if (rating != null) {
                int noOfStars = rating.getRating();
                for (int i = 1; i <= noOfStars; i++) {
                    integerList.add(i);
                }
            }
        }
        return integerList;
    }

    public String getAvgRating() {
        if (chosenDrinkBB != null) {
            IDrinkBook db = bar.getDrinkBook();
            IUserBook ub = bar.getUserBook();
            IRatingBook rb = bar.getRatingBook();
            User drinkOwner = ub.findByName(chosenDrinkBB.getUsername());
            Drink d = db.findByUserAndDrinkname(drinkOwner, chosenDrinkBB.getDrinkname());
            Double avgRating = rb.getAverageRating(d);
            if (avgRating < 0.5) {
                return "Unrated";
            } else {
                return "Avg (" + Double.toString(avgRating) + "/5)";
            }
        }
        return null;
    }
}
