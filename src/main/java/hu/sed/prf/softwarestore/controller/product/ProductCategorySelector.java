package hu.sed.prf.softwarestore.controller.product;

import hu.sed.prf.softwarestore.dao.product.ProductCategoryDAO;
import hu.sed.prf.softwarestore.entity.product.ProductCategory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@SessionScoped
@Named
public class ProductCategorySelector implements Serializable {

	private static final long serialVersionUID = -5863822915793991250L;

	@Inject
	private ProductCategoryDAO productCategoryDAO;

	// TODO(pvarga): Categories should be reseted after user logs out
	private List<Long> selectedCategories = new ArrayList<Long>();

	// TODO(pvarga): Select all categories per default
	/*
	@PostConstruct
	public void init() {
		this.selectedCategories = new ArrayList<Long>();
		this.selectAll();
	}
	*/

	public List<Long> getSelectedCategories() {
		return selectedCategories;
	}

	public void setSelectedCategories(List<Long> selectedCategories) {
		this.selectedCategories = selectedCategories;
	}

	// WORKAROUND: <h:button> doesn't work properly, <h:commandButton> is used instead.
	// TODO(pvarga): Use AJAX for filtering!
	public void filter() {
		return;
	}

	public void selectAll() {
		List<ProductCategory> categories = this.productCategoryDAO.list();
		this.selectedCategories.clear();

		for (ProductCategory category : categories)
			this.selectedCategories.add(category.getId());
	}

	public void selectNone() {
		this.selectedCategories.clear();
	}

}
