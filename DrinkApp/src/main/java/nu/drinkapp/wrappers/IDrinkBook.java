package nu.drinkapp.wrappers;

import nu.drinkapp.core.Drink;
import nu.drinkapp.auth.User;
import nu.drinkapp.persistence.IDAO;
import java.util.List;
import javax.ejb.Local;

/**
 * Interface for DrinkBook
 */

@Local
public interface IDrinkBook extends IDAO<Drink, Long> {

    List<Drink> findByName(String name);
    Drink findById(Long id);
    List<Drink> findByUser(String username);
    Drink findByUserAndDrinkname(User user, String drinkname);
    List<Drink> searchByName(String drinkname);
    List searchByNameAndIngredient(String drinkname, List<String> ingredients);
    Drink findByUsernameAndDrinkname(String username, String drinkname);
    void deleteDrink(Drink drink);
}

