package hu.sed.prf.softwarestore.dao.user;
import hu.sed.prf.softwarestore.dao.core.AbstractGenericDAO;
import hu.sed.prf.softwarestore.entity.user.User;


public class UserDAO extends AbstractGenericDAO<User, Long> {

	private static final long serialVersionUID = -1653672994764463292L;

	public UserDAO() {
		super(User.class);
	}

}

