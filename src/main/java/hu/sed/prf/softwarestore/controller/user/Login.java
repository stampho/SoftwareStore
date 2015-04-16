package hu.sed.prf.softwarestore.controller.user;

import hu.sed.prf.softwarestore.dao.user.UserDAO;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@SessionScoped
@Named
public class Login implements Serializable {

	private static final long serialVersionUID = -8965303746358062679L;

	@Inject
	private UserCredentials credentials;

	@Inject
	private UserError error;

	@Inject
	private UserDAO userDAO;

	private String currentUsername;

	public String login() {
		String username = credentials.getUsername();
		String password = credentials.getPassword();

		error.reset();

		if (username.isEmpty())
			error.setUsernameError("Username is missing");

		if (password.isEmpty())
			error.setPasswordError("Password is missing");

		if (error.hasError())
			return "";

		String passwordForUser = userDAO.getPasswordForUsername(username);
		if (passwordForUser == null) {
			error.setUsernameError("User '" + username + "' does not exist");
			return "";
		}

		if (!password.equals(passwordForUser)) {
			error.setPasswordError("Invalid Password");
			return "";
		}

		setCurrentUserName(username);
		error.reset();
		return "/index.xhtml?faces-redirect=true";
	}

	public String getCurrentUserName() {
		return currentUsername;
	}

	public void setCurrentUserName(String currentUserName) {
		this.currentUsername = currentUserName;
	}

}
