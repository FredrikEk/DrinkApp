/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nu.drinkapp.core;

import java.io.Serializable;
import java.util.Objects;


public class RatingUserId implements Serializable{
    
    String ratingUser;
    DrinkUser drink;    
    
    @Override
    public int hashCode() {
        return Integer.parseInt(ratingUser + "" + drink.hashCode());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final RatingUserId other = (RatingUserId) obj;
        if (!Objects.equals(this.ratingUser, other.ratingUser)) {
            return false;
        }
        if (!Objects.equals(this.drink, other.drink)) {
            return false;
        }
        return true;
    }

}
