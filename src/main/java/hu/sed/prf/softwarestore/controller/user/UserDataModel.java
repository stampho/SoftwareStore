package hu.sed.prf.softwarestore.controller.user;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import hu.sed.prf.softwarestore.controller.core.AbstractDataModel;
import hu.sed.prf.softwarestore.dao.core.AbstractGenericDAO;
import hu.sed.prf.softwarestore.dao.user.UserDAO;
import hu.sed.prf.softwarestore.entity.user.User;

@Named
@ViewScoped
public class UserDataModel extends AbstractDataModel<User, Long> {

	private static final long serialVersionUID = 599328296781344887L;

	@Inject
	private UserDAO userDAO;

	@Override
	protected AbstractGenericDAO<User, Long> getEntityDao() {
		return userDAO;
	}

}