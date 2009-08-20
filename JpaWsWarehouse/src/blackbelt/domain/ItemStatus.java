package blackbelt.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="item_status")
public class ItemStatus {
	@Id @GeneratedValue
	private Long id;
	
	@ManyToOne
	private StatusCodes statusCode;
	
	@ManyToOne
	private Items item;
	
	private Date date;
	private String details;
	public ItemStatus() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public StatusCodes getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(StatusCodes statusCode) {
		this.statusCode = statusCode;
	}
	public Items getItem() {
		return item;
	}
	public void setItem(Items item) {
		this.item = item;
	}
	@Override
	public String toString() {
			return id + " - " + statusCode + " - " + date;
	}

	
	
}
