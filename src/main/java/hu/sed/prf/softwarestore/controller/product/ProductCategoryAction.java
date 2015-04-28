package hu.sed.prf.softwarestore.controller.product;

import hu.sed.prf.softwarestore.controller.core.AbstractEntityAction;
import hu.sed.prf.softwarestore.dao.core.AbstractGenericDAO;
import hu.sed.prf.softwarestore.dao.product.ProductCategoryDAO;
import hu.sed.prf.softwarestore.dao.product.ProductDAO;
import hu.sed.prf.softwarestore.entity.product.Product;
import hu.sed.prf.softwarestore.entity.product.ProductCategory;

import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class ProductCategoryAction extends
		AbstractEntityAction<ProductCategory, Long> {

	private static final long serialVersionUID = 4818649988299908134L;

	@Inject
	ProductCategoryDAO productCategoryDAO;

	@Inject
	ProductDAO productDAO;

	@Inject
	ProductAction productAction;

	public ProductCategoryAction() {
		super(ProductCategory.class);
	}

	@Override
	protected AbstractGenericDAO<ProductCategory, Long> getEntityDao() {
		return productCategoryDAO;
	}

	@Override
	protected String getNavigationTargetAfterPersist() {
		return "";
	}

	@Override
	protected void beforeDeletion(ProductCategory entityToDelete) {
		List<Product> productList = productDAO.findByCategoryAndFilterText(entityToDelete, "");
		for (Product product : productList)
			productAction.delete(product.getId());
	}

}
