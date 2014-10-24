package nu.drinkapp.ctrl;

import nu.drinkapp.auth.AuthDAO;
import nu.drinkapp.auth.User;
import nu.drinkapp.bb.BCrypt;
import nu.drinkapp.bb.LoginBB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 * A controller for login
 */

@Named
@RequestScoped
public class LoginCtrl {

    @Inject
    private AuthDAO authDAO;

    private LoginBB lb;

    @Inject
    protected void setLoginBB(LoginBB lb) {
        this.lb = lb;
    }

    public String login() {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext externalContext = context.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        try {
            User u = authDAO.find(lb.getUsername());
            if (BCrypt.checkpw(lb.getPassword(), u.getPassword())) {
                request.login(lb.getUsername(), u.getPassword());
                lb.setLoggedIn(true);
                return "login-success";
            }
        } catch (ServletException e) {
        } catch (NullPointerException e) {
            context.
                    addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_WARN,
                                    "Login Failed! Username '"
                                    + lb.getUsername()
                                    + "' does not exist.", null));
            externalContext.getFlash().setKeepMessages(true);
            return "login-fail";
        }
        context.
                addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN,
                                "Login Failed! The password specified is not correct.", null));
        externalContext.getFlash().setKeepMessages(true);
        return "login-fail";
    }

    public String logout() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().
                getExternalContext();
        externalContext.invalidateSession();
        return "logout-success";
    }
}
