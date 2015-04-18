package hu.sed.prf.softwarestore.controller.user;

import hu.sed.prf.softwarestore.entity.user.User;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@SessionScoped
@Named
public class LoggedInUser implements Serializable {

	private static final long serialVersionUID = -2796739395591527542L;

	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
