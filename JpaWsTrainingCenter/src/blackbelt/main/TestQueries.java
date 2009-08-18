package blackbelt.main;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blackbelt.dao.TrainingCenterDao;
import blackbelt.domain.Instructor;
import blackbelt.domain.Session;

@Service
public class TestQueries {
	
	@Autowired
	private TrainingCenterDao tcDao;
	
	public void testSessionsWithReg(){
		tcDao.loadTestData();
		
		List<Session> sessionWithRegs = tcDao.getSessionsWithRegs();
		System.out.println("The sessions with following ID's have registrations");
		for(Session s : sessionWithRegs){
			System.out.println(s.getId());
		}
		
		Long sessionCount = tcDao.getCountSession(2010, 2);
		System.out.println("There are " + sessionCount + " sessions who start in 2010");
		
		List<Instructor> instructorsGivenCourse= tcDao.instructorsGivenCourse();
		System.out.println("The following instructors have given courses:");
		for(Instructor i : instructorsGivenCourse){
			System.out.println(i.getName());
		}
		
		List<Instructor> instructorsGivenCourseFromDate = tcDao.instructorsGivenCourseFromDate(new java.util.Date(110,2,1));
		System.out.println("The following instructors have given courses since 2011-03-01:");
		for(Instructor i : instructorsGivenCourseFromDate){
			System.out.println(i.getName());
		}
		
	}
}
