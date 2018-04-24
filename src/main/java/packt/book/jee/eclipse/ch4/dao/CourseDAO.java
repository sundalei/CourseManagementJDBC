package packt.book.jee.eclipse.ch4.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import packt.book.jee.eclipse.ch4.beans.Course;
import packt.book.jee.eclipse.ch4.db.connection.DatabaseConnectionFactory;

public class CourseDAO {
	
	public static void addCourse(Course course) throws IOException, SQLException {
		// get connection from connection pool
		Connection con = DatabaseConnectionFactory.getConnectionFactory().getConnection();
		try {
			final String sql = "insert into Course (name, credits) values (?, ?)";
			// create the prepared statement with an option to get auto-generated keys
			PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			// set parameters
			stmt.setString(1, course.getName());
			stmt.setInt(2, course.getCredits());
			
			stmt.execute();
			// Get auto-generated keys
			ResultSet rs = stmt.getGeneratedKeys();
			if(rs.next()) {
				course.setId(rs.getInt(1));
			}
			
			rs.close();
			stmt.close();
		} finally {
			con.close();
		}
	}
}
