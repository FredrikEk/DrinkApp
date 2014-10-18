/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.DrinkApp.wrappers;

import com.DrinkApp.Core.Drink;
import com.DrinkApp.Core.Step;
import com.DrinkApp.persistence.IDAO;
import javax.ejb.Local;

@Local
public interface IStepBook extends IDAO<Step, Long> {
    public void deleteAllByDrink(Drink drink);
}
