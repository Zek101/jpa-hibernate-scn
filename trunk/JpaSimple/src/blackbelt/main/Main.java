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
		System.out.println("Start main");
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPersistenceUnit");
		EntityManager em = emf.createEntityManager();
		
		System.out.println("Entity manager created");
		
		em.getTransaction().begin();
		Product p = new Product("Boots", 39.90, "XZ3JS", "Yellow boots");
		
		System.out.println("product created");
		
		em.persist(p);
		
		em.getTransaction().commit();
		
		System.out.println("Product committed to db");
		
		em.close();
		emf.close();
		
	}

}
