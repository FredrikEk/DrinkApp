package nu.drinkapp.ctrl;

import nu.drinkapp.core.Bar;
import nu.drinkapp.core.Drink;
import nu.drinkapp.bb.DeleteDrinkBB;
import nu.drinkapp.bb.LoginBB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * A controller for deleting drinks
 */

@Named
@RequestScoped
public class DeleteDrinkCtrl {
    
    @Inject
    private Bar bar;
    private DeleteDrinkBB ddBB;
    private LoginBB loginBB;
    
    
    @Inject
    public void setDeleteDrinkBB(DeleteDrinkBB ddBB){
        this.ddBB = ddBB;
    }
    
    @Inject
    public void setLoginBB(LoginBB loginBB){
        this.loginBB = loginBB;
    }
    
    public void deleteDrink(){
        Drink selectedDrink = bar.getDrinkBook().findByUsernameAndDrinkname(loginBB.getUsername(), ddBB.getDrinkName());
        bar.getFavouriteBook().deleteFavourites(selectedDrink);
        bar.getTypeBook().deleteDrinkTypes(loginBB.getUsername(), ddBB.getDrinkName());
        bar.getStepBook().deleteAllByDrink(selectedDrink);
        bar.getRatingBook().deleteRatings(selectedDrink);
        bar.getDrinkIngredientBook().deleteDrinkIngredientByDrink(selectedDrink);
        bar.getDrinkBook().deleteDrink(selectedDrink);
    }
}
