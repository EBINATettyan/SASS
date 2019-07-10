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

	public void registSelfAssessment(String studentId, int dateId, int comp1, int comp2,String comp1Comment, String comp2Comment, int comp1CheckId,int comp2CheckId) {

		Connection con = null;
		con = createConnection();
		try {
			String sql = "insert into self_assessment_data(id,student_id,date_id,comp1,comp2,comp1_comment,comp2_comment,comp1_check,comp2_check) values(?, ?, ?, ?, ?,?,?,?,?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, 0);
			stmt.setString(2, studentId);
			stmt.setInt(3, dateId);
			stmt.setInt(4, comp1);
			stmt.setInt(5, comp2);
			stmt.setString(6, comp1Comment);
			stmt.setString(7, comp2Comment);
			stmt.setInt(8, comp1CheckId);
			stmt.setInt(9, comp2CheckId);
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
	 * 自己評価の記録を観点毎にもってくる
	 */
	public ArrayList<Integer> selectSelfAssessmentDataOrderByCompId(String studentId, int dateId, int compId) {

		Connection con = null;
		con = createConnection();

		try {
			String sql = "select comp" + compId + " from self_assessment_data where student_id = '" + studentId + "' AND date_id < "
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
