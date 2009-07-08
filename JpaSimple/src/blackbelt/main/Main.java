package blackbelt.main;
import java.util.List;
import java.util.Set;

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

	private static EntityManagerFactory emf;
	private static EntityManager em;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		emf = Persistence.createEntityManagerFactory("myPersistenceUnit");
		em = emf.createEntityManager();
		
		
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
		
		Car c = new Car("Ford", "XSD-456");
		House h = new House("France");
		
		p.addDog(d1);
		p.addDog(d2);
		
		p.setCarID(c);
		p.setHouseID(h);
		
		Person p2 = new Person("Stijn", "De Winne");
		
		em.persist(p2);
		em.persist(p);
		
		em.getTransaction().commit();
		
		//1. Write a method that takes a first name as parameter and finds (and returns) all the Persons having that first name.
		List<Person> persons = getPersonsByFirstName("Stijn"); 
		for(Person per: persons){
			System.out.println(per.getFirstName() + " "+ per.getLastName());
		}
		
		//2. Write a query to retrieve the persons using the car having the plate number “XSD-456”.
		Person per = (Person)em.createQuery("SELECT p FROM Person p WHERE p.carID.licencePlate = 'XSD-456'")
				.getSingleResult();
		System.out.println("Person " + per.getFirstName() + " " + per.getLastName() + " has a car with license plate XSD-456'");
		
		//3. Try to execute a (wrong) query that uses an implicit join in the wrong direction. Find the houses, which have a resident having “John” as first name, with a where clause like: house.residents.firstName = “John”. Identify the error message you get from Hibernate.
		House house = (House)em.createQuery("SELECT h FROM House h WHERE h.persons.firstName = 'Stijn'")
			.getSingleResult();
		//-->illegal attempt to dereference collection
		
		em.close();
		emf.close();
		
		

		
	}
	
	//1. Write a method that takes a first name as parameter and finds (and returns) all the Persons having that first name.
	private static List<Person> getPersonsByFirstName(String firstName){

		List<Person> result = em
		   .createQuery("SELECT p FROM Person p WHERE firstName = :first_name")
		   .setParameter("first_name", firstName)
		   .getResultList();
		

		return result;
	}

}
