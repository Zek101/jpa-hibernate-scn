package blackbelt.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import blackbelt.domain.Equipments;
import blackbelt.domain.ItemStatus;
import blackbelt.domain.Items;
import blackbelt.domain.WarehouseItems;
import blackbelt.domain.Warehouses;

@Component
@Transactional
public class WarehouseDao {
	@PersistenceContext
	private EntityManager em;
	
	public List<Object[]> getWarehouseAvailableItem(Equipments equipment, Date beforeDate){
		return em.createQuery(
				       "select wh, itm " +
				       "  from ItemStatus is1" +
				       "  join is1.item itm" +
				       "  join itm.itemLocation il" +
				       "  join il.warehouse wh" +
				       "  join itm.equipment eq" +
				       " where (is1.date, is1.item) in " +
				       "	(select max(is2.date) , is2.item " + //get latest status per item
				       "  	   from ItemStatus is2" +
				       "      where is2.date <= :today" +  //filter before date
				       "      group by is2.item)" +
				       "   and is1.statusCode.description = 'Idle'" +
				       "   and eq = :equipment") //only take Idle items
		   .setParameter("today", beforeDate)
		   .setParameter("equipment", equipment)
		   .getResultList();
		
		
	}
}
