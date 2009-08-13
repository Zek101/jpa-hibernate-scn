package blackbelt.main;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import blackbelt.domain.Category;
import blackbelt.domain.Dummy;
import blackbelt.domain.Product;
import blackbelt.domain.ProductPK;
import blackbelt.domain.ShoppingBasket;
import blackbelt.domain.Supplier;
import blackbelt.domain.User;

public class MainConnectionTest {
	public static void main(String[] args){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProductStore");
		EntityManager em = emf.createEntityManager();
		
		storeData(em);
		
		em.close();
		emf.close();
		
		
	}
	
	private static void storeData(EntityManager em){
		em.getTransaction().begin();
		
		//Category
		Category Computers = new Category("Computers", null);
		Category Notebooks = new Category("Notebooks", Computers);
		Category Software  = new Category("Desktops", Computers);
		Category Desktop   = new Category("Software", Computers);
		Category Magazines = new Category("Magazines", null);
		Computers.setCategoryChildren(Notebooks, Software, Desktop);
		
		em.persist(Computers);
		em.persist(Notebooks);
		em.persist(Software);
		em.persist(Desktop);
		em.persist(Magazines);
		
		//Suppliers
		Supplier Apple 	   = new Supplier("Apple");
		Supplier HP 	   = new Supplier("HP");
		Supplier Microsoft = new Supplier("Microsoft");
		Supplier CondeNast = new Supplier("Condé Nast");
		em.persist(Apple);
		em.persist(Notebooks);
		em.persist(Microsoft);
		em.persist(CondeNast);
		
		//Products
		Product MacBook      = new Product(new ProductPK("123332", "US"), Double.valueOf(1244.95),Notebooks, Apple );
		Product WindowsVista = new Product(new ProductPK("666582", "US"), Double.valueOf(2.95),Software, Microsoft );
		Product Wired        = new Product(new ProductPK("098701", "US"), Double.valueOf(4.95),Magazines, CondeNast );
		em.persist(MacBook);
		em.persist(WindowsVista);
		em.persist(Wired);
		
		//Users
		User George = new User("George");
		User Alex   = new User("Alex");
		User Phil   = new User("Phil");
		em.persist(George);
		em.persist(Alex);
		em.persist(Phil);
		
		ShoppingBasket sbGeorge = new ShoppingBasket();
		sbGeorge.setProducts(MacBook);
		sbGeorge.setUser(George);
		ShoppingBasket sbAlex = new ShoppingBasket();
		sbAlex.setProducts(Wired, WindowsVista);
		sbAlex.setUser(Alex);
		//ShoppingBasket sbPhil = new ShoppingBasket(); //no need to add since this person doesn't have any products
		
		em.persist(sbGeorge);
		em.persist(sbAlex);
		//em.persist(sbPhil);
				
		em.getTransaction().commit();
	}
}
