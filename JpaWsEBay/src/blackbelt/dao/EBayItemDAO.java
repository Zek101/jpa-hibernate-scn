package blackbelt.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import blackbelt.domain.EBayItem;
import blackbelt.domain.PaymentMethod;

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
	
	
	public List<EBayItem> getItemsByPrice(){
		return (List<EBayItem>)em.createQuery("select e from EBayItem e order by e.priceAsked")
				.getResultList();
	}

	public List<EBayItem> getExpensiveItems(Double minValue){
		return (List<EBayItem>)em.createQuery("select e " +
				                              "  from EBayItem e " +
				                              " where e.priceAsked > :minValue" +
											  " order by e.priceAsked")
				.setParameter("minValue", minValue)
				.getResultList();
	}
	
	
	public List<EBayItem> getItemWithPaypal(Double maxValue){
		return (List<EBayItem>)em.createQuery("select e " +
                							  "  from EBayItem e " +
                							  "  join e.seller c" +
                							  "  join c.paymentMethods p" +
                							  " where p.cardType = :cardType" +
                							  "   and e.priceAsked < :maxValue")
                  .setParameter("cardType", PaymentMethod.cardTypes.PP)
                  .setParameter("maxValue", maxValue)
				  .getResultList();
	}
	
	public List<EBayItem> getItemWithCashInLV(){
		return (List<EBayItem>)em.createQuery("select e " +
                							  "  from EBayItem e " +
                							  "  join e.seller c" +
                							  "  join c.paymentMethods p" +
                							  "  join c.billingAddress a" +
                							  " where p.cardType = :cardType" +
                							  "   and a.city = :city")
                  .setParameter("cardType", PaymentMethod.cardTypes.CA)
                  .setParameter("city", "Las Vegas")
				  .getResultList();
	}
	
}
