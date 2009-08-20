package blackbelt.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="status_codes")
public class StatusCodes {
	@Id @GeneratedValue
	private Long id;
	private String description;
	public StatusCodes() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public StatusCodes(String description) {
		super();
		this.description = description;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return description;
	}
	
	
	
}
