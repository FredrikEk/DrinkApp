package nu.drinkapp.bb;

import nu.drinkapp.core.Drink;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;


@Named
@ViewScoped
public class FavouriteDrinksBB implements Serializable {
    
    private List<DrinkBB> myFavouriteDrinks;
    
    @PostConstruct
    public void post(){
       if(myFavouriteDrinks == null){
           myFavouriteDrinks = new ArrayList();
       } 
    }
    
    public FavouriteDrinksBB(){
        
    }
    
    public FavouriteDrinksBB(List<Drink> drinks) {
        myFavouriteDrinks = new ArrayList();
        for(Drink drink : drinks) {
            myFavouriteDrinks.add(new DrinkBB(drink));
        }
    }
     
    public void setMyFavouriteDrinks(List<DrinkBB> myDrinks){
        this.myFavouriteDrinks = myDrinks;
    }
    
    public List<DrinkBB> getMyFavouriteDrinks(){
        return myFavouriteDrinks;
    }
    
}
