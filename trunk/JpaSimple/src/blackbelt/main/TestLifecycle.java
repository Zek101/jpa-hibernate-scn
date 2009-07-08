package blackbelt.main;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import dto.Product;

public class TestLifecycle {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPersistenceUnit");
		EntityManager em = emf.createEntityManager();
		
		Product p = new Product("Boot", 39.90, "XZ3JS", "Yellow boots");
		Product p2 = new Product("Boot", 39.90, "XZ3JS", "Yellow boots");
		
		/*
		 * New: pojo created with new operator
		 * Managed: persisted
		 * detached: 
		 * removed: em is closed
		 * 
		 * 			| New		| Managed	| Detached	| Removed	|
		 * ----------------------------------------------------------
		 * persist	|OK			|IGN		|EXP		|EXP		|
		 * remove	|OK			|OK			|EXP		|IGN		|
		 * refresh	|EXP		|OK			|EXP		|EXP		|
		 * contains	|FALSE		|TRUE		|			|FALSE		|
		 */
		
		
		//persist - new
		em.persist(p);
		
		//remove - new
		em.remove(p2);
		
		//refresh - new
		try{
			em.refresh(p2);
		}catch(Exception e){System.out.println(e.getMessage());}
		
		//contains - new
		System.out.println(em.contains(p2));
		
		
		
		//persist - managed
		em.persist(p);
		
		//remove - managed
		em.remove(p);
		
		em.persist(p2);
				
		//contains - managed
		System.out.println(em.contains(p2));
		
		
		//persist - removed
		try{
			em.persist(p);
		}catch(Exception e){System.out.println(e.getMessage());}
		
		//remove - removed
		em.remove(p);
		//refresh - removed
		try{
			em.refresh(p);
		}catch(Exception e){System.out.println(e.getMessage());}
		//contains - removed
		System.out.println(em.contains(p));
		


	}

}
