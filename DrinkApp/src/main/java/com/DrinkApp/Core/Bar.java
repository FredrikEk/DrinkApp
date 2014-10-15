/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.DrinkApp.Core;

import com.DrinkApp.wrappers.ITypeBook;
import com.DrinkApp.wrappers.IIngredientBook;
import com.DrinkApp.wrappers.IUserBook;
import com.DrinkApp.wrappers.IDrinkBook;
import com.DrinkApp.wrappers.IDrinkIngredientBook;
import com.DrinkApp.wrappers.IStepBook;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 *
 * @author Fredrik
 */
@ApplicationScoped
public class Bar implements IBar {

    //@EJB
    @Inject
    private IDrinkBook drinkBook;
    @Inject
    private IUserBook userBook;
    @Inject
    private IIngredientBook ingredientBook;
    @Inject
    private ITypeBook typeBook;
    @Inject
    private IDrinkIngredientBook drinkIngredientBook;
    @Inject
    private IStepBook stepBook;
    
    public Bar() {
        //initTestData();
        Logger.getAnonymousLogger().log(Level.INFO, "Shop alive");
    }

    public static IBar newInstance() {
        return new Bar();
    }

    @Override
    public IDrinkBook getDrinkBook() {
        return drinkBook;
    }

    @Override
    public IIngredientBook getIngredientBook() {
        return ingredientBook;
    }

    @Override
    public IUserBook getUserBook() {
        return userBook;
    }

    @Override
    public ITypeBook getTypeBook() {
       return typeBook;
    }

    @Override
    public IDrinkIngredientBook getDrinkIngredientBook() {
        return drinkIngredientBook;
    }

    @Override
    public IStepBook getStepBook() {
        return stepBook;
    }
    
    
}