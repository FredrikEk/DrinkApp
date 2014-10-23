/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nu.drinkapp.wrappers;

import nu.drinkapp.core.Drink;
import nu.drinkapp.core.Step;
import nu.drinkapp.persistence.IDAO;
import javax.ejb.Local;

@Local
public interface IStepBook extends IDAO<Step, Long> {
    public void deleteAllByDrink(Drink drink);
}
