package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Date;
import dao.DateDAO;

public class SearchDateStudentServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		/*
		 * sessionの開始
		 */
		HttpSession session = request.getSession(false);

		/*
		 * 自己評価を行う活動をすべてDBから持ってくる
		 */
		DateDAO dateDAO = new DateDAO();
		ArrayList<Date> dateList = new ArrayList<Date>();
		dateList = dateDAO.searchDate();
		session.setAttribute("dateList", dateList);

		getServletContext().getRequestDispatcher("/Public/student/selectDate.jsp").forward(request, response);
	}

}
