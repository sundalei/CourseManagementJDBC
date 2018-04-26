package packt.book.jee.eclipse.ch4.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import packt.book.jee.eclipse.ch4.beans.Teacher;
import packt.book.jee.eclipse.ch4.db.connection.DatabaseConnectionFactory;

public class TeacherDAO {

	public static void addTeacher(Teacher teacher) throws IOException, SQLException {
		// get connection from connection pool
		Connection con = DatabaseConnectionFactory.getConnectionFactory().getConnection();
		try {
			// create the prepared statement with an option to get auto-generated keys
			final String sql = "insert into Teacher (first_name, last_name, designation) values (?, ?, ?)";
			PreparedStatement stmt = con.prepareStatement(sql);
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
}
