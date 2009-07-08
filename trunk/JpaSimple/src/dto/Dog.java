package dto;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;

@Entity
public class Dog {
	@Id @GeneratedValue
	Long id;
	String name;
	String color;
	
	
	public Dog() {
		super();
	}
	
	public Dog(String color, String name) {
		super();
		this.color = color;
		this.name = name;
	}
	
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
}
