package hu.sed.prf.softwarestore.dao.product;

import hu.sed.prf.softwarestore.dao.core.AbstractGenericDAO;
import hu.sed.prf.softwarestore.entity.product.ProductCategory;

public class ProductCategoryDAO extends
		AbstractGenericDAO<ProductCategory, Long> {

	private static final long serialVersionUID = 7191942913246747322L;

	public ProductCategoryDAO() {
		super(ProductCategory.class);
	}

}
