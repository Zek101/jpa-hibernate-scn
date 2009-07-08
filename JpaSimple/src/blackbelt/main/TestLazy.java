package blackbelt.main;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import dto.Invoice;
import dto.InvoiceLine;

//# Create a new TestLazy executable class : It is important to use a new class or at least a new EntityManager instance otherwise the first-level cache will be used and no SQL statements will be executed.

public class TestLazy {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPersistenceUnit");
		EntityManager emData = emf.createEntityManager();

		//Loading data
		emData.getTransaction().begin();
		
		Invoice i = new Invoice("Invoice 1");
		InvoiceLine il1 = new InvoiceLine("Invoice Line 1");
		InvoiceLine il2 = new InvoiceLine("Invoice Line 2");
		InvoiceLine il3 = new InvoiceLine("Invoice Line 3");
		
		i.addInvoices(il1, il2, il3);
		
		emData.persist(i);
		
		emData.getTransaction().commit();
		emData.close();
		//end loading data
		
		
		EntityManager em = emf.createEntityManager();
		//fetching data
		em.getTransaction().begin();
		
		//# Write code that loads a master element (an Invoice)
		Invoice inv = em.find(Invoice.class, 1L);
		
		//# Retrieve the collection of InvoiceLine
		Set<InvoiceLine> invoicelines = inv.getInvoiceLines();
		
		//# Extract the Iterator and loop over the detail elements (over the InvoiceLines of that Invoice), for the purpose of the exercise use a while loop (or a pre-java 5 for loop)
		//# Put breakpoints after having fetched the collection and also one in the loop, prior you access (and print to the console) the detail element’s attribute (as invoiceLine.getQuantity()).
		for(InvoiceLine il:invoicelines){
			System.out.println("IL: " + il.getName());
		
		}
		
		//# Execute the loop step by step with the debugger and see how many SQL statements are issued
		//# Inspect the local variables ?
		//
		//    * What is the type of the collection retrieved from the Invoice ? --> PersistentSet
		//    * Browse the attributes, to see them change 
		
		em.getTransaction().commit();
		em.close();
		emf.close();

	}
	
}
