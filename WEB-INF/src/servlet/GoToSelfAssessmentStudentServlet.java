package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import control.SystemManager;
import dao.DateDAO;
import dao.SelfAssessmentDAO;

public class GoToSelfAssessmentStudentServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		int dateId = Integer.parseInt(request.getParameter("dateId"));

		/*
		 * sessionの開始
		 */
		HttpSession session = request.getSession(false);
		String studentId = (String) session.getAttribute("studentId");

		/*
		 * まず、選択した日時で既に自己評価を行っているか確認する
		 */
		SelfAssessmentDAO selfAssessmentDAO = new SelfAssessmentDAO();
		int checkId = selfAssessmentDAO.checkSelfAssessmentRegistration(studentId, dateId);

		/*
		 * 選択した日時を絞り込む
		 */
		DateDAO dateDAO = new DateDAO();
		session.setAttribute("dateId", dateId);
		session.setAttribute("time", dateDAO.identifyDate(dateId).getTime());
		session.setAttribute("title", dateDAO.identifyDate(dateId).getTitle());


		/*
		 * countIdを割り振って、観点ごとにページ遷移させる
		 * countIdとcompの番号は一致する
		 */
		int countId = 1;
		request.setAttribute("countId", countId);
		request.setAttribute("checkId", checkId);

		/*
		 * dateIdが2未満だと計算ができないので、条件分岐
		 */
		if (dateId <= 2) {
			getServletContext().getRequestDispatcher("/Public/student/selfAssessment.jsp").forward(request, response);
		} else {

			/*
			 * DBからcompId(countId)に該当するデータを配列としてもってくる
			 */
			ArrayList<Integer> valueList = new ArrayList<Integer>();
			valueList = selfAssessmentDAO.selectSelfAssessmentDataOrderByCompId(studentId, dateId, countId);

			/*
			 * 外れ値分析の実行
			 */
			SystemManager systemManager = new SystemManager();
			ArrayList<Float> result_outlier = systemManager.outlierAnalysisByPython(valueList);
			session.setAttribute("result_outlier",result_outlier);
			getServletContext().getRequestDispatcher("/Public/student/selfAssessment.jsp").forward(request, response);
		}
	}
}
