package blackbelt.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import blackbelt.domain.Address;
import blackbelt.domain.Contact;
import blackbelt.domain.EBayItem;

@Component
@Transactional
public class EBayDao {
	@PersistenceContext
	private EntityManager em;
	
	public void loadTestData(){
		System.out.println("Loading test data...");
		Address shippingStijn = new Address();
		shippingStijn.setCity("Oudegem");
		shippingStijn.setCountry("Belgium");
		shippingStijn.setStreet("Mevr Courtmanspark 63");
		shippingStijn.setZipcode("9200");
		shippingStijn.setRemarks("This will be my shipping address");
		
		Address billingStijn = new Address();
		billingStijn.setCity("Las Vegas"); //to test the before last query, replace with San Fransisco
		billingStijn.setCountry("USA");
		billingStijn.setStreet("Avenue 54");
		billingStijn.setZipcode("90210");
		billingStijn.setRemarks("This is stijn's billing address");
		
		EBayItem shirt = new EBayItem();
		shirt.setTitle("Shirt");
		shirt.setDescription("Blue shirt of Nike");
		shirt.setDetails("Details of the shirt goes here");

		
		Contact stijn = new Contact();
		stijn.setBillingAddress(billingStijn);
		stijn.setShippingAddress(shippingStijn);
		stijn.setEbayUserID("BE-1123423");
		stijn.setEmail("stijn.coene@capgemini.com");
		stijn.setFirstName("Stijn");
		stijn.setLastName("Coene");
		stijn.setGender("M");
		stijn.setSaluation("Mr.");
		stijn.addEBayItems(shirt);
		
		shirt.setSeller(stijn);
		
		em.persist(shippingStijn);
		em.persist(billingStijn);
		em.persist(shirt);
		em.persist(stijn);
	}
}
