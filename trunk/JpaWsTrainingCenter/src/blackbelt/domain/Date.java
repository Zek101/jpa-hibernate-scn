package blackbelt.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SESSION_DATES")
public class Date {
	@Id
	@GeneratedValue
	private Long id;

	private java.util.Date sessionDate;

	public Date() {
	}

	public Date(java.util.Date sessionDate) {
		super();
		this.sessionDate = sessionDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public java.util.Date getDate() {
		return sessionDate;
	}

	public void setDate(java.util.Date sessionDate) {
		this.sessionDate = sessionDate;
	}

	public java.util.Date getSessionDate() {
		return sessionDate;
	}

	public void setSessionDate(java.util.Date sessionDate) {
		this.sessionDate = sessionDate;
	}

}
