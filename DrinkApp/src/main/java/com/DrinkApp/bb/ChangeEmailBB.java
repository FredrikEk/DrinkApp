/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.DrinkApp.bb;

import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Michael
 */
@Named
@RequestScoped
public class ChangeEmailBB implements Serializable{
    
    @NotNull
    private String oldEmail;
    @NotNull
    private String newEmail;
    @NotNull
    private String confirmEmail;
    
    public void setOldEmail(String oldEmail){
        this.oldEmail = oldEmail;
    }
    
    public String getOldEmail(){
        return oldEmail;
    }
    
    public void setNewEmail(String newEmail){
        this.newEmail = newEmail;
    }
    
    public String getNewEmail(){
        return newEmail;
    }
    
    public void setConfirmEmail(String confirmEmail){
        this.confirmEmail = confirmEmail;
    }
    
    public String getConfirmEmail(){
        return confirmEmail;
    }
    
}
