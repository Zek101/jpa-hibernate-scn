package blackbelt.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class EBayItem {
	@Id @GeneratedValue
	private Long id;
	
	private String title;
	private String description;
	private String details;
	
	private Double priceAsked;
	private Double pricePaid;
	
	@ManyToOne
	private Contact buyer;
	
	@ManyToOne
	private Contact seller;
	
	@ManyToOne
	private Category category;

	public EBayItem() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public Double getPriceAsked() {
		return priceAsked;
	}

	public void setPriceAsked(Double priceAsked) {
		this.priceAsked = priceAsked;
	}

	public Double getPricePaid() {
		return pricePaid;
	}

	public void setPricePaid(Double pricePaid) {
		this.pricePaid = pricePaid;
	}

	public Contact getBuyer() {
		return buyer;
	}

	public void setBuyer(Contact buyer) {
		this.buyer = buyer;
	}

	public Contact getSeller() {
		return seller;
	}

	public void setSeller(Contact seller) {
		this.seller = seller;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	
}
