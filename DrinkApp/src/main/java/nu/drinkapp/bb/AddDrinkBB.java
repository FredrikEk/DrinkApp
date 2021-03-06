package nu.drinkapp.bb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

/**
 * The back bean of adding a drink
 */

@Named
@ViewScoped
public class AddDrinkBB implements Serializable{
	
	@NotNull
	private String drinkname;
	@NotNull
	private String comment;
	@NotNull
        private List<IngredientBB> ingredients;
	@NotNull
	private List<TypeBB> types;
	@NotNull
	private List<StepBB> steps;
	
    @PostConstruct
    public void post(){
        if(ingredients == null) {
            ingredients = new ArrayList();
        }
        if(types == null) {
            types = new ArrayList();
        }
        if(steps == null) {
            steps = new ArrayList();
        }
    }
    
    public void setDrinkname(String drinkname){
        this.drinkname = drinkname;
    }
    
    public void setComment(String comment){
        this.comment = comment;
    }
    
    public void setIngredients(List<IngredientBB> ingredients){
        this.ingredients = ingredients;
    }
	
    public void setSteps(List<StepBB> steps){
            this.steps = steps;
    }

    public void setTypes(List<TypeBB> types) {
            this.types = types;
    }

    public String getDrinkname(){
        return this.drinkname;
    }
    
    public String getComment(){
        return this.comment;
    }
    
    public List<IngredientBB> getIngredients(){
        return this.ingredients;
    }
	
	
    public List<StepBB> getSteps(){
            return this.steps;
    }

    public List<TypeBB> getTypes() {
            return this.types;
    }
    
    public void addIngredient(IngredientBB ingredientBB) {
        IngredientBB i = new IngredientBB();
        i.setName(ingredientBB.getName());
        i.setQuantity(ingredientBB.getQuantity());
        ingredients.add(i);
        ingredientBB.setName("");
        ingredientBB.setQuantity("");
    }
    
    public void addType(TypeBB typeBB) {
        TypeBB typeB = new TypeBB();
        typeB.setName(typeBB.getName());
        types.add(typeB);
        typeBB.setName("");
    }
    
    public void removeType(TypeBB typeBB) {
        types.remove(typeBB);
    }
    
    public void removeIngredient(IngredientBB ingredientBB) {
        ingredients.remove(ingredientBB);
    }
    
    public void removeStep(StepBB stepBB) {
        steps.remove(stepBB);
    }
}
