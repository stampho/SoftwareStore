package hu.sed.prf.softwarestore.controller.sale;

import hu.sed.prf.softwarestore.controller.user.LoggedInUser;
import hu.sed.prf.softwarestore.dao.product.ProductDAO;
import hu.sed.prf.softwarestore.dao.sale.SaleDAO;
import hu.sed.prf.softwarestore.entity.product.Product;
import hu.sed.prf.softwarestore.entity.sale.Sale;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class Cart implements Serializable {

	private static final long serialVersionUID = 2754230388812924448L;

	private List<Long> container = new ArrayList<Long>();

	@Inject
	private ProductDAO productDAO;

	@Inject
	private SaleDAO saleDAO;

	@Inject
	private LoggedInUser loggedInUser;

	public void add(Long productId) {
		this.container.add(productId);
	}

	public void remove(Long productId) {
		this.container.remove(productId);
	}

	public boolean has(Long productId) {
		if (this.container == null)
			return false;

		return this.container.contains(productId);
	}

	public boolean isEmpty() {
		return this.container == null || this.container.isEmpty();
	}

	public void reset() {
		this.container.clear();
	}

	public List<Product> list() {
		List<Product> products = new ArrayList<Product>();
		for (Long productId : this.container) {
			Product product = this.productDAO.getProductById(productId);
			products.add(product);
		}
		return products;
	}

	public String buy() {
		if (this.isEmpty())
			return "";

		Sale sale = new Sale(this.loggedInUser.getUser(), list());
		saleDAO.saveOrUpdate(sale);
		saleDAO.flush();
		this.reset();

		return "/index.xhtml?faces-redirect=true";
	}

}
