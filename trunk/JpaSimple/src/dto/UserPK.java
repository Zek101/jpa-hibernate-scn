package dto;

import java.io.Serializable;
import javax.persistence.Embeddable;

@Embeddable
public class UserPK implements Serializable{
	protected String firstName;
	protected String lastName;
	
	public UserPK(String firstName, String lastName){
		this.firstName = firstName;
		this.lastName = lastName;
	}
	public boolean equals(Object obj) {
	      if (this == obj) return true;
	      if (!(obj instanceof UserPK)) return false;
	      UserPK that = (UserPK) obj;
	      return this.firstName.equals(that.firstName) && this.lastName.equals(that.lastName);
	  }
}
