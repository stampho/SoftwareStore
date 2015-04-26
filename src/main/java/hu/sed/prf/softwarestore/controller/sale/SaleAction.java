package hu.sed.prf.softwarestore.controller.sale;

import hu.sed.prf.softwarestore.controller.core.AbstractEntityAction;
import hu.sed.prf.softwarestore.dao.core.AbstractGenericDAO;
import hu.sed.prf.softwarestore.dao.product.ProductDAO;
import hu.sed.prf.softwarestore.dao.sale.SaleDAO;
import hu.sed.prf.softwarestore.dao.user.UserDAO;
import hu.sed.prf.softwarestore.entity.product.Product;
import hu.sed.prf.softwarestore.entity.sale.Sale;
import hu.sed.prf.softwarestore.entity.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class SaleAction extends AbstractEntityAction<Sale, Long> {

	private static final long serialVersionUID = 5653646218450561719L;

	@Inject
	SaleDAO saleDAO;

	@Inject
	UserDAO userDAO;

	@Inject
	ProductDAO productDAO;

	@Inject
	Logger logger;

	public SaleAction() {
		super(Sale.class);
	}

	@Override
	protected AbstractGenericDAO<Sale, Long> getEntityDao() {
		return saleDAO;
	}

	@Override
	protected String getNavigationTargetAfterPersist() {
		return "";
	}

	public Long getUserId() {
		Sale sale = (Sale) getEntity();
		if (sale == null) {
			logger.warning("Sale is null");
			return -1L;
		}

		User user = sale.getUser();
		if (user == null) {
			logger.warning("User is null");
			return -1L;
		}

		return user.getId();
	}

	public void setUserId(Long userId) {
		User user = userDAO.findEntity(userId);
		getEntity().setUser(user);
	}

	public List<Long> getProductIdList() {
		Sale sale = (Sale) getEntity();
		if (sale == null) {
			logger.warning("Sale is null");
			return new ArrayList<Long>();
		}

		List<Long> productIdList = new ArrayList<Long>();
		for (Product product : sale.getProducts()) {
			if (product == null) {
				logger.warning("Product is null");
				continue;
			}

			productIdList.add(product.getId());
		}

		return productIdList;
	}

	public void setProductIdList(List<Long> productIdList) {
		List<Product> productList = new ArrayList<Product>();

		for (Long productId : productIdList)
			productList.add(productDAO.findEntity(productId));

		getEntity().setProducts(productList);
	}

}