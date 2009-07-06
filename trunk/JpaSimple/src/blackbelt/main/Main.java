package blackbelt.main;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import dto.Product;
import dto.User;
import dto.UserPK;



public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPersistenceUnit");
		EntityManager em = emf.createEntityManager();
		
		
		em.getTransaction().begin();
		Product p = new Product("Boot", 39.90, "XZ3JS", "Yellow boots");
		
		User u = new User();
		u.setId(new UserPK("Stijn", "Coene"));
		u.setEmail("stijn.coene@capgemini.com");
		
		em.persist(p);
		em.persist(u);
		
		em.getTransaction().commit();
		
		em.close();
		emf.close();
		
	}

}
