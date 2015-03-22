package hu.sed.prf.softwarestore.entity.sale;

import hu.sed.prf.softwarestore.entity.product.Product;
import hu.sed.prf.softwarestore.entity.user.User;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Sale implements Serializable {

	// FIXME: This number should be generated somehow
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;

	private User user;

	// TODO: Connection should specified here: we need JoinTable (ManyToMany)
	// More than one product can be ordered at once
	private List<Product> products;

	// Price might be changed. For example discount or increase
	private Long price;

	@Temporal(TemporalType.DATE)
	private Date saleDate;

	public Sale() {
	}

	// FIXME: Do we really need to pass the date in the constructor?
	public Sale(User user, List<Product> products, Long price, Date saleDate) {
		super();
		this.user = user;
		this.products = products;
		this.price = price;
		this.saleDate = saleDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public Date getSaleDate() {
		return saleDate;
	}

	public void setSaleDate(Date saleDate) {
		this.saleDate = saleDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((products == null) ? 0 : products.hashCode());
		result = prime * result
				+ ((saleDate == null) ? 0 : saleDate.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sale other = (Sale) obj;
		if (products == null) {
			if (other.products != null)
				return false;
		} else if (!products.equals(other.products))
			return false;
		if (saleDate == null) {
			if (other.saleDate != null)
				return false;
		} else if (!saleDate.equals(other.saleDate))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Sale [id=" + id + ", user=" + user + ", products=" + products
				+ ", price=" + price + ", saleDate=" + saleDate + "]";
	}

}
