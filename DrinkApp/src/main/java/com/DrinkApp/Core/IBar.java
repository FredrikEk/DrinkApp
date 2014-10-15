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

/**
 *
 * @author Fredrik
 */
public interface IBar {
    public IDrinkBook getDrinkBook();
    public IIngredientBook getIngredientBook();
    public IUserBook getUserBook();
    public ITypeBook getTypeBook();
    public IDrinkIngredientBook getDrinkIngredientBook();
    public IStepBook getStepBook();
}
