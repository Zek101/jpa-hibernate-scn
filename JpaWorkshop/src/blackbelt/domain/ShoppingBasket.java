package blackbelt.domain;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class ShoppingBasket  {
	@Id @GeneratedValue
	private Long id;
	
	private Date date;

	@OneToMany
	private Set<Product> product = new HashSet<Product>();
	
	@ManyToOne
	private User user;
	
	
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public Set<Product> getProduct() {
		return product;
	}
	
	public void setProduct(Set<Product> product) {
		this.product = product;
	}
	
	public void setProducts(Product ... product){
		for (int i = 0; i < product.length; i++)
			this.product.add(product[i]);

	}

}
