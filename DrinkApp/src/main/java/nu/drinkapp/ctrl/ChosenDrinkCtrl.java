package nu.drinkapp.ctrl;

import nu.drinkapp.core.Bar;
import nu.drinkapp.core.Drink;
import nu.drinkapp.core.DrinkIngredient;
import nu.drinkapp.wrappers.IDrinkBook;
import nu.drinkapp.core.Step;
import nu.drinkapp.core.Type;
import nu.drinkapp.auth.User;
import nu.drinkapp.bb.ChosenDrinkBB;
import nu.drinkapp.bb.IngredientBB;
import nu.drinkapp.bb.LoginBB;
import nu.drinkapp.bb.StepBB;
import nu.drinkapp.bb.TypeBB;
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
    private ChosenDrinkBB chosenDrinkBB;
    private static final Logger LOG = Logger.getLogger(EditDrinkCtrl.class.getName());

    protected ChosenDrinkCtrl() {
        // Must have for CDI
    }

    @Inject
    public void setDrinkBB(ChosenDrinkBB chosenDrinkBB) {
        this.chosenDrinkBB = chosenDrinkBB;
    }


    public void init() {
        IDrinkBook idb = bar.getDrinkBook();
        LOG.log(Level.INFO, chosenDrinkBB.getUsername() + ":" + chosenDrinkBB.getDrinkname(), this);
        User user = bar.getUserBook().findByName(chosenDrinkBB.getUsername());
        Drink d = idb.findByUserAndDrinkname(user, chosenDrinkBB.getDrinkname());
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

        chosenDrinkBB.setComment(d.getComment());
        chosenDrinkBB.setIngredients(libb);
        chosenDrinkBB.setSteps(lsbb);
        chosenDrinkBB.setTypes(ltbb);
    }

}
