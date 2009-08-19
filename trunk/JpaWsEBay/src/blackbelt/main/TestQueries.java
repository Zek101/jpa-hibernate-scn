package blackbelt.main;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import blackbelt.dao.CategoryDAO;
import blackbelt.dao.ContactDAO;
import blackbelt.dao.EBayDao;
import blackbelt.dao.EBayItemDAO;
import blackbelt.domain.Category;
import blackbelt.domain.Contact;
import blackbelt.domain.EBayItem;
import blackbelt.domain.PaymentMethod;

@Service
@Transactional
public class TestQueries {

	@Autowired
	private EBayDao tcDao;
	
	@Autowired
	private ContactDAO cDao;
	
	@Autowired
	private EBayItemDAO ebiDao;
	
	@Autowired
	private CategoryDAO catDao;
	
	@PersistenceContext
	private EntityManager em;
	
	
	public void loadSampleData(){
		tcDao.loadTestData();
	}
	
	public void testContactDAO(){
		System.out.println("testing ContactDAO");
		System.out.println("Creating Contact...");
		
		PaymentMethod pm = new PaymentMethod();
		pm.setCardType(PaymentMethod.cardTypes.CC);
		pm.setDetails("737-1237654-99");
		em.persist(pm);
		
		PaymentMethod pm2 = new PaymentMethod();
		pm2.setCardType(PaymentMethod.cardTypes.CA);
		pm2.setDetails("see billing address");
		em.persist(pm2);

		PaymentMethod pm3 = new PaymentMethod();
		pm3.setCardType(PaymentMethod.cardTypes.PP);
		pm3.setDetails("123-456-789-012");
		em.persist(pm3);
		
		Contact bart = new Contact();
		bart.setLastName("De Winne");
		bart.addPaymentMethod(pm);
		bart.addPaymentMethod(pm3);
		cDao.createContact(bart);
		
		System.out.println("Read Contact...");
		Contact s = cDao.readContact(Long.valueOf(1));
		System.out.println("User with id 1 has name: " + s.getFirstName() + " " + s.getLastName());
		
		System.out.println("Update Contact...");
		s.addPaymentMethod(pm2);
		bart.setFirstName("Bart");
		cDao.updateContact(bart);
		cDao.updateContact(s);
		
		System.out.println("Delete Contact");
		//cDao.deleteContact(s);
		
	}
	
	public void testEBayItemDAO(){
		System.out.println("testing EBayItemDAO");
		System.out.println("Creating Item...");
		
		Contact c = cDao.readContact(Long.valueOf(2)); //to test the before last query, replace with 2
		
		EBayItem Nokia6890 = new EBayItem();
		Nokia6890.setTitle("Nokia 6890");
		Nokia6890.setDescription("New Nokia mobile phone");
		Nokia6890.setDetails("Set all Nokia details here");
		Nokia6890.setPriceAsked(Double.valueOf(200.89));
		Nokia6890.setPricePaid(Double.valueOf(190.0));
		Nokia6890.setSeller(c);
		ebiDao.createEBayItem(Nokia6890);
		
		c.addEBayItems(Nokia6890);
		cDao.updateContact(c);
		
		System.out.println("Read Item...");
		EBayItem item = ebiDao.readEBayItem(Long.valueOf(1));
		System.out.println("Item with id 1 has Title: " + item.getTitle());
		item.setPriceAsked(Double.valueOf(3));
		item.setPricePaid(Double.valueOf(5.3));
		
		System.out.println("Update Item...");
		Nokia6890.setDetails("Details of Nokia are updated");
		ebiDao.updateEBayItem(Nokia6890);
		
		System.out.println("Delete Item");
		//ebiDao.deleteEBayItem(Nokia6890); //disable to have some more test data
		
	}
	
	public void testCategoryDAO(){
		System.out.println("testing CategoryDAO");
		System.out.println("Creating Categories...");
		Category media = new Category("Multimedia");
		Category mobiles = new Category("Mobile phones");
		Category books = new Category("Books");
		Category strips = new Category("Strips");
		Category thriller = new Category("Thriller");
		
		books.addChildren(strips, thriller);
		media.addChildren(mobiles);
		mobiles.setParent(media);
		strips.setParent(books);
		thriller.setParent(books);

		catDao.createCategory(strips);
		catDao.createCategory(thriller);
		catDao.createCategory(mobiles);
		catDao.createCategory(books);
		catDao.createCategory(media);
		
		System.out.println("Read Category...");
		Category cat = catDao.readCategory(Long.valueOf(1));
		System.out.println("Category with id 1 has Title: " + cat.getTitle());
		
		
		System.out.println("Update Category...");
		books.setDescription("all computer books");
		catDao.updateCategory(books);
		
		System.out.println("Delete Item");
		//catDao.deleteCategory(mobiles);
		
		EBayItem item = ebiDao.readEBayItem(Long.valueOf(2));
		item.setCategory(media);
		ebiDao.updateEBayItem(item);
			
	}
	
	public void testQueries(){
		List<EBayItem> items = ebiDao.getItemsByPrice();
		System.out.println("All Items order by price:");
		System.out.println("------------------------------------------");
		for(EBayItem i : items){
			System.out.println(i);
		}
		System.out.println("------------------------------------------\n");
		
		items = ebiDao.getExpensiveItems(Double.valueOf(150));
		System.out.println("Expensive items with a value of > 150:");
		System.out.println("------------------------------------------");
		for(EBayItem i : items){
			System.out.println(i);
		}
		System.out.println("------------------------------------------\n");
		
		List<Contact> contacts = cDao.getContactsWithCredidCard();
		System.out.println("All contacts who pay with Credit card");
		System.out.println("------------------------------------------");
		for(Contact c : contacts){
			System.out.println(c);
		}
		System.out.println("------------------------------------------\n");
		
		items = ebiDao.getItemWithPaypal(Double.valueOf(201));
		System.out.println("Get items that can be payed with Paypall");
		System.out.println("------------------------------------------");
		for(EBayItem i : items){
			System.out.println(i);
		}
		System.out.println("------------------------------------------\n");
	
		items = ebiDao.getItemWithCashInLV();
		System.out.println("Get items I can pay cash in Las Vegas");
		System.out.println("------------------------------------------");
		for(EBayItem i : items){
			System.out.println(i);
		}
		System.out.println("------------------------------------------\n");
	
		List<Category> categories = catDao.getCategoriesWithItemsInLA();
		System.out.println("Get categories who sell object in San Fransisco");
		System.out.println("------------------------------------------");
		for(Category i : categories){
			System.out.println(i);
		}
		System.out.println("------------------------------------------\n");
	
		categories = catDao.getCategoriesContainingItems();
		System.out.println("Get non-leaf categories having items");
		System.out.println("------------------------------------------");
		for(Category i : categories){
			System.out.println(i);
		}
		System.out.println("------------------------------------------\n");
	}
	
	
}
