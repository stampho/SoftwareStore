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
	private UserDAO userDAO;

	private String currentUserName;

	public String login() {
		String userName = credentials.getUserName();
		String password = credentials.getPassword();

		if (userName.isEmpty() || password.isEmpty())
			return "";

		String passwordForUser = userDAO.getPasswordForUserName(userName);
		if (passwordForUser == null)
			return "";

		if (!password.equals(passwordForUser))
			return "";

		this.setCurrentUserName(userName);
		return "/index.xhtml?faces-redirect=true";
	}

	public String getCurrentUserName() {
		return currentUserName;
	}

	public void setCurrentUserName(String currentUserName) {
		this.currentUserName = currentUserName;
	}

}
