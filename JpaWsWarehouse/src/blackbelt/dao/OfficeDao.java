package blackbelt.dao;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import blackbelt.domain.Offices;


@Component
@Transactional
public class OfficeDao {
	@PersistenceContext
	private EntityManager em;
	
	public Offices getOfficeWithMostWarehouses(){
		List<Offices> offices = (List<Offices>)em.createQuery("select o " +
				       "  from Warehouses w" +
				       "  join w.office o" +
				       " group by o" +
				       " order by count(o) desc").getResultList();
		return offices.get(0);
		
	}
	
}
