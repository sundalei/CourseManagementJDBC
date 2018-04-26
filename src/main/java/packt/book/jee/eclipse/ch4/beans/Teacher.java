package packt.book.jee.eclipse.ch4.beans;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import packt.book.jee.eclipse.ch4.dao.TeacherDAO;

public class Teacher extends Person {
	
	private String designation;
	
	private TeacherDAO teacherDao = new TeacherDAO();

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}
	
	public boolean isValidTeacher() {
		return getFirstName() != null && designation != null;
	}
	
	public void addTeacher() throws IOException, SQLException {
		TeacherDAO.addTeacher(this);
	}
	
	public List<Teacher> getTeachers() throws IOException, SQLException {
		return teacherDao.getTeachers();
	}
}
