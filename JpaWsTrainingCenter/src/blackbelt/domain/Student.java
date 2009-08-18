package blackbelt.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Student {
	@Id @GeneratedValue
	private Long id;
	
	@Column(name="STUDENT_NAME")
	private String name;

	@OneToMany
	private Set<Registration> registrations = new HashSet<Registration>();
	
	public Student(String name) {
		this.name = name;
	}

	public Student() {
		
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

	public Set<Registration> getRegistrations() {
		return registrations;
	}

	public void setRegistrations(Set<Registration> registrations) {
		this.registrations = registrations;
	}
	
	public void addRegistrations(Registration ... registration){
		for (int i = 0; i < registration.length; i++)
			registrations.add(registration[i]);

	}
	
	
	
}
