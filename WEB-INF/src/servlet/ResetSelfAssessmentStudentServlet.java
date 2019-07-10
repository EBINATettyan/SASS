package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import control.SystemManager;
import dao.SelfAssessmentDAO;

@SuppressWarnings("serial")
//最大MaxSize
@MultipartConfig(maxFileSize = 104857600)
public class ResetSelfAssessmentStudentServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		//ファイル以外の入力情報の取得
		HttpSession session = request.getSession(false);
		String studentId = (String) session.getAttribute("studentId");
		int countId = Integer.parseInt(request.getParameter("countId"));
		int dateId = (int) session.getAttribute("dateId");

		/*
		 * DBからcompId(countId)に該当するデータを配列としてもってくる
		 */
		SelfAssessmentDAO selfAssessmentDAO = new SelfAssessmentDAO();
		ArrayList<Integer> valueList = new ArrayList<Integer>();
		valueList = selfAssessmentDAO.selectSelfAssessmentDataOrderByCompId(studentId, dateId, countId);

		/*
		 * 外れ値分析の実行
		 */
		SystemManager systemManager = new SystemManager();
		ArrayList<Float> result_outlier = systemManager.outlierAnalysisByJava(valueList);

		System.out.println("<------------------------->");
		System.out.println("数値データの下限値" + result_outlier.get(0));
		System.out.println("数値データの上限値" + result_outlier.get(1));
		System.out.println("<------------------------->");

		int checkId = selfAssessmentDAO.checkSelfAssessmentRegistration(studentId, dateId);
		session.setAttribute("checkId", checkId);
		session.setAttribute("result_outlier", result_outlier);
		request.setAttribute("countId", countId);
		getServletContext().getRequestDispatcher("/Public/student/selfAssessment.jsp").forward(request, response);
	}
}