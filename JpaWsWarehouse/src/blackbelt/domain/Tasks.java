package blackbelt.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Tasks {
	@Id @GeneratedValue
	private Long id;
	
	//@OneToMany
	//private Set<ItemTasks> itemTask = new HashSet<ItemTasks>();
	
	@ManyToOne
	private Employees employee;
	
	private String name;
	private String details;
	
	@Column(name="start_date")
	private Date startDate;
	
	@Column(name="end_date")
	private Date endDate;

	public Tasks() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Tasks(String name) {
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

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/*public Set<ItemTasks> getItemTask() {
		return itemTask;
	}

	public void setItemTask(Set<ItemTasks> itemTask) {
		this.itemTask = itemTask;
	}*/

	public Employees getEmployee() {
		return employee;
	}

	public void setEmployee(Employees employee) {
		this.employee = employee;
	}

	/*public void addItemTask(ItemTasks ...tasks){
		for(int i = 0; i<tasks.length; i++){
			this.itemTask.add(tasks[i]);
		}
	}*/
	
	public String toString(){
		return getName();
	}
	
}
