/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nu.drinkapp.ctrl;

import nu.drinkapp.core.Bar;
import nu.drinkapp.core.Drink;
import nu.drinkapp.bb.DrinkBB;
import nu.drinkapp.bb.LoginBB;
import nu.drinkapp.bb.MyDrinksBB;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Michael
 */
@Named
@RequestScoped
public class MyDrinksCtrl {
    
    @Inject
    private Bar bar;
    private MyDrinksBB myDrinksBB;
    private LoginBB loginBB;
    
    @Inject
    public void setMyDrinksBB(MyDrinksBB myDrinksBB){
        this.myDrinksBB = myDrinksBB;
    }
    
    @Inject
    public void setLoginBB(LoginBB loginBB){
        this.loginBB = loginBB;
    }
    
    public List<DrinkBB> getMyDrinks() {        
        List<Drink> drinks = bar.getDrinkBook().findByUser(loginBB.getUsername());
        myDrinksBB = new MyDrinksBB(drinks);
        return myDrinksBB.getMyDrinks();
    }
    
}
