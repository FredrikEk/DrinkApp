/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nu.drinkapp.ctrl;

import nu.drinkapp.core.Bar;
import nu.drinkapp.core.Drink;
import nu.drinkapp.bb.DeleteDrinkBB;
import nu.drinkapp.bb.LoginBB;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Michael
 */
@Named
@RequestScoped
public class DeleteDrinkCtrl {
    
    @Inject
    private Bar bar;
    private DeleteDrinkBB ddBB;
    private LoginBB loginBB;
    
    private static final Logger LOG = Logger.getLogger(DeleteDrinkCtrl.class.getName());
    
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
        LOG.log(Level.INFO, ddBB.getDrinkName());
    }
    
}
