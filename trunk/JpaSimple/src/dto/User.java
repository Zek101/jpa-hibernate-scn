package dto;

import javax.persistence.Entity;
import javax.persistence.EmbeddedId;


@Entity
public class User {
	@EmbeddedId private UserPK id;
	
	public String email;

	
	
	public UserPK getId() {
		return id;
	}
	public void setId(UserPK id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
