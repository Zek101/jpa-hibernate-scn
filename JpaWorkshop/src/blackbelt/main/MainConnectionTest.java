package blackbelt.main;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

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
		
		//add sample data to the database
		System.out.print("Saving test date into database...");
		storeData(em);
		System.out.println(" Done.");
		
		
		//execute simple queries
		System.out.println("Executing queries");
		System.out.println("-----------------");
		simpleQueries(em);
		
		
		em.close();
		emf.close();
		
		
	}
	
	private static void storeData(EntityManager em){
		em.getTransaction().begin();
		
		//Category to parent
		Category Computers = new Category("Computers", null);
		Category Notebooks = new Category("Notebooks", Computers);
		Category Software  = new Category("Desktops", Computers);
		Category Desktop   = new Category("Software", Computers);
		Category Magazines = new Category("Magazines", null);
		Category Sexy      = new Category("Sexy", Magazines);
		
		//Category to children
		Computers.setCategoryChildren(Notebooks, Software, Desktop);
		Magazines.setCategoryChildren(Sexy);
		
		em.persist(Computers);
		em.persist(Notebooks);
		em.persist(Software);
		em.persist(Desktop);
		em.persist(Magazines);
		em.persist(Sexy);
		
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
		Product Humo		 = new Product(new ProductPK("234701", "Belgium"), Double.valueOf(3.70),Magazines, CondeNast );
		Product PlayBoy      = new Product(new ProductPK("777777", "US"), Double.valueOf(5.70),Sexy, Microsoft );
		
		em.persist(MacBook);
		em.persist(WindowsVista);
		em.persist(Wired);
		em.persist(Humo);
		em.persist(PlayBoy);
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
		ShoppingBasket sbPhil = new ShoppingBasket(); //no need to add since this person doesn't have any products
		sbPhil.setProducts(PlayBoy, Humo);
		sbPhil.setUser(Phil);
		
		em.persist(sbGeorge);
		em.persist(sbAlex);
		em.persist(sbPhil);
				
		em.getTransaction().commit();
	}
	
	@SuppressWarnings("unchecked")
	public static void simpleQueries(EntityManager em){
		///////////////////////////////
		// EX.1 
		///////////////////////////////
		//Search for simple products based on there barcode and country code
		//[Question: in query below, is it the correct way to use p.barCountry.barcode? Why can't we use p.barcode?
		// I should expect that the Embed would make the fields visible in the Product class]
		Product resultSimple1 = (Product) em.createQuery("select p " +
											"  from Product p " +
											" where p.barCountry.barcode = :barcode " +
											"   and p.barCountry.country = :country")
								.setParameter("barcode", "123332")
								.setParameter("country", "US")
								.getSingleResult();
		System.out.println("Search product by PK (Barcode=123332 and country US)");
		System.out.println(resultSimple1);
		System.out.println();
		
		///////////////////////////////
		// EX.2 
		///////////////////////////////
		//Search products in a category
		//[Question: in the setParameter below, is it correct to set the parameter as a String?
		// I tried to run this storeData method and use the object Magazines instead but I had an 
		// exception]
		List<Product> resultSimple2 =  (List<Product>)em.createQuery("select p " +
														"  from Product p " +
														" where p.category.name = :category")
								.setParameter("category", "Magazines")
								.getResultList();
		
		System.out.println("Search all products that belong to a category (Category=Magazines)");
		for(Product p: resultSimple2){
			System.out.println(p);
		}
		
		///////////////////////////////
		// EX.3 
		///////////////////////////////
		//Search sub categories
		Query querySubCategory = em.createQuery("select c from Category c where c.name = :category");
		Queue<String> categories = new LinkedList<String>();
		Set<Category> catList = new HashSet<Category>();
		
		categories.add("Computers"); //add initial category to queue
		while(categories.size() > 0){
			//poll the element
			String processingCat = categories.poll();
			//find the category for this element
			Category foundCat = (Category)querySubCategory.setParameter("category", processingCat)
								.getSingleResult();
			//add the category to the result set
			catList.add(foundCat);
			
			//add all children to the queue
			for(Category childCat : foundCat.getCategoryChildren()){
				categories.add(childCat.getName());
			}
		}
		
		System.out.println("Category contains " + catList.size() + " elements.");
		List<Product> products = (List<Product>)em.createQuery("select p " +
															   "  from Product p " +
															   " where p.category in (:categoryList)")
									.setParameter("categoryList", catList)
									.getResultList();
		
		System.out.println("All products in category Computers or any subcategory are: ");
		for(Product p : products){
			System.out.println(p);
		}
		
		///////////////////////////////
		// EX.4 
		///////////////////////////////
		List<Supplier> suppliers = (List<Supplier>)em.createQuery("select distinct p.supplier" +
																  "  from Product p" +
																  " where p.price > :maxPrice")
									.setParameter("maxPrice", Double.valueOf(3.0))
									.getResultList();
		System.out.println("All supplier providing products more expensive than $3");
		for(Supplier s : suppliers){
			System.out.println(s.getName());
		}
		
		///////////////////////////////
		// EX.5 
		///////////////////////////////
		List<ShoppingBasket> sb = (List<ShoppingBasket>)em.createQuery("select sb" +
																  	   "  from ShoppingBasket sb" +
																  	   "  join sb.product p" +
																  	   " where p.barCountry.barcode = :barcode " +
															      	   "   and p.barCountry.country = :country")
				  					.setParameter("barcode", "123332")
				  					.setParameter("country", "US")
				  					.getResultList();
		
		System.out.println("Get all ShopingBaskets containing a specific product");
		for(ShoppingBasket s : sb){
			System.out.println(s.getId());
		}			
		
		
		///////////////////////////////
		// EX.6 
		///////////////////////////////
		List<User> users = (List<User>)em.createQuery(	"select u" +
														"  from ShoppingBasket sb" +
														"  join sb.product p" +
														"  join sb.user u" +
														" where p.barCountry.barcode = :barcode " +
														"   and p.barCountry.country = :country" +
														" order by u.name")
							.setParameter("barcode", "777777")
		  					.setParameter("country", "US")
		  					.getResultList();
		System.out.println("Get Users who aving product 777777, US");
		for(User u : users){
			System.out.println(u.getName());
		}
		
		///////////////////////////////
		// EX.7 
		///////////////////////////////
		String category = "Sexy";  //set category to null to order by name
		String queryString = "select u" +
		"  from ShoppingBasket sb" +
		"  join sb.product p" +
		"  join sb.user u" +
		" where p.barCountry.barcode = :barcode " +
		"   and p.barCountry.country = :country ";
		
		if(category != null)
			queryString += " and p.category.name = '"+category+"'" +
						   " order by u.birthDate";
		else
			queryString += " order by u.name";
					
		List<User> usersCat = (List<User>)em.createQuery(	queryString)
				.setParameter("barcode", "777777")
				.setParameter("country", "US")
				.getResultList();
				System.out.println("Get Users who aving product 777777, US for a given category");
		for(User u : usersCat){
			System.out.println(u.getName() );
		}
	}	
}
