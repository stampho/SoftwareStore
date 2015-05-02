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
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
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

	private String filterText;
	private Long priceMax;
	private Long priceMin;

	private Date fromDate = new Date(1);
	private Date toDate = new Date();
	private Date currentDate = new Date();
	private boolean sortAscending = true;

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

	public void loadByCategories(List<Long> categories) {
		List<Product> filteredProducts = new ArrayList<Product>();
		if (categories == null || categories.isEmpty()) {
			setList(filteredProducts);
			return;
		}

		for (Long categoryId : categories) {
			ProductCategory category = productCategoryDAO
					.findEntity(categoryId);

			filteredProducts
					.addAll(productDAO.findByCategoryAndFilters(category,
							(filterText == null) ? "" : filterText,
							(priceMin == null) ? -1 : priceMin,
							(priceMax == null) ? -1 : priceMax,
							fromDate == null ? new Date() : fromDate,
							fromDate == null ? new Date() : toDate));
		}

		setList(filteredProducts);
	}

	public void clearFilters() {
		filterText = null;
		priceMax = null;
		priceMin = null;
		fromDate = new Date(1);
		toDate = new Date();
		currentDate = new Date();
		return;
	}

	// TODO hiába írom át, nem frissül a nézet
	public void fromDateChanged() {
		if (fromDate.after(toDate)) {
			toDate = fromDate;
		}
	}

	public void toDateChanged() {
		if (fromDate.after(toDate)) {
			fromDate = toDate;
		}
	}

	// TODO valamiért sorba rakja, de az nem jelenik meg
	public String sortByOrderNo() {
		List<Product> result = getList();
		if (sortAscending) {

			// ascending order
			Collections.sort(result, new Comparator<Product>() {

				@Override
				public int compare(Product o1, Product o2) {
					return o1.getCategory().getName()
							.compareTo(o2.getCategory().getName());

				}

			});
			sortAscending = false;

		} else {

			// descending order
			Collections.sort(result, new Comparator<Product>() {

				@Override
				public int compare(Product o1, Product o2) {

					return o2.getCategory().getName()
							.compareTo(o1.getCategory().getName());

				}

			});
			sortAscending = true;
		}
		setList(result);
		return null;
	}

	public String getFilterText() {
		return filterText;
	}

	public void setFilterText(String filterText) {
		this.filterText = filterText;
	}

	public Long getPriceMax() {
		return priceMax;
	}

	public void setPriceMax(Long priceMax) {
		this.priceMax = priceMax;
	}

	public Long getPriceMin() {
		return priceMin;
	}

	public void setPriceMin(Long priceMin) {
		this.priceMin = priceMin;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public Date getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}

}
