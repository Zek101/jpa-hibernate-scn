package blackbelt.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import blackbelt.domain.Category;

@Component
@Transactional
public class CategoryDAO {
	@PersistenceContext
	private EntityManager em;

	public void createCategory(Category category){
		em.persist(category);
	}
	
	public Category readCategory(Long id){
		return (Category)em.createQuery("select c from Category c where c.id = :id").setParameter("id", id).getSingleResult();
	}
	
	public void updateCategory(Category category){
		em.merge(category);
	}
	
	public void deleteCategory(Category category){
		em.remove(category);
	}
	
	public List<Category> getCategoriesWithItemsInLA(){
		return (List<Category>)em.createQuery("select cat " +
				                              "  from EBayItem e" +
				                              "  join e.category cat" +
				                              "  join e.seller con" +
				                              "  join con.billingAddress a" +
				                              " where a.city = :city")
				 .setParameter("city", "San Fransisco")
		         .getResultList();
	}
	
	public List<Category> getCategoriesContainingItems(){
		return (List<Category>)em.createQuery("select c " +
                							  "  from EBayItem e" +
                							  "  join e.category c" +
                							  " where exists elements(c.children)")
                .getResultList();
}
}
