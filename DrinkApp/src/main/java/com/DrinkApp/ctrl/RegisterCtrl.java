/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.DrinkApp.ctrl;

import com.DrinkApp.auth.AuthDAO;
import com.DrinkApp.auth.User;
import com.DrinkApp.bb.RegisterBB;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.NoResultException;

/**
 *
 * @author martinkastebo
 */
@Named
@RequestScoped
public class RegisterCtrl {

    @Inject
    private AuthDAO authDAO;
    private RegisterBB ub;

    @Inject
    public void setUserBB(RegisterBB ub) {
        this.ub = ub;
    }

    public void register() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (existUsername()) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Username '"
                    + ub.getUsername()
                    + "' already exists!  ",
                    "Please choose a different username.");
            context.addMessage(null, message);
        }
        if (existEmail()) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Email '"
                    + ub.getUsername()
                    + "' already exists!  ",
                    "Please enter a different email.");
            context.addMessage(null, message);
        } else if (!ub.getPassword().equals(ub.getRePassword())) {
            FacesMessage message = new FacesMessage("The specified passwords do not match.  Please try again");
            context.addMessage(null, message);
        } else {
            User user = new User(ub.getUsername(), ub.getEmail(), ub.getPassword());
            try {
                authDAO.create(user);
            } catch (Exception e) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Error creating user!",
                        "Unexpected error when creating your account.  Please contact the system Administrator");
                context.addMessage(null, message);
                Logger.getAnonymousLogger().log(Level.SEVERE,
                        "Unable to create new user",
                        e);
            }
        }
    }
    /*
     * Help methods to check if a user with the same username and/or email already exists.
     */

    private boolean existUsername() {
        try {
            authDAO.getEntityManager().createNamedQuery("User.findByUsername").
                    setParameter("username", ub.getUsername()).getSingleResult();
            return true;
        } catch (NoResultException nre) {
            return false;
        }
    }

    private boolean existEmail() {
        try {
            authDAO.getEntityManager().createNamedQuery("User.findByEmail").
                    setParameter("email", ub.getEmail()).getSingleResult();
            return true;
        } catch (NoResultException nre) {
            return false;
        }
    }
}
