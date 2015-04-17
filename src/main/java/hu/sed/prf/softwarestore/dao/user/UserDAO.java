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

	public User getUserByName(String name) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("name", name));
		return (User) criteria.uniqueResult();
	}

	public User getUserByEmail(String email) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("email", email));
		return (User) criteria.uniqueResult();
	}

}
