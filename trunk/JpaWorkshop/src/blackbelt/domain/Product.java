package blackbelt.domain;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import sun.security.action.GetBooleanAction;

@Entity
public class Product {
	@EmbeddedId 
	private ProductPK barCountry;
	
	private Double price;
	
	@ManyToOne
	//@Column(nullable=false)
	private Category category;
	
	@ManyToOne
	private Supplier supplier;

	
	
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public Product(ProductPK barCountry, Double price, Category category,
			Supplier supplier) {
		super();
		this.barCountry = barCountry;
		this.price = price;
		this.category = category;
		this.supplier = supplier;
	}



	public ProductPK getId() {
		return barCountry;
	}

	public void setId(ProductPK barCountry) {
		this.barCountry = barCountry;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	
	public String toString(){
		Category current = getCategory();
		String categoryList = "";
		while(current != null){
			categoryList += "-->" + current.getName();
			current = current.getCategoryParent();
		}
		
		return "Barcode: " + barCountry.barcode +
		       "\ncountry: " + barCountry.country +
		       "\ncategory: " + categoryList;
	}
	
	
}
