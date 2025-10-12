package com.myapp.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import com.myapp.dao.ResultDao;
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
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		int examId = Integer.parseInt(request.getParameter("examId"));
		ArrayList<UserScore> adminScores = resultDao.getAdminScores(examId);
		request.setAttribute("adminScoresList", adminScores);
		request.getRequestDispatcher("admin/adminScores.jsp").forward(request, response);
	}

}
