package hu.sed.prf.softwarestore.controller.user;

import hu.sed.prf.softwarestore.dao.user.UserDAO;
import hu.sed.prf.softwarestore.entity.user.User;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@SessionScoped
@Named
public class UserAction implements Serializable {

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
		else if (userDAO.getUserByName(username) == null)
			error.setUsernameError("User '" + username + "' does not exist");

		if (password.isEmpty())
			error.setPasswordError("Password is missing");
		else if (!password
				.equals(userDAO.getUserByName(username).getPassword()))
			error.setPasswordError("Invalid Password");

		if (error.hasError())
			return "";

		setCurrentUserName(username);
		error.reset();
		return "/index.xhtml?faces-redirect=true";
	}

	public String signin() {
		String username = credentials.getUsername();
		String email = credentials.getEmail();
		String realname = credentials.getRealname();
		String password = credentials.getPassword();
		String confirmPassword = credentials.getConfirmPassword();

		error.reset();

		if (username.isEmpty())
			error.setUsernameError("Username is missing");
		else if (userDAO.getUserByName(username) != null)
			error.setUsernameError("Username is already used");

		if (email.isEmpty())
			error.setEmailError("Email is missing");
		else if (userDAO.getUserByEmail(email) != null)
			error.setEmailError("Email is already used");

		if (password.isEmpty())
			error.setPasswordError("Password is missing");

		if (!confirmPassword.equals(password))
			error.setConfirmPasswordError("Confirm password does not match");

		if (error.hasError())
			return "";

		User user = new User(username, email, realname, password);
		userDAO.saveOrUpdate(user);
		userDAO.flush();

		error.reset();
		return "/index.xhtml?faces-redirect=true";
	}

	public String logout() {
		setCurrentUserName(null);
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
