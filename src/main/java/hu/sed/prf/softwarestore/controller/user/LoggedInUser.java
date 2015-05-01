package hu.sed.prf.softwarestore.controller.user;

import hu.sed.prf.softwarestore.entity.user.Role;
import hu.sed.prf.softwarestore.entity.user.User;

import java.io.IOException;
import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@SessionScoped
@Named
public class LoggedInUser implements Serializable {

	private static final long serialVersionUID = -2796739395591527542L;

	private User user;
	private String accessErrorMessage;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getAccessErrorMessage() {
		return accessErrorMessage;
	}

	public void setAccessErrorMessage(String accessErrorMessage) {
		this.accessErrorMessage = accessErrorMessage;
	}

	public boolean isAdministrator() {
		if (this.user == null)
			return false;

		return this.user.getRole() == Role.ADMINISTRATOR;
	}

	public void checkAccess() {
		FacesContext facesContext = FacesContext.getCurrentInstance();

		String viewId = facesContext.getViewRoot().getViewId();
		if (viewId.startsWith("/content/admin") && !isAdministrator()) {
			ConfigurableNavigationHandler navigationHandler = (ConfigurableNavigationHandler) facesContext.getApplication().getNavigationHandler();
			setAccessErrorMessage("<h3>Access Denied!</h3>Admin rights required");
			navigationHandler.performNavigation("/access-denied.xhtml");
			return;
		}

		if (this.user == null) {
			ConfigurableNavigationHandler navigationHandler = (ConfigurableNavigationHandler) facesContext.getApplication().getNavigationHandler();
			setAccessErrorMessage("<h3>Access Denied!</h3>You must be logged in");
			navigationHandler.performNavigation("/access-denied.xhtml");
			return;
		}

		setAccessErrorMessage(null);
	}

	public void checkAccessError() {
		if (accessErrorMessage == null || accessErrorMessage.isEmpty()) {
			ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
			try {
				externalContext.redirect("/index.xhtml");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
