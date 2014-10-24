package nu.drinkapp.core;

import nu.drinkapp.auth.User;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;


@Entity @IdClass(FavouriteUserId.class)
@NamedQueries({@NamedQuery(name = "Favourite.userFavourite", query = "SELECT f FROM Favourite f WHERE f.favouriteUser = :user"),
               @NamedQuery(name = "Favourite.userAndDrink", query = "SELECT f FROM Favourite f WHERE f.drink = :drink AND f.favouriteUser = :user"),
               @NamedQuery(name = "Favourite.deleteDrinkFavourites", query = "DELETE FROM Favourite f WHERE f.drink = :drink"),
               @NamedQuery(name = "Favourite.deleteByUserAndDrink", query = "DELETE FROM Favourite f WHERE f.drink = :drink AND f.favouriteUser = :user")
})
public class Favourite implements Serializable {
    
    @Id
    @ManyToOne
    private User favouriteUser;
    @Id
    @OneToOne
    private Drink drink;
    
    public Favourite() {
        favouriteUser = new User();
        drink = new Drink();
    }
    
    public Favourite(User user, Drink drink) {
        this.favouriteUser = user;
        this.drink = drink;
    }
    
    public User getUser() {
        return this.favouriteUser;
    }
    
    public Drink getDrink() {
        return this.drink;
    }
    
    @Override
    public String toString() {
        return "Favourite(username=" + favouriteUser.getUsername() + ", drinkname=" + drink.getName() + ")";
    } 
}
