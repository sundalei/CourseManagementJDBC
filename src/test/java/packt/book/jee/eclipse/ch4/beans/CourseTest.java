package packt.book.jee.eclipse.ch4.beans;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import packt.book.jee.eclipse.ch4.dao.CourseDAO;
import packt.book.jee.eclipse.ch4.exception.EnrollmentFullException;

public class CourseTest {

	@Test
	public void testIsValidCourse() {
		Course course = new Course();
		Assert.assertFalse(course.isValidCourse());
		
		course.setName("course1");
		Assert.assertFalse(course.isValidCourse());
		
		course.setCredits(0);
		Assert.assertFalse(course.isValidCourse());
		
		course.setCredits(4);
		Assert.assertTrue(course.isValidCourse());
		
		course.setName("");
		Assert.assertFalse(course.isValidCourse());
	}
	
	@Test(expected = EnrollmentFullException.class)
	public void testAddStudent() {
		CourseDAO courseDao = Mockito.mock(CourseDAO.class);
		Mockito.when(courseDao.getNumStudentsInCourse(1)).thenReturn(60);
		Mockito.doNothing().when(courseDao).enrolStudentInCourse(1, 1);
		
		Course course = new Course();
		course.setCourseDAO(courseDao);
		
		course.setId(1);
		course.setName("course1");
		course.setMaxStudents(60);
		
		Student student = new Student();
		student.setId(1);
		student.setFirstName("student1");
		
		course.addStudent(student);
		
		Mockito.verify(courseDao, Mockito.atLeastOnce()).getNumStudentsInCourse(1);
		Mockito.verify(courseDao, Mockito.atLeastOnce()).enrolStudentInCourse(1, 1);
	}
	
	@Test
	public void testAddStudentWithEnrollOpen() {
		CourseDAO courseDao = Mockito.mock(CourseDAO.class);
		Mockito.when(courseDao.getNumStudentsInCourse(1)).thenReturn(59);
		Mockito.doNothing().when(courseDao).enrolStudentInCourse(1, 1);
		
		Course course = new Course();
		course.setCourseDAO(courseDao);
		
		course.setId(1);
		course.setName("course1");
		course.setMaxStudents(60);
		
		Student student = new Student();
		student.setId(1);
		student.setFirstName("student1");
		
		course.addStudent(student);
		
		Mockito.verify(courseDao, Mockito.atLeastOnce()).getNumStudentsInCourse(1);
		Mockito.verify(courseDao, Mockito.atLeastOnce()).enrolStudentInCourse(1, 1);
	}

}
