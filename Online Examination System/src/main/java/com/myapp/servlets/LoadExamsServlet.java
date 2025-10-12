package com.myapp.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import com.myapp.dao.ExamDao;
import com.myapp.utils.Exam;

/**
 * Servlet implementation class LoadExamsServlet
 */
public class LoadExamsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static ExamDao examdao = new ExamDao();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoadExamsServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String error = request.getParameter("error");
		String page = request.getParameter("page");
		if (error != null && error.equals("1")) {
			request.setAttribute("error", "1");
		}

		ArrayList<Exam> examList = examdao.getAllExams();
		request.setAttribute("examList", examList);
		if (page == null && error != null)
			request.getRequestDispatcher("admin/manageExams.jsp").forward(request, response);
		else if (page != null && page.equals("1"))
			request.getRequestDispatcher("admin/manageExams.jsp").forward(request, response);
		else if (page != null && page.equals("2"))
			request.getRequestDispatcher("admin/manageQuestions.jsp").forward(request, response);
		else if (page != null && page.equals("examList"))
			request.getRequestDispatcher("student/examList.jsp").forward(request, response);
		else if (page != null && page.equals("adminScores"))
			request.getRequestDispatcher("admin/adminScores.jsp").forward(request, response);
	}

}
