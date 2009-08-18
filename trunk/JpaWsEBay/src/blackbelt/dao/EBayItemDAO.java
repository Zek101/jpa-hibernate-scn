package blackbelt.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import blackbelt.domain.EBayItem;

@Component
@Transactional
public class EBayItemDAO {
	@PersistenceContext
	private EntityManager em;
	
	public void createEBayItem(EBayItem item){
		em.persist(item);
	}
	
	public EBayItem readEBayItem(Long id){
		return (EBayItem)em.createQuery("select e from EBayItem e where e.id = :id").setParameter("id", id).getSingleResult();
	}
	
	public void updateEBayItem(EBayItem item){
		em.merge(item);
	}
	
	public void deleteEBayItem(EBayItem item){
		em.remove(item);
	}
}
