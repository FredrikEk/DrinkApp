package nu.drinkapp.ctrl;

import nu.drinkapp.auth.AuthDAO;
import nu.drinkapp.auth.Groups;
import nu.drinkapp.auth.SanitizedInput;
import nu.drinkapp.auth.User;
import nu.drinkapp.bb.ChangeEmailBB;
import nu.drinkapp.bb.LoginBB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.persistence.NoResultException;

/**
 * A controller for changing email
 */

@Named
@RequestScoped
public class ChangeEmailCtrl {

    @Inject
    private AuthDAO authDAO;
    private ChangeEmailBB ceBB;
    private LoginBB lb;
    private InternetAddress emailAddress;

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
        } else if (!SanitizedInput.sanitizeInput(ceBB.getOldEmail())) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Email: '"
                    + ceBB.getOldEmail()
                    + "' contains illegal characters!  ",
                    "Please choose a different Email.");
            context.addMessage(null, message);
            externalContext.getFlash().setKeepMessages(true);
        } else if (!SanitizedInput.sanitizeInput(ceBB.getNewEmail())) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Email: '"
                    + ceBB.getNewEmail()
                    + "' contains illegal characters!  ",
                    "Please choose a different Email.");
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
            try {
                emailAddress = new InternetAddress(ceBB.getNewEmail());
                emailAddress.validate();
                User updatedUser = new User(lb.getUsername(), ceBB.getNewEmail(), user.getPassword(), Groups.USER);
                authDAO.update(updatedUser);
                return "changeEmail-success";
            } catch (AddressException e) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "New Email  '"
                        + ceBB.getNewEmail()
                        + "' is not a valid Email!  ",
                        "Please type correct Email.");
                context.addMessage(null, message);
                externalContext.getFlash().setKeepMessages(true);
            }
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
