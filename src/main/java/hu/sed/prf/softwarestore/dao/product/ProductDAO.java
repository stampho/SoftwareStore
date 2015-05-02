package hu.sed.prf.softwarestore.dao.product;

import hu.sed.prf.softwarestore.dao.core.AbstractGenericDAO;
import hu.sed.prf.softwarestore.entity.product.Product;
import hu.sed.prf.softwarestore.entity.product.ProductCategory;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

public class ProductDAO extends AbstractGenericDAO<Product, Long> {

	private static final long serialVersionUID = 3426261831388702594L;

	public ProductDAO() {
		super(Product.class);
	}

	@SuppressWarnings("unchecked")
	public List<Product> findByCategoryAndFilters(ProductCategory category,
			String filterText, long minPrice, long maxPrice) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("category", category));

		if (filterText != null && !"".equals(filterText)) {
			//criteria.add(Restrictions.ilike("name", filterText, MatchMode.ANYWHERE));
			criteria.add(Restrictions.or(
					Restrictions.ilike("name", filterText, MatchMode.ANYWHERE),
					Restrictions.ilike("version", filterText, MatchMode.ANYWHERE),
					Restrictions.ilike("company",filterText, MatchMode.ANYWHERE)));
				//	Restrictions.ilike("description", filterText, MatchMode.ANYWHERE )));

		}

		if (minPrice > 0)
			criteria.add(Restrictions.gt("price", minPrice));
		if (maxPrice > 0)
			criteria.add(Restrictions.lt("price", maxPrice));

		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	public List<Product> findByCategoryAndFilterText(ProductCategory category) {
		Criteria criteria = getSession().createCriteria(getPersistentClass());
		criteria.add(Restrictions.eq("category", category));
		return criteria.list();
	}
}
