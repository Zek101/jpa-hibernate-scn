package blackbelt.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Session {
	@Id @GeneratedValue
	private Long id;
	
	@OneToMany
	private Set<Registration> registrations = new HashSet<Registration>();
	
	@OneToMany
	private Set<Date> dates = new HashSet<Date>();
	
	@ManyToOne
	@JoinColumn(nullable=false)
	private Instructor instructor;
	
	@ManyToOne
	@JoinColumn(name="SESSION_COURSE_FK")
	private Course course;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<Registration> getRegistrations() {
		return registrations;
	}

	public void setRegistrations(Set<Registration> registrations) {
		this.registrations = registrations;
	}

	public Set<Date> getDates() {
		return dates;
	}

	public void setDates(Set<Date> dates) {
		this.dates = dates;
	}

	public Instructor getInstructor() {
		return instructor;
	}

	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}
	
	
	public void addSessionDates(Date ... sessionDate){
		for (int i = 0; i < sessionDate.length; i++)
			dates.add(sessionDate[i]);
	}
	
	public void addRegistration(Registration ... registration){
		for (int i = 0; i < registration.length; i++)
			registrations.add(registration[i]);
	}
}
