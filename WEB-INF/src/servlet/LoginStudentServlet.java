package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Student;
import control.LoginManager;
import dao.StudentDAO;

public class LoginStudentServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		String studentId = request.getParameter("studentId");
		String password = request.getParameter("password");

		System.out.println(studentId);
		System.out.println(password);

		/*
		 * DBにアクセス loginの確認
		 */
		StudentDAO loginDAO = new StudentDAO();
		Student student = loginDAO.loginStudent(studentId);
		LoginManager checkLoginManager = new LoginManager();
		int checkId = checkLoginManager.checkStudent(student, studentId, password);

		if (checkId == 0) {
			getServletContext().getRequestDispatcher("/Public/student/redirectIndex.jsp").forward(request, response);
		} else if (checkId == 1) {

			/*
			 * sessionの開始
			 */
			HttpSession session = request.getSession(true);
			session.setAttribute("student", student);
			session.setAttribute("studentId", student.getStudentId());
			session.setAttribute("password", student.getPassword());

			getServletContext().getRequestDispatcher("/Public/student/home.jsp").forward(request, response);
		}
	}
}