/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.DrinkApp.ctrl;

import com.DrinkApp.Core.Bar;
import com.DrinkApp.Core.Drink;
import com.DrinkApp.bb.DrinkBB;
import com.DrinkApp.bb.DrinkSearchBB;
import com.DrinkApp.bb.IngredientBB;
import com.DrinkApp.wrappers.IDrinkBook;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class SearchDrinkCtrl{

    @Inject
    private Bar bar;
    private IngredientBB ingredientBB;
    private DrinkSearchBB drinkSearchBB;
    private static final Logger LOG = Logger.getLogger(AddDrinkCtrl.class.getName());
    private DrinkBB drinkBB;
    
    protected SearchDrinkCtrl() {
        // Must have for CDI
    }

    @PostConstruct
    public void post() {
        //LOG.log(Level.INFO, "EditProductCtrl alive {0}", this);
    }

    @PreDestroy
    public void pre() {
        //LOG.log(Level.INFO, "EditProductCtrl to be destroyed {0}", this);
    }

    //@Inject
    //public IngredientListCtrl(SingletonShop shop) {
    //    this.pc = shop.getShop().getProductCatalogue();
    //}

    @Inject
    public void setIngredientBB(IngredientBB ingredientBB) {
        this.ingredientBB = ingredientBB;
    }
    
    @Inject
    public void setDrinkSearchBB(DrinkSearchBB drinkSearchBB) {
        this.drinkSearchBB = drinkSearchBB;
    }
    
    @Inject
    public void setDrinkBB(DrinkBB drinkBB) {
        this.drinkBB = drinkBB;
    } 
   
    public void updateDrinks() {
        LOG.log(Level.INFO, "Testing" + drinkSearchBB.getSearchstring(), this);
        if(drinkBB != null && drinkSearchBB.getSearchstring() != null) {
            IDrinkBook db = bar.getDrinkBook();
            List<Drink> ld = db.searchByName(drinkSearchBB.getSearchstring());
            List<DrinkBB> ldbb = new ArrayList();
            for(Drink d : ld) {
                ldbb.add(new DrinkBB(d));
            }
            drinkSearchBB.setDrinks(ldbb);
        }
    }

    public List<DrinkBB> getDrinks() {
        updateDrinks();
        return drinkSearchBB.getDrinks();
    }

}
