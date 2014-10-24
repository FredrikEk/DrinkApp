package nu.drinkapp.bb;

import nu.drinkapp.core.Rating;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

/**
 * The back bean of raing
 */

@Named
@ViewScoped
public class RatingBB implements Serializable {
     
    @NotNull
    private float rating;
    
    @PostConstruct
    public void post(){
        
    }
    
    public RatingBB() {
        
    }
    
    public RatingBB(Rating r) {
        this.rating = r.getRating();
    }
   
    public void setRating(int rating){
        this.rating = rating;
    }
    
    public float getRating(){
        return this.rating;
    }    
}
