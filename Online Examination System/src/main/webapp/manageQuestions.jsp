<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.myapp.utils.User"%>
<%@ page import="com.myapp.utils.Exam"%>
<%@ page import="com.myapp.utils.Question"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Manage Questions - Online Examination System</title>
<link rel="stylesheet" href="styles.css">
</head>
<body>
	<div class="container">
		<%
		HttpSession session1 = request.getSession(false);
		if (session1 != null && session1.getAttribute("userObject") != null) {
			User user = (User) session1.getAttribute("userObject");
			String username = user.getUsername();
			username = username.substring(0, 1).toUpperCase() + username.substring(1);
			if (user.getRole().equals("admin")) {
		%>
		<h2>Manage Questions</h2>
		<nav>
			<ul>
				<li><a href="adminDashboard.jsp">Dashboard</a></li>
				<li><a href="LoadExamsServlet?page=1">Manage Exams</a></li>
				<li><a href="LoadExamsServlet?page=adminScores">Admin
						Scores</a></li>
			</ul>
		</nav>
		<div class="content">
			<h3>Select Exam</h3>
			<form action="LoadQuestionsServlet" method="post">
				<select id="examSelect" name="examId" onchange="this.form.submit()">
					<option value="" disabled selected>Select Exam</option>
					<!-- Exam options will be populated here -->
					<%
					ArrayList<Exam> examList = (ArrayList<Exam>) request.getAttribute("examList");
					if (examList != null) {
						for (Exam exam : examList) {
					%>
					<option value="<%=exam.getId()%>"><%=exam.getExamName()%></option>

					<%
						}
					}
					%>
				</select>
			</form>
			<h3>Question List</h3>
			<table>
				<thead>
					<tr>
						<th>Question ID</th>
						<th>Question Text</th>
						<th>Edit</th>
						<th>Delete</th>
					</tr>
				</thead>
				<tbody>
					<%
					ArrayList<Question> questionList = (ArrayList<Question>) request.getAttribute("questionList");
					if (questionList != null) {
						for (Question question : questionList) {
					%>
					<tr>
						<td><%=question.getQuestionId()%></td>
						<td><%=question.getQuestion()%></td>
					</tr>
					<%
						}
					}
					%>
				</tbody>
			</table>
			<button onclick="showAddQuestionForm()">Add New Question</button>
		</div>
		<%
		} else {
		response.sendRedirect("login.jsp");
			}
		} else {
		response.sendRedirect("login.jsp");
			}
		%>
	</div>
	<footer>&copy; 2024 Online Examination System</footer>

	<!-- Add/Edit Question Modal -->
	<div id="questionModal" class="modal">
		<div class="modal-content">
			<span class="close" onclick="closeModal()">&times;</span>
			<h3>Add/Edit Question</h3>
			<form action="QuestionServlet" method="post">

				<input type="hidden" id="examId" name="examId"
					value="<%=request.getAttribute("examId")%>"> <label
					for="questionText">Question:</label>
				<textarea id="questionText" name="questionText" required></textarea>

				<label for="optionA">Option A:</label> <input type="text"
					id="optionA" name="optionA" required> <label for="optionB">Option
					B:</label> <input type="text" id="optionB" name="optionB" required>

				<label for="optionC">Option C:</label> <input type="text"
					id="optionC" name="optionC" required> <label for="optionD">Option
					D:</label> <input type="text" id="optionD" name="optionD" required>

				<label for="correctOption">Correct Option:</label> <select
					id="correctOption" name="correctOption">
					<option value="A">A</option>
					<option value="B">B</option>
					<option value="C">C</option>
					<option value="D">D</option>
				</select>

				<button type="submit">Save</button>
			</form>
		</div>
	</div>
	<script>
		function showAddQuestionForm() {
			document.getElementById('questionModal').style.display = 'block';
		}

		function closeModal() {
			document.getElementById('questionModal').style.display = 'none';
		}
	</script>
</body>
</html>