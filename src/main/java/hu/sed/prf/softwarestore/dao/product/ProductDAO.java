package hu.sed.prf.softwarestore.dao.product;

import hu.sed.prf.softwarestore.dao.core.AbstractGenericDAO;
import hu.sed.prf.softwarestore.entity.product.Product;

public class ProductDAO extends AbstractGenericDAO<Product, Long> {

	private static final long serialVersionUID = 3426261831388702594L;

	public ProductDAO() {
		super(Product.class);
	}

}
