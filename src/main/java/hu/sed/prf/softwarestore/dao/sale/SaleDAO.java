package hu.sed.prf.softwarestore.dao.sale;

import hu.sed.prf.softwarestore.dao.core.AbstractGenericDAO;
import hu.sed.prf.softwarestore.entity.sale.Sale;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

public class SaleDAO extends AbstractGenericDAO<Sale, Long> {

	private static final long serialVersionUID = -5345773105995190878L;

	public SaleDAO() {
		super(Sale.class);
	}

	@SuppressWarnings("unchecked")
	public List<Sale> getSalesByUsername(String username) {
		Criteria saleCriteria = getSession().createCriteria(getPersistentClass());
		Criteria userCriteria = saleCriteria.createCriteria("user").add(Restrictions.eq("name", username));
		return userCriteria.list();
	}

}
