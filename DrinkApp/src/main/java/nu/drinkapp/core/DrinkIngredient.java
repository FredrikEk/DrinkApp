package nu.drinkapp.core;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;


/*
 * An entity to hold data regarding which ingredients each drink contains
 */

@Entity @IdClass(DrinkIngredientId.class)
@NamedQueries( {@NamedQuery(name = "DrinkIngredient.findByIngredientAndDrink", query = "SELECT di FROM DrinkIngredient di WHERE di.ingredient = :ingredient AND di.drink = :drink"),
                @NamedQuery(name = "DrinkIngredient.deleteAllDrinkIngredient", query = "DELETE FROM DrinkIngredient di WHERE di.drink = :drink"),
				@NamedQuery(name = "DrinkIngredient.findByIngredient", query = "SELECT di FROM DrinkIngredient di WHERE di.ingredient = :ingredient")
})
public class DrinkIngredient implements Serializable{
    
    @ManyToOne
    @Id
    private Drink drink;
    @ManyToOne
    @Id
    private Ingredient ingredient;
    @Column
    private String quantity;
    
    public DrinkIngredient(){
        this.drink      = new Drink();
        this.ingredient = new Ingredient();
        this.quantity   = null;
    }
    
    public DrinkIngredient(Drink drink, Ingredient ingredient, String quantity) {
        this.drink      = drink;
        this.ingredient = ingredient;
        this.quantity   = quantity;
        }
    
    public Drink getDrink() {
        return drink;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }
    
    public String getQuantity() {
        return quantity;
    }
    
    @Override
    public String toString() {
        return "DrinkIngredient{ Ingredient=" + ingredient.getName() + ", Drink=" + drink.getName() + '}';
    }
}
