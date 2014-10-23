/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nu.drinkapp.bb;

import nu.drinkapp.core.DrinkIngredient;
import nu.drinkapp.core.Rating;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

@Named
@ViewScoped
public class RatingBB implements Serializable {
     
    @NotNull
    private float rating;
    
    private static final Logger LOG = Logger.getLogger(RatingBB.class.getName());

    @PostConstruct
    public void post(){
        LOG.log(Level.INFO, "The rating is alive ");
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
