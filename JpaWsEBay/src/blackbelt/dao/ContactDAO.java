package blackbelt.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import blackbelt.domain.Contact;

@Component
@Transactional
public class ContactDAO {
	@PersistenceContext
	private EntityManager em;
	
	public void createContact(Contact contact){
		em.persist(contact);
	}
	
	public Contact readContact(Long id){
		System.out.println("Read contact with id: " + id);
		return (Contact)em.createQuery("SELECT c FROM Contact c where c.id = :id")
				.setParameter("id", id)
				.getSingleResult();
	}
	
	public void updateContact(Contact contact){
		em.merge(contact);
	}
	
	public void deleteContact(Contact contact){
		//Contact foundContact = em.find(Contact.class, contact);
		em.remove(contact);
	}
}
