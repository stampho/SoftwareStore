package hu.sed.prf.softwarestore.controller.product;

import hu.sed.prf.softwarestore.controller.core.AbstractDataModel;
import hu.sed.prf.softwarestore.dao.core.AbstractGenericDAO;
import hu.sed.prf.softwarestore.dao.product.ProductCategoryDAO;
import hu.sed.prf.softwarestore.entity.product.ProductCategory;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class ProductCategoryDataModel extends
		AbstractDataModel<ProductCategory, Long> {

	private static final long serialVersionUID = -4179721060169382541L;

	@Inject
	private ProductCategoryDAO productCategoryDAO;

	@Override
	protected AbstractGenericDAO<ProductCategory, Long> getEntityDao() {
		return productCategoryDAO;
	}

}
