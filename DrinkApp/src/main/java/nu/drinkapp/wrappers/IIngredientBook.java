/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nu.drinkapp.wrappers;

import nu.drinkapp.core.Ingredient;
import nu.drinkapp.persistence.IDAO;
import java.util.List;
import javax.ejb.Local;

@Local
public interface IIngredientBook extends IDAO<Ingredient, Long> {
    public Ingredient findByName(String name);
}

