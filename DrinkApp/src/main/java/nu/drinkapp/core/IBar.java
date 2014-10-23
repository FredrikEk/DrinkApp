/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nu.drinkapp.core;

import nu.drinkapp.wrappers.ITypeBook;
import nu.drinkapp.wrappers.IIngredientBook;
import nu.drinkapp.wrappers.IUserBook;
import nu.drinkapp.wrappers.IDrinkBook;
import nu.drinkapp.wrappers.IDrinkIngredientBook;
import nu.drinkapp.wrappers.IFavouriteBook;
import nu.drinkapp.wrappers.IRatingBook;
import nu.drinkapp.wrappers.IStepBook;

/**
 *
 * Interface to define skeleton code for the bar class
 * 
 */
public interface IBar {
    public IDrinkBook getDrinkBook();
    public IIngredientBook getIngredientBook();
    public IUserBook getUserBook();
    public ITypeBook getTypeBook();
    public IDrinkIngredientBook getDrinkIngredientBook();
    public IStepBook getStepBook();
    public IRatingBook getRatingBook();
    public IFavouriteBook getFavouriteBook();
}
