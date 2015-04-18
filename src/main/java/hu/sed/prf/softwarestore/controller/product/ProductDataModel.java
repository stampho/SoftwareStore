package hu.sed.prf.softwarestore.controller.product;

import hu.sed.prf.softwarestore.controller.core.AbstractDataModel;
import hu.sed.prf.softwarestore.dao.core.AbstractGenericDAO;
import hu.sed.prf.softwarestore.dao.product.ProductDAO;
import hu.sed.prf.softwarestore.dao.sale.SaleDAO;
import hu.sed.prf.softwarestore.entity.product.Product;
import hu.sed.prf.softwarestore.entity.sale.Sale;

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
	private SaleDAO saleDAO;

	@Override
	protected AbstractGenericDAO<Product, Long> getEntityDao() {
		return productDAO;
	}

	public void load(String username) {
		List<Sale> userSales = saleDAO.getSalesByUsername(username);
		List<Product> userProducts = new ArrayList<Product>();

		for (Sale sale : userSales)
			userProducts.addAll(sale.getProducts());

		setList(userProducts);
	}

}
