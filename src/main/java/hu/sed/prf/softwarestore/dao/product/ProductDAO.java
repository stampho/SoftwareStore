package hu.sed.prf.softwarestore.dao.product;

import hu.sed.prf.softwarestore.dao.core.AbstractGenericDAO;
import hu.sed.prf.softwarestore.entity.product.Product;
import hu.sed.prf.softwarestore.entity.product.ProductCategory;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

public class ProductDAO extends AbstractGenericDAO<Product, Long> {

	private static final long serialVersionUID = 3426261831388702594L;

	public ProductDAO() {
		super(Product.class);
	}

	@SuppressWarnings("unchecked")
	public List<Product> findByCategoryAndFilterText(ProductCategory category, String filterText) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("category", category));
		
		if(filterText!= null && !"".equals(filterText)) {
			criteria.add(Restrictions.or(
				Restrictions.ilike("name", "%" + filterText + "%"),
				Restrictions.ilike("version", "%" + filterText + "%"),
				Restrictions.ilike("company", "%" + filterText + "%"),
				Restrictions.ilike("description", "%" + filterText + "%")));

		}
		return criteria.list();
	}
}
