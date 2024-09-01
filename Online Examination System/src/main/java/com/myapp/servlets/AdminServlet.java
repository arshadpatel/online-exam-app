package com.myapp.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.myapp.dao.ExamDao;
import com.myapp.utils.Exam;

/**
 * Servlet implementation class AdminServlet
 */
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static ExamDao examdao = new ExamDao();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String examName = request.getParameter("examName");
		String description = request.getParameter("description");
		String action = request.getParameter("action");
		
		int examExists = examdao.getExamId(examName);
		if(examExists != -1) {
			response.sendRedirect("LoadExamsServlet?error=1");
			return;
		}
		if(action != null && action.equals("delete")) {
			int examId = Integer.parseInt(request.getParameter("examId"));
			examdao.deleteExam(examId);
			response.sendRedirect("LoadExamsServlet?page=1");
			return;
		}
		Exam exam = new Exam();
		exam.setExamName(examName);
		exam.setDescription(description);
		int id = examdao.addExam(exam);
		if(id != -1) {
			exam.setId(id);
		}
		response.sendRedirect("LoadExamsServlet?page=1");
	}

}
