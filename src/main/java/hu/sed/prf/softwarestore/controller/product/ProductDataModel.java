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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

	private Date fromDate;
	private Date toDate = new Date();
	private Date currentDate = new Date();

	private OrderByType orderByType = null;

	private Boolean sortAscending = true;

	public ProductDataModel(){
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		try {
			fromDate  = dateFormat.parse("01/01/2007");
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	
	private enum OrderByType {
		CATEGORY, PRODUCT, RELEASEDATE, PRICE
	}

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
		if (orderByType != null) {
			sortList(filteredProducts, orderByType);
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

	public String sortList(List<Product> list, final OrderByType orderByType) {
		if (sortAscending) {
			// ascending order
			Collections.sort(list, new Comparator<Product>() {
				@Override
				public int compare(Product o1, Product o2) {
					switch (orderByType) {
					case CATEGORY:
						return o1.getCategory().getName()
								.compareTo(o2.getCategory().getName());
					case PRODUCT:
						return o1.getCompany().toLowerCase()
								.compareTo(o2.getCompany().toLowerCase());
					case RELEASEDATE:
						Date date1 = o1.getReleaseDate();
						Date date2 = o2.getReleaseDate();
						if (date1.after(date2)) {
							return -1;
						} else if (date1.before(date2)) {
							return 1;
						} else {
							return 0;
						}
					case PRICE:
						return o1.getPrice().compareTo(o2.getPrice());
					default:
						return o1.getCompany().toLowerCase()
								.compareTo(o2.getCompany().toLowerCase());
					}
				}
			});
			sortAscending = false;
		} else {
			// descending order
			Collections.sort(list, new Comparator<Product>() {
				@Override
				public int compare(Product o1, Product o2) {
					switch (orderByType) {
					case CATEGORY:
						return o2.getCategory().getName()
								.compareTo(o1.getCategory().getName());
					case PRODUCT:
						return o2.getCompany().toLowerCase()
								.compareTo(o1.getCompany().toLowerCase());
					case RELEASEDATE:
						Date date1 = o1.getReleaseDate();
						Date date2 = o2.getReleaseDate();
						if (date1.after(date2)) {
							return 1;
						} else if (date1.before(date2)) {
							return -1;
						} else {
							return 0;
						}
					case PRICE:
						return o2.getPrice().compareTo(o1.getPrice());
					default:
						return o2.getCategory().getName()
								.compareTo(o1.getCategory().getName());
					}
				}
			});
			sortAscending = true;
		}
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

	public OrderByType getOrderByType() {
		return orderByType;
	}

	public void setOrderByType(int orderByType) {
		if (orderByType == 0) {
			this.orderByType = OrderByType.CATEGORY;
		} else if (orderByType == 1) {
			this.orderByType = OrderByType.PRODUCT;
		} else if (orderByType == 2) {
			this.orderByType = OrderByType.RELEASEDATE;
		} else if (orderByType == 3) {
			this.orderByType = OrderByType.PRICE;
		}
	}

	public void setOrderByType(OrderByType orderByType) {
		this.orderByType = orderByType;
	}
}
