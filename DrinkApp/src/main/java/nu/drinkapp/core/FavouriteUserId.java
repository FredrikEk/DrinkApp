package nu.drinkapp.core;

import java.io.Serializable;
import java.util.Objects;

public class FavouriteUserId implements Serializable{
    
    String favouriteUser;
    DrinkUser drink;    
    
    @Override
    public int hashCode() {
        return Integer.parseInt(favouriteUser + "" + drink.hashCode());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FavouriteUserId other = (FavouriteUserId) obj;
        if (!Objects.equals(this.favouriteUser, other.favouriteUser)) {
            return false;
        }
        if (!Objects.equals(this.drink, other.drink)) {
            return false;
        }
        return true;
    }  
}
