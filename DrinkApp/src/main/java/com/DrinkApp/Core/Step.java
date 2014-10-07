/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.DrinkApp.Core;

import com.DrinkApp.persistence.AbstractEntity;
import javax.persistence.Column;
import javax.persistence.Entity;

/*
 * The Step model. Stores the different steps of how to make a certain drink.
 */

@Entity
public class Step extends AbstractEntity {
    
    @Column
    private int stepNr;
    @Column
    private String description;
    
    public Step(){
        this.description = "";
        this.stepNr = 0;
     
    }
    
    public Step(String description, int stepNr) {
        this.description = description;
        this.stepNr = stepNr;
        }
    
    public Step(Long id, String name) {
        super(id);
        this.description = description;
        this.stepNr = stepNr;
    }
    
    public int getStepNr() {
        return stepNr;
    }
    
    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Ingredient{" + "id=" + getId() + ", stepNr=" + stepNr + ", description=" + description + '}';
    }
    
}
