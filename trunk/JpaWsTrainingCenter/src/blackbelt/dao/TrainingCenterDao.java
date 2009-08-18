package blackbelt.dao;

import java.util.Calendar;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import sun.reflect.ReflectionFactory.GetReflectionFactoryAction;

import blackbelt.domain.Company;
import blackbelt.domain.Course;
import blackbelt.domain.Date;
import blackbelt.domain.Instructor;
import blackbelt.domain.Registration;
import blackbelt.domain.Session;
import blackbelt.domain.Student;

@Component
@Transactional
public class TrainingCenterDao {
	@PersistenceContext
	private EntityManager em;
	
	
	@SuppressWarnings("unchecked")
	public List<Session> getSessionsWithRegs(){
		//return all sessions that have registrations
		return em.createQuery("SELECT distinct s" +
				              "  FROM Session s" +
				              "  where exists elements(s.registrations)").getResultList();
	}
	
	public Long getCountSession(int year, int month){
		return (Long)em.createQuery("select count(distinct s)" +
				              		"  from Session s" +
				 				    "  join s.dates d" +
							  		" where year(d.sessionDate) = :year" +
							  		"   and month(d.sessionDate) = :month")
				              .setParameter("year", year)
				              .setParameter("month", month)
				              .getSingleResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Instructor> instructorsGivenCourse(){
		return (List<Instructor>)em.createQuery("select distinct i" +
          							"  from Session s" +
          							"  join s.instructor i" +
          							"  join s.course c" +
          							" where c is not null")
		  				.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Instructor> instructorsGivenCourseFromDate(java.util.Date fromDate){
		return (List<Instructor>)em.createQuery("select distinct i" +
          							"  from Session s" +
          							"  join s.instructor i" +
          							"  join s.course c" +
          							"  join s.dates d" + 
          							" where c is not null" +
          							"   and d.sessionDate > :fromDate")
          				.setParameter("fromDate", fromDate)
		  				.getResultList();
	}
	
	
	
	public void loadTestData(){
		
		Student Stijn = new Student("Stijn");
		Student Tom = new Student("Tom");
		
		Company Capgemini = new Company("Capgemini");
		Company JavaBlackBelt = new Company("JavaBlackBelt");
		Company Adobe = new Company("Adobe");
		
		Date sessionDateJpa1 = new Date(new java.util.Date(110,1,9)); //Feb 9 2010
		Date sessionDateJpa2 = new Date(new java.util.Date(110,1,10));
		Date sessionDateSpring1 = new Date(new java.util.Date(110,10,2));
		Date sessionDateSpring2 = new Date(new java.util.Date(110,10,3));
		Date sessionDateSpring3 = new Date(new java.util.Date(110,10,4));
		
		Session sesJpa = new Session();
		sesJpa.addSessionDates(sessionDateJpa1, sessionDateJpa2);
		
		Session sesSpring = new Session();
		sesSpring.addSessionDates(sessionDateSpring1,sessionDateSpring2,sessionDateSpring3);
		
		
		Registration regStijn = new Registration();
		regStijn.setStudent(Stijn);
		regStijn.setCompany(Capgemini);
		regStijn.setSession(sesJpa);
		//sync other direction!
		Stijn.addRegistrations(regStijn);
		sesJpa.addRegistration(regStijn);
		
		Course jpaWs = new Course("Java Blackbelt JPA Workshop");
		jpaWs.addSession(sesJpa);
		sesJpa.setCourse(jpaWs);
		
		Course spring = new Course("Java Blackbelt Spring fundamentals");
		spring.addSession(sesSpring);
		sesSpring.setCourse(spring);
		
		Instructor John = new Instructor("John");
		sesJpa.setInstructor(John);
		sesSpring.setInstructor(John);
		
		Instructor Steven = new Instructor("Steven");
		
		em.persist(Stijn);
		em.persist(Tom);
		
		em.persist(Capgemini);
		em.persist(JavaBlackBelt);
		em.persist(Adobe);
		
		em.persist(sessionDateJpa1);
		em.persist(sessionDateJpa2);
		em.persist(sessionDateSpring1);
		em.persist(sessionDateSpring2);
		em.persist(sessionDateSpring3);
		
		em.persist(John);
		em.persist(Steven);
				
		em.persist(sesJpa);
		em.persist(sesSpring);
				
		em.persist(regStijn);
		
		em.persist(jpaWs);
		em.persist(spring);
		
		
	}
}
