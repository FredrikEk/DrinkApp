/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.DrinkApp.ctrl;

import com.DrinkApp.auth.AuthDAO;
import com.DrinkApp.auth.Groups;
import com.DrinkApp.auth.User;
import com.DrinkApp.bb.BCrypt;
import com.DrinkApp.bb.ChangePasswBB;
import com.DrinkApp.bb.LoginBB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class ChangePasswCtrl {

    @Inject
    private AuthDAO authDAO;
    private ChangePasswBB cpBB;
    private LoginBB liBB;

    @Inject
    protected void setChangePasswBB(ChangePasswBB cpBB) {
        this.cpBB = cpBB;
    }

    @Inject
    protected void setLoginBB(LoginBB liBB) {
        this.liBB = liBB;
    }

    public String changeP() {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        User user = authDAO.find(liBB.getUsername());
        if (!existPassword()) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Old Password Incorrect'",
                    "Please Type Again.'");
            context.addMessage(null, message);
            externalContext.getFlash().setKeepMessages(true);
        } else if (!cpBB.getNewPassword().equals(cpBB.getConfirmPassword())) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Confirm New Password Again'",
                    "Please Type Again.'");
            context.addMessage(null, message);
            externalContext.getFlash().setKeepMessages(true);
        } else {
            User updateUser = new User(liBB.getUsername(), user.getEmail(), cpBB.getHashedPassword(), Groups.USER);
            authDAO.update(updateUser);
            return "passwUpdated";
        }
        return "changePassw-fail";
    }

    private boolean existPassword() {
        User user = authDAO.find(liBB.getUsername());
        return BCrypt.checkpw(cpBB.getPassword(), user.getPassword());
    }
}
