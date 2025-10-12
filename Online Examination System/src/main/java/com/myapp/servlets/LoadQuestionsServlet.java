package com.myapp.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.myapp.dao.QuestionDao;
import com.myapp.utils.Question;

/**
 * Servlet implementation class LoadQuestionsServlet
 */
public class LoadQuestionsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static QuestionDao questiondao = new QuestionDao();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoadQuestionsServlet() {
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
		int examId = Integer.parseInt(request.getParameter("examId"));
		String page = request.getParameter("page");
		String examName = request.getParameter("examName");

		List<Question> questionList = questiondao.getAllQuestions(examId);
		request.setAttribute("examId", examId);
		request.setAttribute("questionList", questionList);
		request.setAttribute("examName", examName);
		if (page!=null && page.equals("examPage"))
			request.getRequestDispatcher("student/examPage.jsp").forward(request, response);
		else
			request.getRequestDispatcher("admin/manageQuestions.jsp").forward(request, response);
	}

}
