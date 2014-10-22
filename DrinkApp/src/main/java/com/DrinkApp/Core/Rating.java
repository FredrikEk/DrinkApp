/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.DrinkApp.Core;

import com.DrinkApp.auth.User;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

@Entity @IdClass(RatingUserId.class)
@NamedQueries({@NamedQuery(name = "Rating.averageRating", query = "SELECT SUM(r.rating),count(r) FROM Rating r WHERE r.drink = :drink"),
               @NamedQuery(name = "Rating.userRating", query = "SELECT d FROM Drink d WHERE d.user = :user"),
               @NamedQuery(name = "Rating.userAndDrink", query = "SELECT r FROM Rating r WHERE r.drink = :drink AND r.ratingUser = :user"),
               @NamedQuery(name = "Rating.deleteDrinkRatings", query = "DELETE FROM Rating r WHERE r.drink = :drink")
})
public class Rating implements Serializable {
    
    @Id
    @ManyToOne
    private User ratingUser;
    @Id
    @OneToOne
    private Drink drink;
    
    private int rating;
    
    public Rating() {
        ratingUser = new User();
        drink = new Drink();
        rating = 0;
    }
    
    public Rating(User user, Drink drink, int rating) {
        this.ratingUser = user;
        this.drink = drink;
        this.rating = rating;
    }
    
    public User getUser() {
        return this.ratingUser;
    }
    
    public Drink getDrink() {
        return this.drink;
    }
    
    public int getRating() {
        return this.rating;
    }
    
    @Override
    public String toString() {
        return "Rating(username=" + ratingUser.getUsername() + ", drinkname=" + drink.getName() + ")";
    }
    
}
