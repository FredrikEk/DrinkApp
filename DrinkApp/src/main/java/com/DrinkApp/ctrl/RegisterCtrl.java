/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.DrinkApp.ctrl;

import com.DrinkApp.auth.AuthDAO;
import com.DrinkApp.auth.Groups;
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
    private RegisterBB rb;

    @Inject
    protected void setUserBB(RegisterBB ub) {
        this.rb = ub;
    }

    public String register() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (existUsername()) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Username '"
                    + rb.getUsername()
                    + "' already exists!  ",
                    "Please choose a different username.");
            context.addMessage(null, message);
        } else if (existEmail()) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Email '"
                    + rb.getEmail()
                    + "' already exists!  ",
                    "Please enter a different email.");
            context.addMessage(null, message);
        } else if (!rb.getPassword().equals(rb.getRePassword())) {
            FacesMessage message = new FacesMessage("The specified passwords do not match.  Please try again");
            context.addMessage(null, message);
        } else {
            User user = new User(rb.getUsername(), rb.getEmail(), rb.getHashedPassword(), Groups.USER);
            try {
                authDAO.create(user);
                return "registerSuccess";
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
        return "register"; //Returns to the register page, since it failed. Might change in faces-config to make String it more clear
    }
    /*
     * Help methods to check if a user with the same username and/or email already exists.
     */

    private boolean existUsername() {
        try {
            authDAO.getEntityManager().createNamedQuery("User.findByUsername").
                    setParameter("username", rb.getUsername()).getSingleResult();
            return true;
        } catch (NoResultException nre) {
            return false;
        }
    }

    private boolean existEmail() {
        try {
            authDAO.getEntityManager().createNamedQuery("User.findByEmail").
                    setParameter("email", rb.getEmail()).getSingleResult();
            return true;
        } catch (NoResultException nre) {
            return false;
        }
    }
}
