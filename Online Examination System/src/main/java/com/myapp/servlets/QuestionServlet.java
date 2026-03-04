package com.myapp.servlets;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import com.myapp.dao.ExamDao;
import com.myapp.dao.QuestionDao;
import com.myapp.utils.Exam;
import com.myapp.utils.Question;

public class QuestionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static QuestionDao questionDao = new QuestionDao();
    private static ExamDao examDao = new ExamDao();

    // Handles Edit and Delete (GET requests from links)
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        String idParam = request.getParameter("id");

        if (action == null || idParam == null) {
            response.sendRedirect("manageQuestions.jsp");
            return;
        }

        int questionId = Integer.parseInt(idParam);

        if (action.equals("delete")) {
            int examId = questionDao.getExamIdByQuestionId(questionId);
            questionDao.deleteQuestion(questionId);
            response.sendRedirect("LoadQuestionsServlet?examId=" + examId);

        } else if (action.equals("edit")) {
            Question editQ = questionDao.getQuestionById(questionId);
            int examId = editQ.getExamId();

            // Reload everything needed for manageQuestions.jsp
            ArrayList<Exam> examList = examDao.getAllExams();
            ArrayList<Question> questionList = questionDao.getAllQuestions(examId);

            request.setAttribute("editQuestion", editQ);
            request.setAttribute("examId", examId);
            request.setAttribute("examList", examList);
            request.setAttribute("questionList", questionList);

            request.getRequestDispatcher("manageQuestions.jsp").forward(request, response);
        }
    }

    // Handles Add and Edit form submissions (POST)
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        int examId = Integer.parseInt(request.getParameter("examId"));

        String ques       = request.getParameter("questionText");
        String optionA    = request.getParameter("optionA");
        String optionB    = request.getParameter("optionB");
        String optionC    = request.getParameter("optionC");
        String optionD    = request.getParameter("optionD");
        String crctOption = request.getParameter("correctOption");

        Question question = new Question();
        question.setExamId(examId);
        question.setQuestion(ques);
        question.setOptionA(optionA);
        question.setOptionB(optionB);
        question.setOptionC(optionC);
        question.setOptionD(optionD);
        question.setCorrectOpt(crctOption);

        if ("edit".equals(action)) {
            int questionId = Integer.parseInt(request.getParameter("questionId"));
            question.setQuestionId(questionId);
            questionDao.updateQuestion(question);
        } else {
            // add
            int id = questionDao.addQuestion(question);
            if (id != -1) question.setQuestionId(id);
        }

        response.sendRedirect("LoadQuestionsServlet?examId=" + examId);
    }
}