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
import nu.drinkapp.bb.AddDrinkBB;
import nu.drinkapp.bb.IngredientBB;
import nu.drinkapp.bb.LoginBB;
import nu.drinkapp.bb.StepBB;
import nu.drinkapp.bb.TypeBB;
import nu.drinkapp.wrappers.IDrinkIngredientBook;
import nu.drinkapp.wrappers.IStepBook;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class AddDrinkCtrl{

    @Inject
    private Bar bar;
    private TypeBB typeBB;
    private StepBB stepBB;
    private IngredientBB ingredientBB;
    private AddDrinkBB drinkBB;
    private LoginBB loginBB;
    private static final Logger LOG = Logger.getLogger(AddDrinkCtrl.class.getName());
    
    protected AddDrinkCtrl() {
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
    public void setDrinkBB(AddDrinkBB drinkBB) {
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
        if(ingredientBB != null && !(ingredientBB.getName().equals("") || ingredientBB.getQuantity().equals(""))) {
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
        if(typeBB != null && !typeBB.getName().equals("")) {
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
        if(stepBB != null && !stepBB.getName().equals("")) {
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
       
        
        if(drinkBook.findByUserAndDrinkname(user, name) == null){
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
            // Create a drink without ingredients and steps.
            Drink drink = new Drink(name, user, dri, sti, tyi, comment);
            drinkBook.create(drink);

            for(int i = 0; i < ingredientList.size(); i++) {
                DrinkIngredient di = new DrinkIngredient(drink, ingredientList.get(i), libb.get(i).getQuantity());
                drinkIngredientBook.create(di);
                dri.add(di);
            }

            for(int i = 0; i < lsbb.size(); i++) {
                Step s = new Step(lsbb.get(i).getName(), i, drink);
                stepBook.create(s);
                sti.add(s);
            }

            Drink drink2 = new Drink(drink.getName(), drink.getUser(), dri, sti, drink.getTypes(), comment);
            drinkBook.update(drink2);
            return "chosenDrink";
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
}
