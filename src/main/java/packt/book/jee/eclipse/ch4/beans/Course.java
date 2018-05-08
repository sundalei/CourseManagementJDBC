package packt.book.jee.eclipse.ch4.beans;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import packt.book.jee.eclipse.ch4.dao.CourseDAO;
import packt.book.jee.eclipse.ch4.exception.EnrollmentFullException;

public class Course {

	private int id;

	private String name;

	private int credits;

	private int teacherId;

	private Teacher teacher;

	private CourseDAO courseDAO = new CourseDAO();
	
	private int maxStudents;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCredits() {
		return credits;
	}

	public void setCredits(int credits) {
		this.credits = credits;
	}

	public int getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public void setCourseDAO(CourseDAO courseDAO) {
		this.courseDAO = courseDAO;
	}

	public boolean isValidCourse() {
		return name != null && credits != 0 && name.trim().length() > 0;
	}

	public void addCourse() throws IOException, SQLException {
		CourseDAO.addCourse(this);
	}

	public List<Course> getCourses() throws IOException, SQLException {
		return courseDAO.getCourses();
	}

	public void addStudent(Student student) throws EnrollmentFullException {
		int currentEnrollment = courseDAO.getNumStudentsInCourse(id);
		if(currentEnrollment >= getMaxStudents()) {
			throw new EnrollmentFullException("Course if full. Enrolment closed");
		}
		courseDAO.enrolStudentInCourse(id, student.getId());
	}
	
	public int getMaxStudents() {
		return maxStudents;
	}

	public void setMaxStudents(int maxStudents) {
		this.maxStudents = maxStudents;
	}
}
