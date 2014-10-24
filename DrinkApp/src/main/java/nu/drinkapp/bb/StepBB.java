package nu.drinkapp.bb;

import nu.drinkapp.core.Step;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

@Named
@ViewScoped
public class StepBB implements Serializable{
	
    @NotNull
    private String name;

    @PostConstruct
    public void post(){
    }
    
    public StepBB() {   
    }
    
    public StepBB(Step s) {
        this.name = s.getDescription();
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public String getName(){
        return this.name;
    }
    
}
