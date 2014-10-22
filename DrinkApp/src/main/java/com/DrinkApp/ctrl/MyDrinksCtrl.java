/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.DrinkApp.ctrl;

import com.DrinkApp.Core.Bar;
import com.DrinkApp.Core.Drink;
import com.DrinkApp.bb.DrinkBB;
import com.DrinkApp.bb.LoginBB;
import com.DrinkApp.bb.MyDrinksBB;
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
