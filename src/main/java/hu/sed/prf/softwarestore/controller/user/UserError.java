package hu.sed.prf.softwarestore.controller.user;

import java.util.EnumMap;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@RequestScoped
@Named
public class UserError {
	private enum ErrorType {
		USERNAME, PASSWORD, CONFIRMPASSWORD, EMAIL;
	}

	private EnumMap<ErrorType, String> errors = new EnumMap<ErrorType, String>(
			ErrorType.class);

	public String getUsernameError() {
		return this.errors.get(ErrorType.USERNAME);
	}

	public void setUsernameError(String usernameError) {
		this.errors.put(ErrorType.USERNAME, usernameError);
	}

	public String getPasswordError() {
		return this.errors.get(ErrorType.PASSWORD);
	}

	public void setPasswordError(String passwordError) {
		this.errors.put(ErrorType.PASSWORD, passwordError);
	}

	public String getConfirmPasswordError() {
		return this.errors.get(ErrorType.CONFIRMPASSWORD);
	}

	public void setConfirmPasswordError(String confirmPasswordError) {
		this.errors.put(ErrorType.CONFIRMPASSWORD, confirmPasswordError);
	}

	public String getEmailError() {
		return this.errors.get(ErrorType.EMAIL);
	}

	public void setEmailError(String emailError) {
		this.errors.put(ErrorType.EMAIL, emailError);
	}

	public boolean hasError() {
		for (ErrorType errorType : ErrorType.values()) {
			if (this.errors.get(errorType) == null)
				continue;

			if (!this.errors.get(errorType).isEmpty())
				return true;
		}

		return false;
	}

	public void reset() {
		for (ErrorType errorType : ErrorType.values())
			this.errors.put(errorType, "");
	}
}
