package blackbelt.main;

import java.util.GregorianCalendar;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import blackbelt.domain.Employees;
import blackbelt.domain.Equipments;
import blackbelt.domain.ItemLocations;
import blackbelt.domain.ItemStatus;
import blackbelt.domain.ItemTasks;
import blackbelt.domain.Items;
import blackbelt.domain.Offices;
import blackbelt.domain.StatusCodes;
import blackbelt.domain.Tasks;
import blackbelt.domain.Warehouses;

@Service
@Transactional
public class TestData {
	@PersistenceContext
	private EntityManager em;
	
	public void loadData(){
		System.out.print("Loading data...");
		
		Offices ofcBrussels = new Offices("Brussels");
		Offices ofcTokyo = new Offices("Tokyo");
		
		Warehouses whsBrusselsA = new Warehouses("Brussels-A");
		Warehouses whsBrusselsB = new Warehouses("Brussels-B");
		Warehouses whsTokyoA = new Warehouses("Tokyo-A");
		
		Employees empNicolas = new Employees("Nicolas", "Colruyt");
		Employees empJohn = new Employees("John", "Philips");
		
		Equipments eqThinkpad = new Equipments("Thinkpad 600");
		Equipments eqFerrari = new Equipments("Ferrari Testa");
		
		Items itThinkpadWhite = new Items("Thinkpad 800 - white");
		Items itThinkpadBlack = new Items("Thinkpad 800 - black");
		Items itFerrari = new Items("Ferrari Testa - red");
		
		Tasks tskWork = new Tasks("Work");
		Tasks tskPlayWow = new Tasks("Play Wow");
		Tasks tskHaveFun = new Tasks("Have fun");
		
		ItemTasks ittWorkThinkpad = new ItemTasks();
		ItemTasks ittFunFerrari = new ItemTasks();
		ItemTasks ittFunThinkpad = new ItemTasks();
		ItemTasks ittPlayThinkpad = new ItemTasks();
		
		StatusCodes scIdle = new StatusCodes("Idle");
		StatusCodes scInUse = new StatusCodes("In-Use");
		StatusCodes scOnTrans = new StatusCodes("On-transit");
		StatusCodes scBroken = new StatusCodes("Broken");
		
		ItemStatus isThinkBlackBroken = new ItemStatus();
		isThinkBlackBroken.setDate((new GregorianCalendar(2008, 1, 1)).getTime());
		ItemStatus isThinkBlackIdle = new ItemStatus();
		isThinkBlackIdle.setDate((new GregorianCalendar(2008, 6, 6)).getTime());
		ItemStatus isThinkWhiteInUse = new ItemStatus();
		isThinkWhiteInUse.setDate((new GregorianCalendar(2009, 3, 3)).getTime());
		ItemStatus isThinkWhiteIdle = new ItemStatus();
		isThinkWhiteIdle.setDate((new GregorianCalendar(2009, 4, 4)).getTime());
		ItemStatus isFerrariOnTransit = new ItemStatus();
		isFerrariOnTransit.setDate((new GregorianCalendar(2009, 4, 20)).getTime());
		ItemStatus isThinkWhiteInUse2 = new ItemStatus();
		isThinkWhiteInUse2.setDate((new GregorianCalendar(2009, 5, 5)).getTime());
		ItemStatus isFerrariInUse = new ItemStatus();
		isFerrariInUse.setDate((new GregorianCalendar(2009, 5, 5)).getTime());
		
		ItemLocations ilBrusselThinkWhite = new ItemLocations();
		ilBrusselThinkWhite.setDateFrom((new GregorianCalendar(2008, 1, 1)).getTime());
		ItemLocations ilTokyoFerrari = new ItemLocations();
		ilTokyoFerrari.setDateFrom((new GregorianCalendar(2000, 1, 1)).getTime());
		ItemLocations ilBrusselFerrari = new ItemLocations();
		ilBrusselFerrari.setDateFrom((new GregorianCalendar(2009, 5, 5)).getTime());
		ItemLocations ilBrusselThinkBlack = new ItemLocations();
		ilBrusselThinkBlack.setDateFrom((new GregorianCalendar(2008, 1, 1)).getTime());
		
		//links
		//offices --> * warehouses
		whsBrusselsA.setOffice(ofcBrussels);
		whsBrusselsB.setOffice(ofcBrussels);
		whsTokyoA.setOffice(ofcTokyo);
		
		//warehouses --> * itemLocations
		ilBrusselFerrari.setWarehouse(whsBrusselsA);
		ilBrusselThinkBlack.setWarehouse(whsBrusselsA);
		ilBrusselThinkWhite.setWarehouse(whsBrusselsA);
		ilTokyoFerrari.setWarehouse(whsTokyoA);
		
		//itemLocations <--> items
		ilBrusselThinkWhite.setItem(itThinkpadWhite);
		itThinkpadWhite.setItemLocation(ilBrusselThinkWhite);
		ilTokyoFerrari.setItem(itFerrari);
		itFerrari.setItemLocation(ilTokyoFerrari);
		ilBrusselFerrari.setItem(itFerrari);
		itFerrari.setItemLocation(ilBrusselFerrari);
		ilBrusselThinkBlack.setItem(itThinkpadBlack);
		itThinkpadBlack.setItemLocation(ilBrusselThinkBlack);
		
		
		//office <-- * employees
		empJohn.setOffice(ofcBrussels);
		empNicolas.setOffice(ofcBrussels);
		
		//employees * <-- Tasks
		tskWork.setEmployee(empNicolas);
		tskPlayWow.setEmployee(empNicolas);
		tskHaveFun.setEmployee(empJohn);
		
		//Tasks  --> * ItemTasks
		ittWorkThinkpad.setTask(tskWork);
		ittPlayThinkpad.setTask(tskPlayWow);
		ittFunFerrari.setTask(tskHaveFun);
		ittFunThinkpad.setTask(tskHaveFun);
		
		//ItemTask --> Items
		ittWorkThinkpad.setItem(itThinkpadWhite);
		ittFunFerrari.setItem(itFerrari);
		ittFunThinkpad.setItem(itThinkpadWhite);
		ittPlayThinkpad.setItem(itThinkpadWhite);
		
		//Item * --> Equipment
		itThinkpadWhite.setEquipment(eqThinkpad);
		itThinkpadBlack.setEquipment(eqThinkpad);
		itFerrari.setEquipment(eqFerrari);
		
		//items --> ItemStatus
		isThinkBlackBroken.setItem(itThinkpadBlack);
		isThinkBlackIdle.setItem(itThinkpadBlack);
		isThinkWhiteIdle.setItem(itThinkpadWhite);
		isThinkWhiteInUse.setItem(itThinkpadWhite);
		isThinkWhiteInUse2.setItem(itThinkpadWhite);
		isFerrariInUse.setItem(itFerrari);
		isFerrariOnTransit.setItem(itFerrari);
		
		
		//itemStatus --> statusCode
		isThinkBlackBroken.setStatusCode(scBroken);
		isThinkBlackIdle.setStatusCode(scIdle);
		isThinkWhiteIdle.setStatusCode(scIdle);
		isThinkWhiteInUse.setStatusCode(scInUse);
		isThinkWhiteInUse2.setStatusCode(scInUse);
		isFerrariInUse.setStatusCode(scInUse);
		isFerrariOnTransit.setStatusCode(scOnTrans);
		
		em.persist(ofcBrussels);
		em.persist(ofcTokyo);
		
		em.persist(whsBrusselsA);
		em.persist(whsBrusselsB);
		em.persist(whsTokyoA);
		
		em.persist(empJohn);
		em.persist(empNicolas);
		
		em.persist(ilBrusselThinkBlack);
		em.persist(ilBrusselFerrari);
		em.persist(ilTokyoFerrari);
		em.persist(ilBrusselThinkWhite);
		
		em.persist(eqFerrari);
		em.persist(eqThinkpad);
		
		em.persist(itFerrari);
		em.persist(itThinkpadBlack);
		em.persist(itThinkpadWhite);
		
		em.persist(tskHaveFun);
		em.persist(tskPlayWow);
		em.persist(tskWork);
		
		em.persist(ittPlayThinkpad);
		em.persist(ittFunThinkpad);
		em.persist(ittFunFerrari);
		em.persist(ittWorkThinkpad);
		
		em.persist(scBroken);
		em.persist(scOnTrans);
		em.persist(scInUse);
		em.persist(scIdle);
		
		em.persist(isFerrariInUse);
		em.persist(isFerrariOnTransit);
		em.persist(isThinkWhiteIdle);
		em.persist(isThinkWhiteInUse2);
		em.persist(isThinkWhiteInUse);
		em.persist(isThinkBlackIdle);
		em.persist(isThinkBlackBroken);
		
		System.out.println(" Done.");
	}
}
