package blackbelt.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import blackbelt.domain.Employees;

@Component
@Transactional
public class EmployeeDao {
	@PersistenceContext
	private EntityManager em;
	
	public Employees getUserWithMostUsedEquip(){
		List<Employees> emp = (List<Employees>) em.createQuery("select emp" +
				       "  from ItemTasks it" +
				       "  join it.item itm" +
				       "  join it.task tsk" +
				       "  join tsk.employee emp" +
				       " group by emp, itm" +
				       " order by count(*) desc")
			.getResultList();
		
		return emp.get(0);
	}
}
