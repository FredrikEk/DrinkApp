package nu.drinkapp.bb;

import nu.drinkapp.core.DrinkIngredient;
import nu.drinkapp.core.Ingredient;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

/**
 * The back bean of ingredients
 */

@Named
@ViewScoped
public class IngredientBB implements Serializable{
	
    @NotNull
    private String name;
    @NotNull
    private String quantity;

    @PostConstruct
    public void post(){
    }
    
    public IngredientBB() {
        
    }
    
    public IngredientBB(Ingredient i, String quantity) {
        this.name = i.getName();
        this.quantity = quantity;
    }
    
    public IngredientBB(DrinkIngredient di) {
        this.name = di.getIngredient().getName();
        this.quantity = di.getQuantity();
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public void setQuantity(String quantity){
        this.quantity = quantity;
    }
    
    public String getName(){
        return this.name;
    }
    
    public String getQuantity(){
        return this.quantity;
    } 
}
