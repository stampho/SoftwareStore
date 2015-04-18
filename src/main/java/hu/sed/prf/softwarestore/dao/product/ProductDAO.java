package hu.sed.prf.softwarestore.dao.product;

import hu.sed.prf.softwarestore.dao.core.AbstractGenericDAO;
import hu.sed.prf.softwarestore.entity.product.Product;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

public class ProductDAO extends AbstractGenericDAO<Product, Long> {

	private static final long serialVersionUID = 3426261831388702594L;

	public ProductDAO() {
		super(Product.class);
	}

	@SuppressWarnings("unchecked")
	public List<Product> getProductsByCategoryId(Long categoryId) {
		Criteria productCriteria = getSession().createCriteria(getPersistentClass());
		Criteria categoryCriteria = productCriteria.createCriteria("category");
		categoryCriteria.add(Restrictions.eq("id", categoryId));
		return categoryCriteria.list();
	}
}
