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
public class MyDrinksBB implements Serializable {
    
    private List<DrinkBB> myDrinks;
    
    @PostConstruct
    public void post(){
       if(myDrinks == null){
           myDrinks = new ArrayList();
       } 
    }
    
    public MyDrinksBB(){
        
    }
    
    public MyDrinksBB(List<Drink> drinks) {
        myDrinks = new ArrayList();
        for(Drink drink : drinks) {
            myDrinks.add(new DrinkBB(drink));
        }
    }
     
    public void setMyDrinks(List<DrinkBB> myDrinks){
        this.myDrinks = myDrinks;
    }
    
    public List<DrinkBB> getMyDrinks(){
        return myDrinks;
    }
    
}
