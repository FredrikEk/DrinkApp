package nu.drinkapp.ctrl;

import nu.drinkapp.core.Bar;
import nu.drinkapp.core.Drink;
import nu.drinkapp.core.DrinkIngredient;
import nu.drinkapp.wrappers.IDrinkBook;
import nu.drinkapp.wrappers.IIngredientBook;
import nu.drinkapp.wrappers.ITypeBook;
import nu.drinkapp.core.Ingredient;
import nu.drinkapp.core.Step;
import nu.drinkapp.core.Type;
import nu.drinkapp.auth.User;
import nu.drinkapp.bb.EditDrinkBB;
import nu.drinkapp.bb.IngredientBB;
import nu.drinkapp.bb.LoginBB;
import nu.drinkapp.bb.StepBB;
import nu.drinkapp.bb.TypeBB;
import nu.drinkapp.wrappers.IDrinkIngredientBook;
import nu.drinkapp.wrappers.IStepBook;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * A controller for edit drink
 */

@Named
@RequestScoped
public class EditDrinkCtrl{

    @Inject
    private Bar bar;
    private IDrinkBook db;
    private TypeBB typeBB;
    private StepBB stepBB;
    private IngredientBB ingredientBB;
    private EditDrinkBB drinkBB;
    private LoginBB loginBB;
    
    protected EditDrinkCtrl() {
        // Must have for CDI
    }

    @PostConstruct
    public void post() {
    }

    @PreDestroy
    public void pre() {
    }


    @Inject
    public void setIngredientBB(IngredientBB ingredientBB) {
        this.ingredientBB = ingredientBB;
    }
    
    @Inject
    public void setDrinkBB(EditDrinkBB drinkBB) {
        this.drinkBB = drinkBB;
    }
    
    @Inject
    public void setTypeBB(TypeBB typeBB) {
        this.typeBB = typeBB;
    }
    
    @Inject
    public void setStepBB(StepBB stepBB) {
        this.stepBB = stepBB;
    }
    
    @Inject
    public void setLoginBB(LoginBB loginBB) {
        this.loginBB = loginBB;
    }
    
    public String updateIngredient() {
        if(ingredientBB != null && !(ingredientBB.getName() == null || ingredientBB.getQuantity() == null) && !(ingredientBB.getName().equals("") || ingredientBB.getQuantity().equals(""))) {
            IngredientBB ibb = new IngredientBB();
            ibb.setName(ingredientBB.getName());
            ibb.setQuantity(ingredientBB.getQuantity());

            List<IngredientBB> libb = drinkBB.getIngredients();
            libb.add(ibb);
            drinkBB.setIngredients(libb);
            ingredientBB.setName("");
            ingredientBB.setQuantity("");
        }
        return null;
    }
    
    public String removeIngredient(IngredientBB ingBB) {
        drinkBB.removeIngredient(ingBB);
        return null;
    }
    
    public String updateType() {
        if(typeBB != null && typeBB.getName() != null && !typeBB.getName().equals("")) {
            TypeBB t = new TypeBB();
            t.setName(typeBB.getName());

            List<TypeBB> lt = drinkBB.getTypes();
            lt.add(t);
            drinkBB.setTypes(lt);
            typeBB.setName("");
        }
        return null;
    }
    
    public String updateStep() {
        if(stepBB != null && stepBB.getName() != null && !stepBB.getName().equals("")) {
            StepBB sbb = new StepBB();
            sbb.setName(stepBB.getName());
            List<StepBB> sbbList = drinkBB.getSteps();
            sbbList.add(sbb);
            drinkBB.setSteps(sbbList);
            stepBB.setName("");
        }
        return null;
    }
    
    public String finish() {
        
        IDrinkBook drinkBook = bar.getDrinkBook();
        String username = loginBB.getUsername();
        String name = drinkBB.getDrinkname();
        User user = bar.getUserBook().findByName(username);
        FacesContext context = FacesContext.getCurrentInstance();
       
        
        Drink oldDrink = drinkBook.findByUserAndDrinkname(user, name);
        if(oldDrink != null){
            updateIngredient();
            updateType();
            updateStep();


            IIngredientBook ingredientBook = bar.getIngredientBook();
            ITypeBook typeBook = bar.getTypeBook();
            IDrinkIngredientBook drinkIngredientBook = bar.getDrinkIngredientBook();
            IStepBook stepBook = bar.getStepBook();

            String comment = drinkBB.getComment();

            List<IngredientBB> libb = drinkBB.getIngredients();
            List<StepBB> lsbb = drinkBB.getSteps();
            List<TypeBB> ltbb = drinkBB.getTypes();

            List<DrinkIngredient> dri = new ArrayList();
            List<Step> sti = new ArrayList();
            List<Type> tyi = new ArrayList();
            List<Ingredient> ingredientList = new ArrayList();

            // Loop over all ingredients. Create ingredients and add them to a list.
            for(IngredientBB i : libb) {
                Ingredient ingredient = new Ingredient(i.getName());
                if(ingredientBook.findByName(i.getName()) == null) {
                    ingredientBook.create(ingredient);
                }
                ingredientList.add(ingredient);
            }

            // Loop over all types. Create types and add them to a list.
            for(TypeBB tb : ltbb) {
                Type t = new Type(tb.getName());
                if(typeBook.findByName(tb.getName()) == null) {
                    typeBook.create(t);
                }
                tyi.add(t);
            }

            drinkIngredientBook.deleteDrinkIngredientByDrink(oldDrink);
            for(int i = 0; i < ingredientList.size(); i++) {
                DrinkIngredient di = new DrinkIngredient(oldDrink, ingredientList.get(i), libb.get(i).getQuantity());
                drinkIngredientBook.create(di);
                dri.add(di);
            }

            stepBook.deleteAllByDrink(oldDrink);
            for(int i = 0; i < lsbb.size(); i++) {
                Step s = new Step(lsbb.get(i).getName(), i, oldDrink);
                stepBook.create(s);
                sti.add(s);
            }

            Drink drink2 = new Drink(oldDrink.getName(), oldDrink.getUser(), dri, sti, tyi, comment);
            drinkBook.update(drink2);
            return "myDrinks";
        }
        else {
            context.addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "You have already created a drink called '"
                    + name
                    + "'!  ",
                    "Please choose a different drinkname.")
            );
            return null;
        }
    }
    
    public void init(String drinkname) {
        IDrinkBook idb = bar.getDrinkBook();
        User user = bar.getUserBook().findByName(loginBB.getUsername());
        Drink d = idb.findByUserAndDrinkname(user, drinkname);
        List<DrinkIngredient> drinkIngredients = d.getIngredients();
        List<IngredientBB> libb = new ArrayList();
        for(DrinkIngredient di : drinkIngredients) {
            IngredientBB ibb = new IngredientBB();
            ibb.setName(di.getIngredient().getName());
            ibb.setQuantity(di.getQuantity());
            libb.add(ibb);
        }
        List<StepBB> lsbb = new ArrayList();
        for(Step s : d.getSteps()) {
            StepBB sbb = new StepBB();
            sbb.setName(s.getDescription());
            lsbb.add(sbb);
        }
        List<TypeBB> ltbb = new ArrayList();
        for(Type t : d.getTypes()) {
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
