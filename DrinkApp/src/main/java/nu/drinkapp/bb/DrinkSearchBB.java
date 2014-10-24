package nu.drinkapp.bb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

@Named
@ViewScoped
public class DrinkSearchBB implements Serializable{
	
    @NotNull
    private String searchstring;
    @NotNull
    private List<String> ingredients;  
    private List<DrinkBB> drinks;
    private DrinkBB drinkBB;
    private String ingredientString;
        
        
        
    @PostConstruct
    public void post(){
        if(ingredients == null) {
            ingredients = new ArrayList();
        }
        if(drinks == null) {
            drinks = new ArrayList();
        }
    }
    
    public void setSearchstring(String searchstring){
        this.searchstring = searchstring;
    }
    
    public void setIngredients(List<String> ingredients){
        this.ingredients = ingredients;
    }
    
    public void setDrinks(List<DrinkBB> drinks) {
        this.drinks = drinks;
    }

    public void setDrinkBB(DrinkBB drinkBB) {
        this.drinkBB = drinkBB;
    }
    
    public void setIngredientString(String ingredientString) {
        this.ingredientString = ingredientString;
    }
    
    public String getSearchstring(){
        return this.searchstring;
    }
    
    public List<String> getIngredients(){
        return this.ingredients;
    }
    
    public List<DrinkBB> getDrinks() {
        return this.drinks;
    }
    
    public DrinkBB getDrinkBB() {
        return this.drinkBB;
    }
    
    public String getIngredientString() {
        return this.ingredientString;
    }

    public void removeIngredient(String s) {
        this.ingredients.remove(s);
    }
    
    public void addIngredientTest() {
        this.ingredients.add(ingredientString);
        this.ingredientString = "";
    }
    
}

