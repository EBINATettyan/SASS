package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import utility.DriverAccessor;

public class FileDAO extends DriverAccessor {
	public void uploadFile(String studentId, String fileDirectory, String fileName, int dateId, int kindId,
			int compId) {
		Calendar c = Calendar.getInstance();
		// フォーマットパターンを指定して表示する
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/HH:mm");

		Connection con = null;
		con = createConnection();

		try {

			String sql = "insert into files(id,student_id,file_directory,file_name,date_id,upload_date,kind_id,comp_id) values(?, ?, ?, ?, ?,?,?,?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, 0);
			stmt.setString(2, studentId);
			stmt.setString(3, fileDirectory);
			stmt.setString(4, fileName);
			stmt.setInt(5, dateId);
			stmt.setString(6, sdf.format(c.getTime()));
			stmt.setInt(7, kindId);
			stmt.setInt(8, compId);
			stmt.executeUpdate();
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		}
	}
}
