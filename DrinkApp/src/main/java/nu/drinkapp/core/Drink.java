package nu.drinkapp.core;

import nu.drinkapp.auth.User;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/*
 * The Drink model. Stores drinks in the database with a user
 * who submitted the drink, a name, a list of ingredients and a 
 * list of steps of how to make the drink. 
 */

@Entity @IdClass(DrinkUser.class)
@NamedQueries( {@NamedQuery(name = "Drink.findByUserAndDrinkname", query = "SELECT d FROM Drink d WHERE d.user = :username AND d.drinkname = :drinkname"),
                @NamedQuery(name = "Drink.findByName", query = "SELECT d FROM Drink d WHERE d.drinkname = :drinkname"),
                @NamedQuery(name = "Drink.searchByName", query = "SELECT d FROM Drink d WHERE d.drinkname LIKE :drinkname"),
                @NamedQuery(name = "Drink.searchByUser", query = "SELECT d FROM Drink d WHERE d.user = :user"),
                @NamedQuery(name = "Drink.deleteDrink", query = "DELETE FROM Drink d WHERE d.drinkname = :drinkname AND d.user = :user")
})
public class Drink implements Serializable {
    
    @ManyToOne
    @Id
    private User user;
    @Column
    @Id
    private String drinkname;
    @OneToMany(mappedBy = "drink")
    private List<DrinkIngredient> ingredients;
    @OneToMany(mappedBy = "drink")
    private List<Step> steps;
    @ManyToMany
    private List<Type> types;
    @Column
    private String comment;
    
    
    public Drink() {
        this.user        = new User();
        this.drinkname   = null;
        this.ingredients = new ArrayList();
        this.steps       = new ArrayList();
        this.types       = new ArrayList();
        this.comment     = null;
    }
    
    public Drink(String name, User user, List<DrinkIngredient> ingredients, List<Step> steps, List<Type> types, String comment) {
        this.drinkname   = name;
        this.user        = user;
        this.ingredients = ingredients;
        this.steps       = steps;
        this.types       = types;
        this.comment     = comment;
        }
    
    public User getUser() {
        return user;
    }

    public String getName() {
        return drinkname;
    }

    public List<DrinkIngredient> getIngredients() {
        return ingredients;
    }
    
    public List<Step> getSteps() {
		return this.steps;
    }
    
    public List<Type> getTypes() {
        return types;
    }
    
    public String getComment() {
        return comment;
    }
    
    @Override
    public String toString() {
        return "Drink{ name=" + drinkname + "user: " + user.getUsername() + '}';
    }
}
