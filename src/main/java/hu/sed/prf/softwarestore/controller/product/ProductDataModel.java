package hu.sed.prf.softwarestore.controller.product;

import hu.sed.prf.softwarestore.controller.core.AbstractDataModel;
import hu.sed.prf.softwarestore.dao.core.AbstractGenericDAO;
import hu.sed.prf.softwarestore.dao.product.ProductCategoryDAO;
import hu.sed.prf.softwarestore.dao.product.ProductDAO;
import hu.sed.prf.softwarestore.dao.sale.SaleDAO;
import hu.sed.prf.softwarestore.entity.product.Product;
import hu.sed.prf.softwarestore.entity.product.ProductCategory;
import hu.sed.prf.softwarestore.entity.sale.Sale;
import hu.sed.prf.softwarestore.entity.user.User;

import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class ProductDataModel extends AbstractDataModel<Product, Long> {

	private static final long serialVersionUID = -908257778137796529L;

	@Inject
	private ProductDAO productDAO;

	@Inject
	private ProductCategoryDAO productCategoryDAO;

	@Inject
	private SaleDAO saleDAO;

	@Override
	protected AbstractGenericDAO<Product, Long> getEntityDao() {
		return productDAO;
	}

	public void loadByUser(User user) {
		List<Sale> userSales = saleDAO.findByUser(user);
		List<Product> userProducts = new ArrayList<Product>();

		for (Sale sale : userSales)
			userProducts.addAll(sale.getProducts());

		setList(userProducts);
	}

	public void loadByCategories(List<Long> categories, String filter) {
		List<Product> filteredProducts = new ArrayList<Product>();
		String filterText = filter == null ? "" : filter;
		
		if (categories == null || categories.isEmpty()) {
			setList(filteredProducts);
			return;
		}

		for (Long categoryId : categories) {
			ProductCategory category = productCategoryDAO.findEntity(categoryId);
			filteredProducts.addAll(productDAO.findByCategoryAndFilterText(category, filterText));
		}

		setList(filteredProducts);
	}

}
