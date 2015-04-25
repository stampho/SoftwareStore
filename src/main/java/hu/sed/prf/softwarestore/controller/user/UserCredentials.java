package hu.sed.prf.softwarestore.controller.user;

import hu.sed.prf.softwarestore.entity.user.Role;
import hu.sed.prf.softwarestore.entity.user.User;

import java.util.Date;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@RequestScoped
@Named
public class UserCredentials {
	private String originalUsername;

	private String username;
	private String password;
	private String confirmPassword;
	private String email;
	private String realname;
	private Role role;
	private Date registrationDate;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public String getOriginalUsername() {
		return originalUsername;
	}

	public void setOriginalUsername(String originalUsername) {
		this.originalUsername = originalUsername;
	}

	public String fill(User user) {
		if (user == null)
			return "/index.xhtml?faces-redirect=true";

		this.setOriginalUsername(user.getName());

		this.setUsername(user.getName());
		this.setEmail(user.getEmail());
		this.setRealname(user.getRealName());
		this.setRole(user.getRole());
		this.setRegistrationDate(user.getRegistrationDate());

		return "";
	}

	public void reset() {
		this.setOriginalUsername(null);

		this.setUsername("");
		this.setEmail("");
		this.setRealname("");
		this.setRole(Role.CUSTOMER);
	}

}
