package blackbelt.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Employees {
	@Id @GeneratedValue
	private Long id;
	
	@ManyToOne
	private Offices office;
	
	public Offices getOffice() {
		return office;
	}

	public void setOffice(Offices office) {
		this.office = office;
	}

	/*public Set<Tasks> getTasks() {
		return tasks;
	}

	public void setTasks(Set<Tasks> tasks) {
		this.tasks = tasks;
	}*/

	//@OneToMany
	//private Set<Tasks> tasks = new HashSet<Tasks>();
	
	private String firstName;
	private String lastName;
	private String details;
	
	public Employees() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Employees(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
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
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}

	@Override
	public String toString() {
		return firstName;
	}

	/*public Set<Tasks> getTask() {
		return tasks;
	}

	public void setTask(Set<Tasks> tasks) {
		this.tasks = tasks;
	}

	public void addTasks(Tasks ... tasks){
		for(int i = 0; i < tasks.length; i++){
			this.tasks.add(tasks[i]);
		}
	}*/
	
	
}
