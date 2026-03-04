package com.myapp.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.myapp.utils.DBConnection;
import com.myapp.utils.Question;

public class QuestionDao {

    public ArrayList<Question> getAllQuestions(int id) {
        ArrayList<Question> list = new ArrayList<>();
        String query = "select * from questions where examId = ?";
        try (Connection connection = DBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Question question = new Question();
                question.setQuestionId(resultSet.getInt(1));
                question.setExamId(resultSet.getInt(2));
                question.setQuestion(resultSet.getString(3));
                question.setOptionA(resultSet.getString(4));
                question.setOptionB(resultSet.getString(5));
                question.setOptionC(resultSet.getString(6));
                question.setOptionD(resultSet.getString(7));
                question.setCorrectOpt(resultSet.getString(8));
                list.add(question);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Fixed: was querying exams table instead of questions table
    public int getQuestionId(String question) {
        String query = "select question_id from questions where question = ?";
        try (Connection connection = DBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, question);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                return resultSet.getInt(1);
            else
                return -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    // NEW: get a single question by its ID (needed for edit)
    public Question getQuestionById(int questionId) {
        String query = "select * from questions where question_id = ?";
        try (Connection connection = DBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, questionId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Question question = new Question();
                question.setQuestionId(resultSet.getInt(1));
                question.setExamId(resultSet.getInt(2));
                question.setQuestion(resultSet.getString(3));
                question.setOptionA(resultSet.getString(4));
                question.setOptionB(resultSet.getString(5));
                question.setOptionC(resultSet.getString(6));
                question.setOptionD(resultSet.getString(7));
                question.setCorrectOpt(resultSet.getString(8));
                return question;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // NEW: get examId by questionId (needed to redirect after delete)
    public int getExamIdByQuestionId(int questionId) {
        String query = "select examId from questions where question_id = ?";
        try (Connection connection = DBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, questionId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                return resultSet.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public int addQuestion(Question question) {
        String query = "insert into questions(examId, question, optionA, optionB, optionC, optionD, correctOption) values(?,?,?,?,?,?,?)";
        try (Connection connection = DBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, question.getExamId());
            preparedStatement.setString(2, question.getQuestion());
            preparedStatement.setString(3, question.getOptionA());
            preparedStatement.setString(4, question.getOptionB());
            preparedStatement.setString(5, question.getOptionC());
            preparedStatement.setString(6, question.getOptionD());
            preparedStatement.setString(7, question.getCorrectOpt());
            preparedStatement.executeUpdate();
            return getQuestionId(question.getQuestion());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    // NEW: update an existing question (needed for edit)
    public int updateQuestion(Question question) {
        String query = "update questions set examId=?, question=?, optionA=?, optionB=?, optionC=?, optionD=?, correctOption=? where question_id=?";
        try (Connection connection = DBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, question.getExamId());
            preparedStatement.setString(2, question.getQuestion());
            preparedStatement.setString(3, question.getOptionA());
            preparedStatement.setString(4, question.getOptionB());
            preparedStatement.setString(5, question.getOptionC());
            preparedStatement.setString(6, question.getOptionD());
            preparedStatement.setString(7, question.getCorrectOpt());
            preparedStatement.setInt(8, question.getQuestionId());
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    // NEW: delete a question by its ID
    public int deleteQuestion(int questionId) {
        String query = "delete from questions where question_id = ?";
        try (Connection connection = DBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, questionId);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}