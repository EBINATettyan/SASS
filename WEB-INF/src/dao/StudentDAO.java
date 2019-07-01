package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import beans.Student;
import utility.DriverAccessor;

public class StudentDAO extends DriverAccessor {

	// ユーザIDからパスワードを返す
	public Student loginStudent(String studentId) {

		Connection con = null;
		con = createConnection();

		try {
			String sql = "select * from students where student_id = '" + studentId + "';";

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();

			Student user = new Student(rs.getInt("id"), rs.getString("student_id"), rs.getString("password"));

			stmt.close();
			rs.close();
			closeConnection(con);

			return user;

		} catch (

		SQLException e)

		{
			e.printStackTrace();
			return null;
		} finally

		{
		}
	}
}