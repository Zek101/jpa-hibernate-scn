package blackbelt.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class PaymentMethod {
	@Id @GeneratedValue
	private Long id;
	
	
	public enum cardTypes{CC, PP, CA}
	private cardTypes cardType;
	private String details;
	
	public PaymentMethod() {
	}
	public cardTypes getCardType() {
		return cardType;
	}
	public void setCardType(cardTypes cardType) {
		this.cardType = cardType;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	
}
