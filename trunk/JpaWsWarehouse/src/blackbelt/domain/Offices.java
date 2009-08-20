package blackbelt.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Offices {
	@Id @GeneratedValue
	private Long id;
	
	//@OneToMany
	//private Set<Warehouses> warehouses = new HashSet<Warehouses>();
	
	//@OneToMany
	//private Set<Employees> employees = new HashSet<Employees>();
	
	private String name;
	private Address address;
	private String details;
	
	public Offices() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	 
	public Offices(String name) {
		super();
		this.name = name;
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
	
	
	
	/*public Set<Warehouses> getWarehouses() {
		return warehouses;
	}
	public void setWarehouses(Set<Warehouses> warehouses) {
		this.warehouses = warehouses;
	}
	public Set<Employees> getEmployees() {
		return employees;
	}
	public void setEmployees(Set<Employees> employees) {
		this.employees = employees;
	}
	public void addWarehouses(Warehouses ... warehouses){
		for(int i = 0; i < warehouses.length; i++){
			this.warehouses.add(warehouses[i]);
		}
	}

	public void addEmployees(Employees ... employees){
		for(int i = 0; i < employees.length; i++){
			this.employees.add(employees[i]);
		}
	}*/

	public String toString(){
		return this.getName();
	}
}
