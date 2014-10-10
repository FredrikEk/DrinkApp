/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.DrinkApp.Core;

import com.DrinkApp.auth.User;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Fredrik
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
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DrinkUser other = (DrinkUser) obj;
        if (!Objects.equals(this.user, other.user)) {
            return false;
        }
        if (!Objects.equals(this.drinkname, other.drinkname)) {
            return false;
        }
        return true;
    }
}
