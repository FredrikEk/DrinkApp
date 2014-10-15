/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.DrinkApp.bb;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

@Named
@ViewScoped
public class TypeBB {
	
    @NotNull
    private String name;

    @PostConstruct
    public void post(){
        LOG.log(Level.INFO, "The type is alive ");
    }
    private static final Logger LOG = Logger.getLogger(RegisterBB.class.getName());
    
    public void setName(String name){
        this.name = name;
    }
    
    public String getName(){
        return this.name;
    }
    
}
