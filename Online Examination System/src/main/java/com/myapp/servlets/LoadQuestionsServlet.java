package com.myapp.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.myapp.dao.ExamDao;
import com.myapp.dao.QuestionDao;
import com.myapp.utils.Exam;
import com.myapp.utils.Question;

public class LoadQuestionsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static QuestionDao questionDao = new QuestionDao();
    private static ExamDao examDao = new ExamDao();

    // GET: just load the page with exam dropdown
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    String examIdParam = request.getParameter("examId");
    String page = request.getParameter("page");
    String examName = request.getParameter("examName");

    // If examId is present, load questions for that exam
    if (examIdParam != null && !examIdParam.isEmpty()) {
        int examId = Integer.parseInt(examIdParam);
        List<Question> questionList = questionDao.getAllQuestions(examId);

        request.setAttribute("examId", examId);
        request.setAttribute("questionList", questionList);
        request.setAttribute("examName", examName);

        if ("examPage".equals(page)) {
            // This is where your Start Exam button should go
            request.getRequestDispatcher("examPage.jsp").forward(request, response);
        } else {
            ArrayList<Exam> examList = examDao.getAllExams();
            request.setAttribute("examList", examList);
            request.getRequestDispatcher("manageQuestions.jsp").forward(request, response);
        }
    } else {
        // No examId = admin just loading the manage page
        ArrayList<Exam> examList = examDao.getAllExams();
        request.setAttribute("examList", examList);
        request.getRequestDispatcher("manageQuestions.jsp").forward(request, response);
    }
}
    // POST: exam was selected, load questions + keep dropdown populated
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Always reload exam list so dropdown stays visible
        ArrayList<Exam> examList = examDao.getAllExams();
        request.setAttribute("examList", examList);

        String examIdParam = request.getParameter("examId");
        if (examIdParam != null && !examIdParam.isEmpty()) {
            int examId = Integer.parseInt(examIdParam);
            String page = request.getParameter("page");
            String examName = request.getParameter("examName");

            List<Question> questionList = questionDao.getAllQuestions(examId);
            request.setAttribute("examId", examId);
            request.setAttribute("questionList", questionList);
            request.setAttribute("examName", examName);

            if (page != null && page.equals("examPage"))
                request.getRequestDispatcher("examPage.jsp").forward(request, response);
            else
                request.getRequestDispatcher("manageQuestions.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("manageQuestions.jsp").forward(request, response);
        }
    }
}