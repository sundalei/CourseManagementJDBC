package packt.book.jee.eclipse.ch4.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import packt.book.jee.eclipse.ch4.beans.Course;
import packt.book.jee.eclipse.ch4.beans.Teacher;
import packt.book.jee.eclipse.ch4.db.connection.DatabaseConnectionFactory;

public class TeacherDAO {

	public static void addTeacher(Teacher teacher) throws IOException, SQLException {
		// get connection from connection pool
		Connection con = DatabaseConnectionFactory.getConnectionFactory().getConnection();
		try {
			// create the prepared statement with an option to get auto-generated keys
			final String sql = "insert into Teacher (first_name, last_name, designation) values (?, ?, ?)";
			PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			// set parameters
			stmt.setString(1, teacher.getFirstName());
			stmt.setString(2, teacher.getLastName());
			stmt.setString(3, teacher.getDesignation());

			stmt.execute();
			// get auto-generated keys
			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				teacher.setId(rs.getInt(1));
			}
			rs.close();
			stmt.close();
		} finally {
			con.close();
		}
	}
	
	public List<Teacher> getTeachers() throws IOException, SQLException {
		// get connection from connection pool
		Connection con = DatabaseConnectionFactory.getConnectionFactory().getConnection();
		
		List<Teacher> teachers = new ArrayList<Teacher>();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			
			stmt = con.createStatement();
			// execute sql query
			rs = stmt.executeQuery("select * from teacher");
			
			//iterate over result set and create Teacher objects
			//add them to teacher list
			while(rs.next()) {
				Teacher teacher = new Teacher();
				teacher.setId(rs.getInt("id"));
				teacher.setFirstName(rs.getString("first_name"));
				teacher.setLastName(rs.getString("last_name"));
				teacher.setDesignation(rs.getString("designation"));
				teachers.add(teacher);
			}
			//System.out.println(teachers);
			return teachers;
		} finally {
			try {
				if(rs != null)
					rs.close();
				if(stmt != null)
					stmt.close();
				if(con != null) 
					con.close();
			} catch(SQLException e) {
				
			}
		}
	}
}
