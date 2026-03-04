package com.myapp.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import com.myapp.dao.ExamDao;
import com.myapp.dao.ResultDao;
import com.myapp.utils.Exam;
import com.myapp.utils.UserScore;

/**
 * Servlet implementation class AdminScoresServlet
 */
public class AdminScoresServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static ResultDao resultDao = new ResultDao();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminScoresServlet() {
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
		// Load exam list for the dropdown
	    ExamDao examDao = new ExamDao();
	    ArrayList<Exam> examList = examDao.getAllExams();
	    request.setAttribute("examList", examList);
	    
	    // If an exam was already selected, load scores too
	    String examIdParam = request.getParameter("examId");
	    if (examIdParam != null && !examIdParam.isEmpty()) {
	        int examId = Integer.parseInt(examIdParam);
	        ArrayList<UserScore> adminScores = resultDao.getAdminScores(examId);
	        request.setAttribute("adminScoresList", adminScores);
	    }
	    
	    request.getRequestDispatcher("adminScores.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    // Always reload exam list
	    ExamDao examDao = new ExamDao();
	    request.setAttribute("examList", examDao.getAllExams());
	    
	    int examId = Integer.parseInt(request.getParameter("examId"));
	    ArrayList<UserScore> adminScores = resultDao.getAdminScores(examId);
	    request.setAttribute("adminScoresList", adminScores);
	    
	    request.getRequestDispatcher("adminScores.jsp").forward(request, response);
	}

}
