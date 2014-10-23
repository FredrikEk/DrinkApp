/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nu.drinkapp.wrappers;

import nu.drinkapp.core.Drink;
import nu.drinkapp.auth.User;
import nu.drinkapp.persistence.IDAO;
import java.util.List;
import javax.ejb.Local;

@Local
public interface IDrinkBook extends IDAO<Drink, Long> {

    List<Drink> findByName(String name);
    Drink findById(Long id);
    List<Drink> findByUser(String username);
    List<Drink> findByIngredient(String ingredient);
    Drink findByUserAndDrinkname(User user, String drinkname);
    List<Drink> searchByName(String drinkname);
    List searchByNameAndIngredient(String drinkname, List<String> ingredients);
    Drink findByUsernameAndDrinkname(String username, String drinkname);
    void deleteDrink(Drink drink);
}

