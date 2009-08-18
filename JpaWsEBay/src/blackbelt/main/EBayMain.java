package blackbelt.main;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import blackbelt.domain.Dummy;

public class EBayMain {
	public static void main(String[] args){
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("blackbelt/applicationContext.xml");
		TestQueries tq = (TestQueries)applicationContext.getBean("testQueries");
		tq.loadSampleData();
		
		tq.testContactDAO();
		
	}
}
