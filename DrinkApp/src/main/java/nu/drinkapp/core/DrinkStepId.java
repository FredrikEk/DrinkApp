/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nu.drinkapp.core;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * Help class for making the entity drink and the attribute stepNr a 
 * primary key pair 
 * 
 */

public class DrinkStepId implements Serializable{
    
    DrinkUser drink;
    int stepNr;
    
    @Override
    public int hashCode() {
        return Integer.parseInt(stepNr + "" + drink.hashCode());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DrinkStepId other = (DrinkStepId) obj;
        if (!Objects.equals(this.drink, other.drink)) {
            return false;
        }
        if (this.stepNr != other.stepNr) {
            return false;
        }
        return true;
    }
}
