package blackbelt.dao;

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
		return (Category)em.createQuery("select c from Category c where c.id = :id").setParameter("id", id);
	}
	
	public void updateCategory(Category category){
		em.merge(category);
	}
	
	public void deleteCategory(Category category){
		em.remove(category);
	}
}
