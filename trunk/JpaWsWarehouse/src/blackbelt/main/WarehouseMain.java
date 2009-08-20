package blackbelt.main;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class WarehouseMain {
	public static void main(String[] args){
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("blackbelt/applicationContext.xml");
		TestData testData = (TestData)applicationContext.getBean("testData");
		TestQueries testQueries = (TestQueries)applicationContext.getBean("testQueries");
		
		testData.loadData();
		testQueries.getOfficeWithMostWarehouses();
		testQueries.getTaskWithMostItems();
		testQueries.getWarehouseAvailableItem();
		testQueries.getUserWithMostUsedEquip();
		
		
	}
}
