package blackbelt.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blackbelt.dao.ContactDAO;
import blackbelt.dao.EBayDao;
import blackbelt.domain.Contact;

@Service
public class TestQueries {

	@Autowired
	private EBayDao tcDao;
	
	@Autowired
	private ContactDAO cDao;
	
	public void loadSampleData(){
		tcDao.loadTestData();
	}
	
	public void testContactDAO(){
		System.out.println("testing ContactDAO");
		System.out.println("Creating Contact...");
		Contact bart = new Contact();
		bart.setLastName("De Winne");
		cDao.createContact(bart);
		
		System.out.println("Read Contact...");
		Contact s = cDao.readContact(Long.valueOf(1));
		System.out.println("User with id 1 has name: " + s.getFirstName() + " " + s.getLastName());
		
		System.out.println("Update Contact...");
		bart.setFirstName("Bart");
		cDao.updateContact(bart);
		
		System.out.println("Delete Contact");
		cDao.deleteContact(s);
		
	}
	
	
}
