/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.DrinkApp.ctrl;

import com.DrinkApp.auth.AuthDAO;
import com.DrinkApp.auth.User;
import com.DrinkApp.bb.LoginBB;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author martinkastebo
 */
@Named
@RequestScoped
public class LoginCtrl {
    
    private static final Logger LOG = Logger.getLogger(LoginCtrl.class.getSimpleName());
    
    @Inject
    private AuthDAO authDAO;
    private LoginBB lb;
    
    @Inject
    protected void setLoginBB(LoginBB lb){
        this.lb = lb;
    }
    
    public String login() {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        LOG.log(Level.INFO, "*** Try login {0} {1}", new Object[]{lb.getUsername(), lb.getPassword()});
        
        // Really check is there some data in database?
        User u =  authDAO.find(lb.getUsername());
        LOG.log(Level.INFO, "*** Found {0} {1}", new Object[]{lb.getUsername(), lb.getPassword()});
        
        
        try {
            request.login(lb.getUsername(), lb.getPassword());
            LOG.log(Level.INFO, "*** Login success");
            LOG.log(Level.INFO, "*** User principal {0}", request.getUserPrincipal());
            LOG.log(Level.INFO, "*** Is role admin {0}", request.isUserInRole("admin"));
            LOG.log(Level.INFO, "*** Is role user {0}", request.isUserInRole("user"));
          
            externalContext.getSessionMap().put("user", u);  // Store User in session
            return "login-success";
        } catch (ServletException e) {
              LOG.log(Level.INFO, "*** Login fail");
              LOG.log(Level.INFO, "*** Error: " + e.getMessage());
              e.printStackTrace();
            FacesContext.getCurrentInstance().
                    addMessage(null, 
                            new FacesMessage(FacesMessage.SEVERITY_WARN,
                                    "Login Failed", null));
            // Must set this (use the Flash-scope) else message
            // wan't survive the redirect (see faces-config.xml)
            externalContext.getFlash().setKeepMessages(true);
        }
        return "login-fail";
    }
    
    public String logout() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().
                getExternalContext();
        externalContext.invalidateSession();
        LOG.log(Level.INFO, "*** Logout success");
        return "logout-success";
    }
    
}
