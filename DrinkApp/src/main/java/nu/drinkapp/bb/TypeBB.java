/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package nu.drinkapp.bb;

import nu.drinkapp.core.Type;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

@Named
@ViewScoped
public class TypeBB implements Serializable {
	
    @NotNull
    private String name;

    @PostConstruct
    public void post(){
        LOG.log(Level.INFO, "The type is alive ");
    }
    private static final Logger LOG = Logger.getLogger(RegisterBB.class.getName());
    
    public TypeBB() {
        
    }
    
    public TypeBB(Type t) {
        this.name = t.getName();
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public String getName(){
        return this.name;
    }
    
}
