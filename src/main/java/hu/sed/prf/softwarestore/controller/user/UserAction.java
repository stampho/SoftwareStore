package hu.sed.prf.softwarestore.controller.user;

import hu.sed.prf.softwarestore.dao.user.UserDAO;
import hu.sed.prf.softwarestore.entity.user.Role;
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

	// TODO(pvarga): signups should use save()
	// TODO(pvarga): validation should go to save function
	public String signup() {
		String username = credentials.getUsername();
		String email = credentials.getEmail();
		String realname = credentials.getRealname();
		String password = credentials.getPassword();
		String confirmPassword = credentials.getConfirmPassword();

		error.reset();

		String patternPassword = "^(?=.{6})(?!.{11})(?=.*[a-zA-Z0-9])(?=.*[a-z]){1,}(?=.*[A-Z]){1,}(?=.*\\d){1,}[a-zA-Z\\d]+$";
		String patternEmail = "^[\\w\\.-]+@[\\w\\.-]+\\.[\\w]{2,3}$";
		String patternRealName = "^(?!.{31})[a-zA-Z ]*$";

		if (username.isEmpty())
			error.setUsernameError("Username is invalid or missing");
		else if (userDAO.getUserByName(username) != null)
			error.setUsernameError("Username is already in use");

		if (!email.matches(patternEmail) || email.isEmpty())
			error.setEmailError("Email is invalid or missing");
		else if (userDAO.getUserByEmail(email) != null)
			error.setEmailError("Email is already in use");

		if (!password.matches(patternPassword) || password.isEmpty())
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

	public void save(String originalUsername) {
		// Username is passed in parameter as a workaround. Primefaces dialog doesn't
		// really work with User.
		User user = this.userDAO.getUserByName(originalUsername);

		if (user == null)
			return;

		error.reset();

		String username = credentials.getUsername();
		String email = credentials.getEmail();
		String realname = credentials.getRealname();
		String password = credentials.getPassword();
		String confirmPassword = credentials.getConfirmPassword();
		Role role = credentials.getRole();

		// Empty input fields should not change the corresponding user properties
		boolean isUsernameChanged = !username.isEmpty()
				&& !username.equals(user.getName());
		boolean isEmailChanged = !email.isEmpty()
				&& !email.equals(user.getEmail());
		boolean isRealnameChanged = !realname.isEmpty()
				&& !realname.equals(user.getRealName());
		boolean isPasswordChanged = !(password == null || password.isEmpty())
				|| !(confirmPassword == null || confirmPassword.isEmpty());
		boolean isRoleChanged = role != null
				&& !role.equals(user.getRole());

		if (isUsernameChanged && userDAO.getUserByName(username) != null)
			error.setUsernameError("Username is already in use");

		// TODO(pvarga): Check valid email
		if (isEmailChanged && userDAO.getUserByEmail(email) != null)
			error.setEmailError("Email is already in use");

		// TODO(pvarga): Check valid password
		if (isPasswordChanged && !confirmPassword.equals(password))
			error.setConfirmPasswordError("Confirm password does not match");

		if (error.hasError())
			return;

		// TODO(pvarga): Ask password to perform the change

		if (isUsernameChanged)
			user.setName(username);

		if (isEmailChanged)
			user.setEmail(email);

		if (isRealnameChanged)
			user.setRealName(realname);

		if (isPasswordChanged)
			user.setPassword(password);

		if (isRoleChanged)
			user.setRole(role);

		if (isUsernameChanged || isEmailChanged || isRealnameChanged
				|| isPasswordChanged || isRoleChanged) {
			userDAO.update(user);
			userDAO.flush();

			if (this.loggedInUser != null && this.loggedInUser.equals(user))
				this.loggedInUser.setUser(user);

			// TODO(pvarga): Raise message when update was successful
		}

		return;
	}

	public void resetPassword(User user) {
		if (user == null)
			return;

		user.setPassword(user.getName());
		userDAO.update(user);
		userDAO.flush();
	}

	// TODO(pvarga): Implement this!
	public String delete(User user) {
		return "";
	}

}
