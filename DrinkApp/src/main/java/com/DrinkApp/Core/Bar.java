/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.DrinkApp.Core;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author Fredrik
 */
@ApplicationScoped
public class Bar implements IBar {

    @EJB
    private IDrinkBook drinkBook;
    
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

}