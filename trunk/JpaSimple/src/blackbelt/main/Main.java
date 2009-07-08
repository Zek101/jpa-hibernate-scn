package blackbelt.main;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import dto.Car;
import dto.Dog;
import dto.House;
import dto.Person;
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
		//Product p = new Product("Boot", 39.90, "XZ3JS", "Yellow boots");

		//User u = new User();
		//u.setId(new UserPK("Stijn", "Coene"));
		//u.setEmail("stijn.coene@capgemini.com");
		
		//em.persist(p);
		//em.persist(u);
		
		Person p = new Person("Stijn", "Coene");
		Dog d1 = new Dog("Tom", "Brown");
		Dog d2 = new Dog("Bart", "Black");
		
		Car c = new Car("Ford", "ADB 192");
		House h = new House("France");
		
		p.addDog(d1);
		p.addDog(d2);
		
		p.setCarID(c);
		p.setHouseID(h);
		
		Person p2 = new Person("Steven", "De Wilde");
		
		em.persist(p2);
		em.persist(p);
		
		em.getTransaction().commit();
		
		em.close();
		emf.close();
		
	}

}
