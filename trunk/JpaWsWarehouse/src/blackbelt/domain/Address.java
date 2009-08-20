package blackbelt.domain;

import javax.persistence.Embeddable;

@Embeddable
public class Address {
	private String Street;
	private Integer number;
	private String zipcode;
	private String country;
	private String city;
	public Address() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getStreet() {
		return Street;
	}
	public void setStreet(String street) {
		Street = street;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	
}
