package com.DrinkApp.ctrl;

import com.DrinkApp.Core.Bar;
import com.DrinkApp.Core.Drink;
import com.DrinkApp.Core.DrinkIngredient;
import com.DrinkApp.wrappers.IDrinkBook;
import com.DrinkApp.Core.Step;
import com.DrinkApp.Core.Type;
import com.DrinkApp.auth.User;
import com.DrinkApp.bb.ChosenDrinkBB;
import com.DrinkApp.bb.IngredientBB;
import com.DrinkApp.bb.LoginBB;
import com.DrinkApp.bb.StepBB;
import com.DrinkApp.bb.TypeBB;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class ChosenDrinkCtrl {

    @Inject
    private Bar bar;
    private ChosenDrinkBB drinkBB;
    private LoginBB loginBB;
    private static final Logger LOG = Logger.getLogger(EditDrinkCtrl.class.getName());

    protected ChosenDrinkCtrl() {
        // Must have for CDI
    }

    @Inject
    public void setDrinkBB(ChosenDrinkBB drinkBB) {
        this.drinkBB = drinkBB;
    }

    @Inject
    public void setLoginBB(LoginBB loginBB) {
        this.loginBB = loginBB;
    }

    public void init(String drinkname) {
        IDrinkBook idb = bar.getDrinkBook();
        LOG.log(Level.INFO, loginBB.getUsername() + ":" + drinkname, this);
        User user = bar.getUserBook().findByName(loginBB.getUsername());
        Drink d = idb.findByUserAndDrinkname(user, drinkname);
        List<DrinkIngredient> drinkIngredients = d.getIngredients();
        List<IngredientBB> libb = new ArrayList();
        for (DrinkIngredient di : drinkIngredients) {
            IngredientBB ibb = new IngredientBB();
            ibb.setName(di.getIngredient().getName());
            ibb.setQuantity(di.getQuantity());
            libb.add(ibb);
        }
        List<StepBB> lsbb = new ArrayList();
        for (Step s : d.getSteps()) {
            StepBB sbb = new StepBB();
            sbb.setName(s.getDescription());
            lsbb.add(sbb);
        }
        List<TypeBB> ltbb = new ArrayList();
        for (Type t : d.getTypes()) {
            TypeBB tbb = new TypeBB();
            tbb.setName(t.getName());
            ltbb.add(tbb);
        }

        drinkBB.setComment(d.getComment());
        drinkBB.setIngredients(libb);
        drinkBB.setSteps(lsbb);
        drinkBB.setTypes(ltbb);
    }

}
