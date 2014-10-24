package nu.drinkapp.wrappers;

import nu.drinkapp.core.Ingredient;
import nu.drinkapp.persistence.IDAO;
import javax.ejb.Local;

/**
 * Interface for IngredientBook
 */

@Local
public interface IIngredientBook extends IDAO<Ingredient, Long> {
    public Ingredient findByName(String name);
}

