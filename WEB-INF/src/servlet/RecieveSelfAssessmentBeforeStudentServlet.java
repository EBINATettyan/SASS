package servlet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import control.FileManager;
import dao.FileDAO;
import dao.SelfAssessmentDAO;

//最大MaxSize
@MultipartConfig(maxFileSize = 104857600)
public class RecieveSelfAssessmentBeforeStudentServlet extends HttpServlet {

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
		int compValue = Integer.parseInt(request.getParameter("comp_value"));
		int dateId = (int) session.getAttribute("dateId");

		// ファイル保存先
		//final File uploadDir = new File("/usr/share/tomcat/webapps/sassFile/" + studentId+"/"+dateId);
		final File uploadDir = new File("/root/eclipse-workspace/sassFile/" + studentId + "/" + dateId);
		Part fl = request.getPart("fl");

		if (fl == null) {
			;
		} else {
			// ファイル名が重複するのを防ぐため、時間を取得する
			SimpleDateFormat timeSub = new SimpleDateFormat("ss");
			String time = timeSub.format(new Date()); // 20161205124121559
			String fileName = time.concat(fl.getSubmittedFileName()).toString();
			FileManager fileManager = new FileManager();
			fileName = fileManager.moveFileName(fileName);
			int kindId = fileManager.IdentifyFileKind(fileName);

			// ファイルの保存
			save(fl, new File(uploadDir, fileName));

			// uploadDirをStirngに変換
			String path = uploadDir.toString();

			// DBに保存
			FileDAO fileDAO = new FileDAO();
			fileDAO.uploadFile(studentId, path, fileName, dateId, kindId, countId);
		}

		/* if文で条件分岐
		 * 具体的には、compId==1であった場合、comp2の場合の外れ値を実行し、その値をsessionで保持し、getRequestDispatcher
		 * compId==2であった場合は、sessionで保持された自己評価の記録とともにDBに記録し、活動を選ぶ画面に遷移させる
		 */
		if (countId == 1) {
			SelfAssessmentDAO selfAssessmentDAO = new SelfAssessmentDAO();
			int checkId = selfAssessmentDAO.checkSelfAssessmentRegistration(studentId, dateId);

			session.setAttribute("comp" + countId, compValue);
			request.setAttribute("countId", countId + 1);
			session.setAttribute("checkId", checkId);
			getServletContext().getRequestDispatcher("/Public/student/selfAssessment_before.jsp").forward(request,
					response);
		} else if (countId == 2) {
			int comp1 = (int) session.getAttribute("comp1");
			int comp2 = compValue;

			SelfAssessmentDAO selfAssessmentDAO = new SelfAssessmentDAO();
			selfAssessmentDAO.registSelfAssessment(studentId, dateId, comp1, comp2, null, null, 0, 0);

			/*
			 * sessionの破棄
			 */
			session.removeAttribute("comp1");
			session.removeAttribute("title");
			session.removeAttribute("date");
			session.removeAttribute("dateId");
			getServletContext().getRequestDispatcher("/Public/student/selectDate.jsp").forward(request, response);
		}
	}

	public void save(Part in, File out) throws IOException {
		BufferedInputStream br = new BufferedInputStream(in.getInputStream());
		try (BufferedOutputStream bw = new BufferedOutputStream(new FileOutputStream(out))) {
			int len = 0;
			byte[] buff = new byte[1024];
			while ((len = br.read(buff)) != -1) {
				bw.write(buff, 0, len);
			}
		}
	}
}