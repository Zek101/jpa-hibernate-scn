package blackbelt.main;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import blackbelt.dao.EquipmentDao;
import blackbelt.dao.OfficeDao;
import blackbelt.dao.TaskDao;
import blackbelt.dao.EmployeeDao;
import blackbelt.dao.WarehouseDao;
import blackbelt.domain.Employees;
import blackbelt.domain.Equipments;
import blackbelt.domain.Offices;
import blackbelt.domain.Tasks;
import blackbelt.domain.Warehouses;

@Service
@Transactional

public class TestQueries {
	@Autowired
	private OfficeDao officeDao;
	
	@Autowired
	private TaskDao taskDao;
	
	@Autowired
	private WarehouseDao whDao;
	
	@Autowired
	private EquipmentDao eqDao;
	
	@Autowired
	private EmployeeDao usrDao;
	
	public void getOfficeWithMostWarehouses(){
		Offices office = officeDao.getOfficeWithMostWarehouses();
		System.out.println("Office with most warehouses: " + office);
		
	}
	
	public void getTaskWithMostItems(){
		Tasks task = taskDao.getTaskWithMostItems();
		System.out.println("Task with most items is: " + task);
	}
	
	public void getWarehouseAvailableItem(){
		Equipments eq = eqDao.readEquipment(Long.valueOf(2)); //read thinkpad
		//available at this time
		//Warehouses w = whDao.getWarehouseAvailableItem(eq, Calendar.getInstance().getTime());
		
		//available on date 
		List<Object[]> warehouseitems = whDao.getWarehouseAvailableItem(eq, (new GregorianCalendar(2009, 4, 5)).getTime());
		for(Object[] wi : warehouseitems){
			System.out.println("Warehouse: " + wi[0] + ", has item '" + wi[1] + "' available.");
		}
	}
	
	public void getUserWithMostUsedEquip(){
		Employees emp = usrDao.getUserWithMostUsedEquip();
		System.out.println("User who has used most equipment is " + emp);
	}
}
