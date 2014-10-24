package nu.drinkapp.ctrl;

import nu.drinkapp.core.Bar;
import nu.drinkapp.core.Drink;
import nu.drinkapp.core.Favourite;
import nu.drinkapp.auth.User;
import nu.drinkapp.bb.DrinkBB;
import nu.drinkapp.bb.FavouriteDrinksBB;
import nu.drinkapp.bb.LoginBB;
import nu.drinkapp.wrappers.IDrinkBook;
import nu.drinkapp.wrappers.IFavouriteBook;
import nu.drinkapp.wrappers.IUserBook;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import nu.drinkapp.auth.AuthDAO;
import nu.drinkapp.bb.ChosenDrinkBB;

/**
 * A controller for favourite drinks
 */

@Named
@RequestScoped
public class FavouriteDrinksCtrl {

    @Inject
    private Bar bar;
    private FavouriteDrinksBB favouriteDrinksBB;
    private LoginBB loginBB;
    @Inject
    AuthDAO authDAO;

    @Inject
    public void setFavouriteDrinksBB(FavouriteDrinksBB favouriteDrinksBB) {
        this.favouriteDrinksBB = favouriteDrinksBB;
    }

    @Inject
    public void setLoginBB(LoginBB loginBB) {
        this.loginBB = loginBB;
    }

    public boolean isFavourite(DrinkBB dbb) {
        IDrinkBook db = bar.getDrinkBook();
        IUserBook ub = bar.getUserBook();
        IFavouriteBook fb = bar.getFavouriteBook();
        User drinkOwner = ub.findByName(dbb.getUsername());
        Drink d = db.findByUserAndDrinkname(drinkOwner, dbb.getDrinkname());
        User user = ub.findByName(loginBB.getUsername());
        Favourite isFavourite = fb.findByDrinkAndUser(d, user);
        if(isFavourite != null) {
            return true;
        } else {
            return false;
        }
    }
	
	public boolean isFavouriteChosenDrink(ChosenDrinkBB dbb) {
            IDrinkBook db = bar.getDrinkBook();
            IUserBook ub = bar.getUserBook();
            IFavouriteBook fb = bar.getFavouriteBook();
            User drinkOwner = ub.findByName(dbb.getUsername());
            Drink d = db.findByUserAndDrinkname(drinkOwner, dbb.getDrinkname());
            User user = ub.findByName(loginBB.getUsername());
            Favourite isFavourite = fb.findByDrinkAndUser(d, user);
            if(isFavourite != null) {
		return true;
            } else {
		return false;
            }
	}

    public List<DrinkBB> getFavouriteDrinksBB() {
        List<Favourite> favourites = bar.getFavouriteBook().findByUser(loginBB.getUsername());
        List<Drink> drinks = new ArrayList();
        for (Favourite f : favourites) {
            drinks.add(f.getDrink());
        }
        favouriteDrinksBB = new FavouriteDrinksBB(drinks);
        return favouriteDrinksBB.getMyFavouriteDrinks();
    }

    public void deleteAsFavourite(DrinkBB dbb) {
        IDrinkBook db = bar.getDrinkBook();
        IUserBook ub = bar.getUserBook();
        IFavouriteBook fb = bar.getFavouriteBook();
        User drinkOwner = ub.findByName(dbb.getUsername());
        Drink d = db.findByUserAndDrinkname(drinkOwner, dbb.getDrinkname());
        User user = ub.findByName(loginBB.getUsername());
        fb.deleteByUserAndDrink(user, d);
    }
}
