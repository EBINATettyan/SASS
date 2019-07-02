package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import beans.Date;
import utility.DriverAccessor;

public class DateDAO extends DriverAccessor {

	public ArrayList<Date> searchDate() {

		Connection con = null;
		con = createConnection();

		try {

			String sql = "select * from date";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			ArrayList<Date> dateList = new ArrayList<Date>();
			while (rs.next()) {
				Date date = new Date(rs.getInt("id"), rs.getString("title"), rs.getString("time"));
				dateList.add(date);
			}
			stmt.close();
			rs.close();
			return dateList;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;

		} finally {
		}
	}

	public Date identifyDate(int dateId) {

		Connection con = null;
		con = createConnection();

		try {

			String sql = "select time from date where id ='" + dateId + "'";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			rs.next();
			Date date = new Date(rs.getInt("id"),rs.getString("title"),rs.getString("time"));

			stmt.close();
			rs.close();
			return date;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;

		} finally {
		}
	}

}
