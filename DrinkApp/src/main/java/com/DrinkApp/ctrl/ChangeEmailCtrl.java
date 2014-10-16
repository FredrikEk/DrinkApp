/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.DrinkApp.ctrl;

import com.DrinkApp.auth.AuthDAO;
import com.DrinkApp.auth.Groups;
import com.DrinkApp.auth.User;
import com.DrinkApp.bb.ChangeEmailBB;
import com.DrinkApp.bb.LoginBB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.NoResultException;

/**
 *
 * @author Michael
 */
@Named
@RequestScoped
public class ChangeEmailCtrl {

    @Inject
    private AuthDAO authDAO;

    private ChangeEmailBB ceBB;
    private LoginBB lb;

    @Inject
    protected void setChangeEmailBB(ChangeEmailBB ceBB) {
        this.ceBB = ceBB;
    }

    @Inject
    protected void setLoginBB(LoginBB lb) {
        this.lb = lb;
    }

    public String changeEmail() {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        User user = authDAO.find(lb.getUsername());
        if (!ceBB.getOldEmail().equals(user.getEmail())) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Old Email '"
                    + ceBB.getOldEmail()
                    + "' is incorrect!  ",
                    "Please enter a different email.");
            context.addMessage(null, message);
            externalContext.getFlash().setKeepMessages(true);
        } else if (existEmail()) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "New Email '"
                    + ceBB.getNewEmail()
                    + "' already exists!  ",
                    "Please enter a different email.");
            context.addMessage(null, message);
            externalContext.getFlash().setKeepMessages(true);
        } else if (!ceBB.getNewEmail().equals(ceBB.getConfirmEmail())) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Confirm Email '"
                    + ceBB.getConfirmEmail()
                    + "' doesnt match New Email!  ",
                    "Please enter a different email.");
            context.addMessage(null, message);
            externalContext.getFlash().setKeepMessages(true);
        } else {
            User updatedUser = new User(lb.getUsername(), ceBB.getNewEmail(), user.getPassword(), Groups.USER);
            authDAO.update(updatedUser);
            return "changeEmail-success";
        }
        return "changeEmail-fail";
    }

    private boolean existEmail() {
        try {
            authDAO.getEntityManager().createNamedQuery("User.findByEmail").
                    setParameter("email", ceBB.getNewEmail()).getSingleResult();
            return true;
        } catch (NoResultException nre) {
            return false;
        }
    }

}
