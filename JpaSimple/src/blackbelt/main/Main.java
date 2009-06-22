package blackbelt.main;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import dto.Product;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPersistenceUnit");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		Product p = new Product("Boots", 39.90, "XZ3JS", "Yellow boots");
		
		em.persist(p);
		em.getTransaction().commit();
		em.close();
		emf.close();
		
	}

}
