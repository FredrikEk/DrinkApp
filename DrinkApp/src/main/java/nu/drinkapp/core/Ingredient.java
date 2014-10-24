package nu.drinkapp.core;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/*
 * The Ingredient model. Stores ingredients in the database.
 */

@Entity
public class Ingredient implements Serializable{
    
    @Column
    @Id
    private String name;
    @OneToMany(mappedBy = "ingredient")
    private List<DrinkIngredient> drinks;
    
    public Ingredient(){
        this.name = "";
    }
    
    public Ingredient(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }

    public List<DrinkIngredient> getDrinks() {
        return drinks;
    }
    
    @Override
    public String toString() {
        return "Ingredient{name=" + name  + '}';
    }
}
