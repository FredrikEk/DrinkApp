package nu.drinkapp.wrappers;

import nu.drinkapp.core.Drink;
import nu.drinkapp.core.DrinkIngredient;
import nu.drinkapp.core.Ingredient;
import nu.drinkapp.persistence.IDAO;
import javax.ejb.Local;

@Local
public interface IDrinkIngredientBook extends IDAO<DrinkIngredient, Long> {
    public DrinkIngredient findByDrinkAndIngredient(Ingredient i, Drink d);
    public void deleteDrinkIngredientByDrink(Drink drink);
}
