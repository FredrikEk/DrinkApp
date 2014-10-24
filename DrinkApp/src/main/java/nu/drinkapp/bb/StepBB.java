package nu.drinkapp.bb;

import nu.drinkapp.core.Step;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

@Named
@ViewScoped
public class StepBB implements Serializable, Comparable<StepBB>{
	
    @NotNull
    private String name;
	private int stepNr;

    @PostConstruct
    public void post(){
    }
    
    public StepBB() {   
    }
    
    public StepBB(Step s) {
        this.name = s.getDescription();
		this.stepNr = s.getStepNr();
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public String getName(){
        return this.name;
    }
	
	public void setStepNr(int stepNr) {
		this.stepNr = stepNr;
	}
	
	public int getStepNr() {
		return this.stepNr;
	}
	
	@Override
	public int compareTo(StepBB sbb) {
		return Integer.compare(this.stepNr, sbb.getStepNr());
	}
    
}
