package blackbelt.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import blackbelt.domain.Equipments;


@Component
@Transactional
public class EquipmentDao {
	@PersistenceContext
	private EntityManager em;
	
	public Equipments readEquipment(Long id){
        return (Equipments)em.find(Equipments.class, id);
    }

}
