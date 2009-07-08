package dto;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class House {
	@Id @GeneratedValue
	Long id;
	String location;
	
	@OneToMany(mappedBy="houseID")  //working
	//@ManyToOne(cascade=CascadeType.PERSIST) //error
	//@JoinColumn(name="PERSION_ID")		    //error
    Set<Person> persons;

	public House() {
		super();
		// TODO Auto-generated constructor stub
	}

	public House(String location) {
		super();
		this.location = location;
	}
	
	
}
