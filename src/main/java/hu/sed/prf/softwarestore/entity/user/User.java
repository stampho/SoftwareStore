package hu.sed.prf.softwarestore.entity.user;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;


@Entity
@Table(name = "user", uniqueConstraints = {
		@UniqueConstraint(name = "unique_name", columnNames = { "name" }),
		@UniqueConstraint(name = "unique_email", columnNames = { "email" }) })
public class User implements Serializable {

	private static final long serialVersionUID = -8091600730565788663L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;

	@NotEmpty
	@Length(min = 2, max = 14)
	private String name;

	@Email
	private String email;
	
	@NotEmpty
	private String password;

	@Column(name = "registration_date")
	@Temporal(TemporalType.TIMESTAMP)
	@Past
	private Date registrationDate;

	@Column(name = "real_name")
	@Length(max = 31)
	private String realName;

	@Enumerated(EnumType.ORDINAL)
	@NotNull
	private Role role;

	// TODO: It would be nice to have a picture for the user's profile
	// private String picturePath;

	// TODO: Additional members can be:
	// - Address
	// - Phone number
	// - Birth date
	// Most probably we won't need these for a simple webshop.

	public User() {
	}

	public User(String name, String email, String realName, String password) {
		super();
		this.name = name;
		this.email = email;
		this.realName = realName;
		this.password = password;

		this.role = Role.CUSTOMER;
		this.registrationDate = new Date();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
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
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return name + " (" + realName + ")";
	}

}
