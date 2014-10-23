package nu.drinkapp.ctrl;

import nu.drinkapp.bb.IngredientListBB;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class IngredientListCtrl{

    private IngredientListBB ingredientBB;

    protected IngredientListCtrl() {
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
    public void setProductBB(IngredientListBB ingredientBB) {
        this.ingredientBB = ingredientBB;
    }
   
    //public String update() {
    //    LOG.log(Level.INFO, "Save: {0}" + ingredientBB);
    //    pc.update(new Product(ingredientBB.getId(), ingredientBB.getName()));
    //    return "productList?faces-redirect=true";
    //}

}
