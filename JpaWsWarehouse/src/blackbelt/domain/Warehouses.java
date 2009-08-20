package blackbelt.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Warehouses {
	@Id @GeneratedValue
	private Long id;
	
	//@OneToMany
	//private Set<ItemLocations> itemLocation = new HashSet<ItemLocations>();
	
	@ManyToOne
	private Offices office;
	
	private String name;
	private Address address;
	private String details;
	public Warehouses() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Warehouses(String name) {
		super();
		this.name = name;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	/*public Set<ItemLocations> getItemLocation() {
		return itemLocation;
	}

	public void setItemLocation(Set<ItemLocations> itemLocation) {
		this.itemLocation = itemLocation;
	}*/

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Offices getOffice() {
		return office;
	}

	public void setOffice(Offices office) {
		this.office = office;
	}

	@Override
	public String toString() {
		return name;
	}

	
	/*public void addItemLocation(ItemLocations ... item){
		for(int i = 0; i < item.length; i++){
			this.itemLocation.add(item[i]);
		}
	}*/
	
	
	
	
}
