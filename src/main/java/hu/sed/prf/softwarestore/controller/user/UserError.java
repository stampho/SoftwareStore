package hu.sed.prf.softwarestore.controller.user;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@RequestScoped
@Named
public class UserError {
	private String usernameError;
	private String passwordError;

	public String getUsernameError() {
		return usernameError;
	}

	public void setUsernameError(String usernameError) {
		this.usernameError = usernameError;
	}

	public String getPasswordError() {
		return passwordError;
	}

	public void setPasswordError(String passwordError) {
		this.passwordError = passwordError;
	}

	public boolean hasError() {
		if (this.usernameError != null)
			return true;
		if (this.passwordError != null)
			return true;

		return false;
	}

	public void reset() {
		this.usernameError = null;
		this.passwordError = null;
	}
}
