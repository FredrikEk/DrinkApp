package nu.drinkapp.core;

import java.io.Serializable;
import java.util.Objects;

/**
 * Help class for making the entities drink and ingredient a primary 
 * key pair 
 */
public class DrinkIngredientId implements Serializable{
    
    DrinkUser drink;
    String ingredient;

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DrinkIngredientId other = (DrinkIngredientId) obj;
        if (!Objects.equals(this.drink, other.drink)) {
            return false;
        }
        if (!Objects.equals(this.ingredient, other.ingredient)) {
            return false;
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        return Integer.parseInt(drink.hashCode() + "" + ingredient.hashCode());
    }
}
