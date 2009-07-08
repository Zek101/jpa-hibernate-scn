package dto;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;



@Entity
public class Person {
	@Id @GeneratedValue
	Long id;
	
	String firstName;
	String lastName;
	
	@OneToMany(cascade=CascadeType.PERSIST) @JoinColumn(name="PERSON_ID")
    Set<Dog> dogs = new HashSet<Dog>();
	
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="CAR_ID")
    Car carID;
	
	@ManyToOne(cascade=CascadeType.PERSIST) //working (also change House)
	@JoinColumn(name="HOUSE_ID")  //working
	//@OneToMany(mappedBy="person") //error
    House houseID;
	
	
	
	public Person(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Person() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void addDog(Dog dog){
		this.dogs.add(dog);
		
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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

	public Car getCarID() {
		return carID;
	}

	public void setCarID(Car carID) {
		this.carID = carID;
	}

	public House getHouseID() {
		return houseID;
	}

	public void setHouseID(House houseID) {
		this.houseID = houseID;
	}
	
	
}
