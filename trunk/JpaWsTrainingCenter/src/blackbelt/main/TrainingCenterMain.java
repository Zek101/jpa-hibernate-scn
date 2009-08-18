package blackbelt.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TrainingCenterMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("blackbelt/applicationContext.xml");
		TestQueries tq = (TestQueries)applicationContext.getBean("testQueries");
		tq.testSessionsWithReg();

	}

}
