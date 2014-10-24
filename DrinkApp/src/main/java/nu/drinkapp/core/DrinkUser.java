package nu.drinkapp.core;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * Help class for making the entity User and the attribute drinkname 
 * a primary key pair
 * 
 */

public class DrinkUser implements Serializable{
    
    String user;
    String drinkname;
    
    @Override
    public int hashCode() {
        return Integer.parseInt(drinkname.hashCode() + "" + user.hashCode());
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) {
            return false;
        }
        if(getClass() != obj.getClass()) {
            return false;
        }
        final DrinkUser other = (DrinkUser) obj;
        if (!Objects.equals(this.user, other.user)) {
            return false;
        }
        if(!Objects.equals(this.drinkname, other.drinkname)) {
            return false;
        }
        return true;
    }
}
