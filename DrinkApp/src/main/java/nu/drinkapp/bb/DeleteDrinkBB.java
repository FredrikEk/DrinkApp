package nu.drinkapp.bb;

import java.io.Serializable;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

@Named
@ViewScoped
public class DeleteDrinkBB implements Serializable {
    
    @NotNull
    private String drinkName;
    
    public DeleteDrinkBB(){
        
    }
    
    public void setDrinkName(String drinkName){
        this.drinkName = drinkName;
    }
    
    public String getDrinkName(){
        return drinkName;
    }
    
}
