package com.myapp.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;

import com.myapp.dao.ExamDao;
import com.myapp.dao.ResultDao;
import com.myapp.utils.Result;
import com.myapp.utils.User;
import com.myapp.utils.ExamScore;

/**
 * Servlet implementation class submitExamServlet
 */
public class SubmitExamServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static ExamDao examDao = new ExamDao();
	private static ResultDao resultDao = new ResultDao();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SubmitExamServlet() {
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
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    int noOfQuestions = Integer.parseInt(request.getParameter("no_of_questions"));
    String examName = request.getParameter("examName");

    HttpSession session1 = request.getSession(false);

    if (session1 != null && session1.getAttribute("userObject") != null) {
        
        User user = (User) session1.getAttribute("userObject");
        int score = 0;
        int i = 1;

        // Calculate score
        for (i = 1; i <= noOfQuestions; i++) {
            String optionSelected = request.getParameter("q" + i);
            String crctOption = request.getParameter("q" + i + "answer");
            if (optionSelected != null && optionSelected.equals(crctOption))
                score++;
        }

        int percentage = (score * 100) / (--i);

        // Build result object
        Result result = new Result();
        result.setExamId(examDao.getExamId(examName));
        result.setScore(percentage);
        result.setUserId(user.getUserId());

        // Insert or update result in DB
        int resultExists = resultDao.getResultId(user.getUserId(), examDao.getExamId(examName));
        if (resultExists == -1) {
            int addResult = resultDao.addResult(result);
            if (addResult != -1)
                result.setResultId(addResult);
        } else {
            result.setResultId(resultExists);
            resultDao.updateResult(result);
        }

        // Forward to scores page
        ArrayList<ExamScore> userResultList = resultDao.getUserResults(result.getUserId());
        request.setAttribute("userResultList", userResultList);
        request.getRequestDispatcher("viewScores.jsp").forward(request, response);

    } else {
        response.sendRedirect("login.jsp");
    }
}

}
