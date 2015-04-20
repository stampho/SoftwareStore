package hu.sed.prf.softwarestore.controller.user;

import hu.sed.prf.softwarestore.dao.user.UserDAO;
import hu.sed.prf.softwarestore.entity.user.User;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@ViewScoped
@Named
public class UserAction implements Serializable {

	private static final long serialVersionUID = -8965303746358062679L;

	@Inject
	private UserCredentials credentials;

	@Inject
	private UserError error;

	@Inject
	private UserDAO userDAO;

	@Inject
	private LoggedInUser loggedInUser;

	public String login() {
		String username = credentials.getUsername();
		String password = credentials.getPassword();

		User currentUser = userDAO.getUserByName(username);

		error.reset();

		if (username.isEmpty())
			error.setUsernameError("Username is missing");
		else if (currentUser == null)
			error.setUsernameError("User '" + username + "' does not exist");

		if (password.isEmpty())
			error.setPasswordError("Password is missing");
		else if (currentUser != null
				&& !password.equals(currentUser.getPassword()))
			error.setPasswordError("Invalid Password");

		if (error.hasError())
			return "";

		this.loggedInUser.setUser(currentUser);
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

		String patternUserNamePassword = "^(?=.{6})(?!.{11})(?=.*[a-zA-Z0-9])(?=.*[a-z]){1,}(?=.*[A-Z]){1,}(?=.*\\d){1,}[a-zA-Z\\d]+$";
		String patternEmail = "^[\\w\\.-]+@[\\w\\.-]+\\.[\\w]{2,3}$";
		String patternRealName = "^(?!.{31})[a-zA-Z ]*$";

		if (!username.matches(patternUserNamePassword) || username.isEmpty())
			error.setUsernameError("Username should contain min 6 characters,  max 10 characters, at least 1 lowercase letter, 1 uppercase letter and 1 number.");
		else if (userDAO.getUserByName(username) != null)
			error.setUsernameError("Username is already in use");

		if (!email.matches(patternEmail) || email.isEmpty())
			error.setEmailError("Email is invalid or missing");
		else if (userDAO.getUserByEmail(email) != null)
			error.setEmailError("Email is already in use");

		if (!password.matches(patternUserNamePassword) || password.isEmpty())
			error.setPasswordError("Password should contain min 6 characters,  max 10 characters, at least 1 lowercase letter, 1 uppercase letter and 1 number.");

		if (!confirmPassword.equals(password))
			error.setConfirmPasswordError("Confirm password does not match");

		if (!realname.matches(patternRealName))
			error.setRealNameError("Real Name is too long or incorret.");

		if (error.hasError())
			return "";

		User user = new User(username, email, realname, password);
		userDAO.saveOrUpdate(user);
		userDAO.flush();

		error.reset();
		return "/index.xhtml?faces-redirect=true";
	}

	public String logout() {
		this.loggedInUser.setUser(null);
		error.reset();
		return "/index.xhtml?faces-redirect=true";
	}

	// TODO(pvarga): Move this to UserCredentials.java
	public String fillCredentials(User user) {
		if (user == null)
			return "/index.xhtml?faces-redirect=true";

		System.err.println("!!! FILL: " + user.getName());

		if (error.hasError())
			return "";

		credentials.setUsername(user.getName());
		credentials.setEmail(user.getEmail());
		credentials.setRealname(user.getRealName());

		return "";
	}

	public String save() {
		User currentUser = this.loggedInUser.getUser();

		if (currentUser == null)
			return "/index.xhtml?faces-redirect=true";

		error.reset();

		String username = credentials.getUsername();
		String email = credentials.getEmail();
		String realname = credentials.getRealname();
		String password = credentials.getPassword();
		String confirmPassword = credentials.getConfirmPassword();

		boolean isUsernameChanged = !username.isEmpty()
				&& !username.equals(currentUser.getName());
		boolean isEmailChanged = !email.isEmpty()
				&& !email.equals(currentUser.getEmail());
		boolean isRealnameChanged = !realname.isEmpty()
				&& !realname.equals(currentUser.getRealName());
		boolean isPasswordChanged = !password.isEmpty()
				|| !confirmPassword.isEmpty();

		if (isUsernameChanged && userDAO.getUserByName(username) != null)
			error.setUsernameError("Username is already in use");

		// TODO(pvarga): Check valid email
		if (isEmailChanged && userDAO.getUserByEmail(email) != null)
			error.setEmailError("Email is already in use");

		// TODO(pvarga): Check valid password
		if (isPasswordChanged && !confirmPassword.equals(password))
			error.setConfirmPasswordError("Confirm password does not match");

		if (error.hasError())
			return "";

		// TODO(pvarga): Ask password to perform the change

		if (isUsernameChanged)
			currentUser.setName(username);

		if (isEmailChanged)
			currentUser.setEmail(email);

		if (isRealnameChanged)
			currentUser.setRealName(realname);

		if (isPasswordChanged)
			currentUser.setPassword(password);

		if (isUsernameChanged || isEmailChanged || isRealnameChanged
				|| isPasswordChanged) {
			userDAO.update(currentUser);
			userDAO.flush();

			this.loggedInUser.setUser(currentUser);

			// TODO(pvarga): Print message when update was successful
		}

		return "";
	}

	// TODO(pvarga): Implement this!
	public String delete() {
		return "";
	}

}
