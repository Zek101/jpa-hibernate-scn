package blackbelt.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Contact {
	@Id @GeneratedValue
	private Long id;
	
	private String gender;
	private String saluation;
	private String ebayUserID;
	private String firstName;
	private String lastName;
	private String email;
	
	@OneToMany(mappedBy="seller")
	private Set<EBayItem> eBayItems = new HashSet<EBayItem>();
	
	@OneToMany
	private Set<PaymentMethod> paymentMethods = new HashSet<PaymentMethod>();
	
	@OneToOne
	private Address shippingAddress;
	
	@OneToOne
	private Address billingAddress;

	public Contact() {
		
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getSaluation() {
		return saluation;
	}

	public void setSaluation(String saluation) {
		this.saluation = saluation;
	}

	public String getEbayUserID() {
		return ebayUserID;
	}

	public void setEbayUserID(String ebayUserID) {
		this.ebayUserID = ebayUserID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<EBayItem> getEBayItems() {
		return eBayItems;
	}

	public void setEBayItems(Set<EBayItem> bayItems) {
		eBayItems = bayItems;
	}

	public Set<PaymentMethod> getPaymentMethods() {
		return paymentMethods;
	}

	public void setPaymentMethods(Set<PaymentMethod> paymentMethods) {
		this.paymentMethods = paymentMethods;
	}

	public Address getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(Address shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public Address getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(Address billingAddress) {
		this.billingAddress = billingAddress;
	}
	
	public void addEBayItems(EBayItem ... item){
		for(int i = 0; i < item.length; i++){
			eBayItems.add(item[i]);
		}
	}
	
	public void addPaymentMethod(PaymentMethod ... pm){
		for(int i = 0; i < pm.length; i++){
			paymentMethods.add(pm[i]);
		}
	}
	
	

	@Override
	public String toString() {
		return this.getFirstName() + " " + this.getLastName();
	}
	
	
}
