package hu.sed.prf.softwarestore.dao.user;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import hu.sed.prf.softwarestore.dao.core.AbstractGenericDAO;
import hu.sed.prf.softwarestore.entity.user.User;

public class UserDAO extends AbstractGenericDAO<User, Long> {

	private static final long serialVersionUID = -1653672994764463292L;

	public UserDAO() {
		super(User.class);
	}

	public String getPasswordForUserName(String name) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("name", name));
		User user = (User) criteria.uniqueResult();
		if (user == null)
			return null;

		return user.getPassword();
	}
}
