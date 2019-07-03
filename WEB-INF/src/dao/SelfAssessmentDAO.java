package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;

import utility.DriverAccessor;

public class SelfAssessmentDAO extends DriverAccessor {

	public void registSliderValue(String userId, int dateId, int comp1, int comp2, int comp3, int comp4,
			String comp1Comment, String comp2Comment, String comp3Comment, String comp4Comment) {

		Connection con = null;
		con = createConnection();
		try {
			String sql = "insert into slider_values(id,user_id,date_id,comp1,comp2,comp3,comp4,comp1_comment,comp2_comment,comp3_comment,comp4_comment) values(?, ?, ?, ?, ?,?,?,?,?,?,?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, 0);
			stmt.setString(2, userId);
			stmt.setInt(3, dateId);
			stmt.setInt(4, comp1);
			stmt.setInt(5, comp2);
			stmt.setInt(6, comp3);
			stmt.setInt(7, comp4);
			stmt.setString(8, comp1Comment);
			stmt.setString(9, comp2Comment);
			stmt.setString(10, comp3Comment);
			stmt.setString(11, comp4Comment);
			stmt.executeUpdate();
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		}
	}

	/*
	 * 既に値が登録されていないかの確認
	 */
	public int checkSelfAssessmentRegistration(String studentId, int dateId) {

		Connection connection = null;
		connection = createConnection();

		try {
			String searchSQL = "select count(*) from self_assessment_data where student_id = '" + studentId
					+ "' AND date_id= "
					+ dateId;
			Statement checkStmt = connection.createStatement();
			ResultSet rs = checkStmt.executeQuery(searchSQL);
			rs.next();

			int result = rs.getInt(1);

			checkStmt.close();
			rs.close();

			return result;

		} catch (SQLException e) {

			e.printStackTrace();

		} finally {
		}
		return 0;
	}

	/*
	 * sliderValueの値を観点毎にもってくる
	 */
	public ArrayList<Integer> selectSelfAssessmentDataOrderByCompId(String studentId, int dateId, int compId) {

		Connection con = null;
		con = createConnection();

		try {
			String sql = "select comp" + compId + " from self_assessmet_data where student_id = '" + studentId + "' AND date_id < "
					+ dateId;

			Statement checkStmt = con.createStatement();
			ResultSet rs = checkStmt.executeQuery(sql);

			ArrayList<Integer> comp1SliderValueList = new ArrayList<Integer>();
			while (rs.next()) {
				comp1SliderValueList.add(rs.getInt("comp" + compId));
			}

			checkStmt.close();
			rs.close();

			Collections.sort(comp1SliderValueList);
			return comp1SliderValueList;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		}
		return null;
	}
}
