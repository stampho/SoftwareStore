package hu.sed.prf.softwarestore.controller.product;

import hu.sed.prf.softwarestore.controller.core.AbstractEntityAction;
import hu.sed.prf.softwarestore.dao.core.AbstractGenericDAO;
import hu.sed.prf.softwarestore.dao.product.ProductCategoryDAO;
import hu.sed.prf.softwarestore.dao.product.ProductDAO;
import hu.sed.prf.softwarestore.entity.product.Product;
import hu.sed.prf.softwarestore.entity.product.ProductCategory;

import java.util.logging.Logger;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class ProductAction extends AbstractEntityAction<Product, Long> {

	private static final long serialVersionUID = -4322435122199549561L;

	@Inject
	ProductDAO productDAO;

	@Inject
	ProductCategoryDAO productCategoryDAO;

	@Inject
	Logger logger;

	public ProductAction() {
		super(Product.class);
	}

	@Override
	protected AbstractGenericDAO<Product, Long> getEntityDao() {
		return productDAO;
	}

	@Override
	protected String getNavigationTargetAfterPersist() {
		return "";
	}

	public Long getCategoryId() {
		Product product = (Product) getEntity();
		if (product == null) {
			logger.warning("Product is null");
			return -1L;
		}

		ProductCategory category = product.getCategory();
		if (category == null) {
			logger.warning("Category is null");
			return -1L;
		}

		return category.getId();
	}

	public void setCategoryId(Long categoryId) {
		ProductCategory category = productCategoryDAO.findEntity(categoryId);
		getEntity().setCategory(category);
	}

}