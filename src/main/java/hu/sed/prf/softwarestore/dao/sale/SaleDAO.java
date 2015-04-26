package hu.sed.prf.softwarestore.dao.sale;

import hu.sed.prf.softwarestore.dao.core.AbstractGenericDAO;
import hu.sed.prf.softwarestore.entity.product.Product;
import hu.sed.prf.softwarestore.entity.sale.Sale;
import hu.sed.prf.softwarestore.entity.user.User;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

public class SaleDAO extends AbstractGenericDAO<Sale, Long> {

	private static final long serialVersionUID = -5345773105995190878L;

	public SaleDAO() {
		super(Sale.class);
	}

	@SuppressWarnings("unchecked")
	public List<Sale> findByUser(User user) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("user", user));
		return criteria.list();
	}

	public void deleteByUser(User user) {
		List<Sale> saleList = findByUser(user);
		for (Sale sale : saleList)
			delete(sale);

		flush();
	}

	@SuppressWarnings("unchecked")
	public List<Sale> findByProduct(Product product) {
		Criteria criteria = getSession().createCriteria(getPersistentClass(), "sale");
		criteria.createAlias("sale.products", "product");
		criteria.add(Restrictions.eq("product.id", product.getId()));
		return criteria.list();
	}

	public void deleteByProduct(Product product) {
		List<Sale> saleList = findByProduct(product);
		for (Sale sale : saleList)
			delete(sale);

		flush();
	}
}
