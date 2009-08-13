package blackbelt.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class ProductPK implements Serializable {

	protected String barcode;
	protected String country;
	
	
	public ProductPK(String barcode, String country) {
		this.barcode = barcode;
		this.country = country;
	}
	
	public ProductPK() {
		super();
	}
	
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}

	
	public boolean equals(Object obj) {
		if(this == obj)return true;
		if(!(obj instanceof ProductPK)) return false;
		ProductPK that = (ProductPK)obj;
		return 	this.country.equals(that.country) &&
				this.barcode.equals(that.barcode);
	}

	
	

	
}
