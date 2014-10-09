package com.DrinkApp.auth;


import java.io.Serializable;

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


@Named
@RequestScoped
public class AuthBean implements Serializable {

    private static final Logger LOG = Logger.getLogger(AuthBean.class.getSimpleName());
    private static final long serialVersionUID = 1L;

    private String id;
    private String password;
   

    @Inject // Bad use setter or constructor injection
    private AuthDAO authDAO;

    public String login() {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        LOG.log(Level.INFO, "*** Try login {0} {1}", new Object[]{id, password});
        
        // Really check is there some data in database?
        User u =  authDAO.find("qqq");
        LOG.log(Level.INFO, "*** Found {0} {1}", new Object[]{u.getUsername(), u.getPasswd()});
        
        
        try {
            //request.setCharacterEncoding("UTF-8");
            request.login(id, password);
            LOG.log(Level.INFO, "*** Login success");
            LOG.log(Level.INFO, "*** User principal {0}", request.getUserPrincipal());
            LOG.log(Level.INFO, "*** Is role admin {0}", request.isUserInRole("admin"));
            LOG.log(Level.INFO, "*** Is role user {0}", request.isUserInRole("user"));
          
            externalContext.getSessionMap().put("user", u);  // Store User in session
            return "success";
        } catch (ServletException e) {
              LOG.log(Level.INFO, "*** Login fail");
            
            FacesContext.getCurrentInstance().
                    addMessage(null, 
                            new FacesMessage(FacesMessage.SEVERITY_WARN,
                                    "Login Failed", null));
            // Must set this (use the Flash-scope) else message
            // wan't survive the redirect (see faces-config.xml)
            externalContext.getFlash().setKeepMessages(true);
          
        }
        return "fail";
    }
    
    public String logout() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().
                getExternalContext();
        externalContext.invalidateSession();
        LOG.log(Level.INFO, "*** Logout success");
        return "success";
    }

    // ------------------------------
    // Getters & Setters 
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
     
    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
