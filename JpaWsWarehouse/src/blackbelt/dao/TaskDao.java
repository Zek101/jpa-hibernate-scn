package blackbelt.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import blackbelt.domain.Tasks;


@Component
@Transactional
public class TaskDao {
	@PersistenceContext
	private EntityManager em;
	
	public Tasks getTaskWithMostItems(){
		List<Tasks> tasks = (List<Tasks>)em.createQuery("select t" +
				       "  from ItemTasks it " +
				       "  join it.task t " +
				       " group by t" +
				       " order by count(t) desc ").getResultList();
		return tasks.get(0);
	}
}
