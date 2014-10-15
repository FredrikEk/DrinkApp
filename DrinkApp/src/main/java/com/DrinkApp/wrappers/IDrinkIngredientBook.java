/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.DrinkApp.wrappers;

import com.DrinkApp.Core.DrinkIngredient;
import com.DrinkApp.persistence.IDAO;
import javax.ejb.Local;

/**
 *
 * @author Fredrik
 */
@Local
public interface IDrinkIngredientBook extends IDAO<DrinkIngredient, Long> {

}
