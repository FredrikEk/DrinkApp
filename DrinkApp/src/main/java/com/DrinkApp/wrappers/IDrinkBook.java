/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.DrinkApp.Core;

import com.DrinkApp.persistence.IDAO;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Fredrik
 */
@Local
public interface IDrinkBook extends IDAO<Drink, Long> {

    List<Drink> getByName(String name);
    Drink getById(Long id);
    List<Drink> getByUser(String username);
	List<Drink> getByIngredient(String ingredient);
	
}

