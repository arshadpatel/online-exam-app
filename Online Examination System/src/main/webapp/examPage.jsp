<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.myapp.utils.Question"%>
<%@ page import="com.myapp.utils.User"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Exam Page</title>
<link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
	<div class="container">
		<%
		HttpSession session1 = request.getSession(false);
		if (session1 != null && session1.getAttribute("userObject") != null) {
			User user = (User) session1.getAttribute("userObject");
			String username = user.getUsername();
			username = username.substring(0, 1).toUpperCase() + username.substring(1);
			if (user.getRole().equals("student")) {
		%>
		<%
			String examName = (String) request.getAttribute("examName");
			Integer examId = (Integer) request.getAttribute("examId");  // add this
			int i = 1;
		%>
		<h2>
			Exam:
			<%=examName%></h2>
		<form action="SubmitExamServlet" method="post">  
		    <!-- Pass exam info via hidden fields, NOT query string -->
		    <input type="hidden" name="examName" value="<%=examName%>">
		    <input type="hidden" name="examId"   value="<%=examId%>">
			<!-- Loop through each question -->
			<%
			ArrayList<Question> questionList = (ArrayList<Question>) request.getAttribute("questionList");
			if (questionList != null) {
				for (Question question : questionList) {
			%>
			<div class="question">
				<p>
					<strong>Question <%=i%></strong>
					<%=question.getQuestion()%>
				</p>
				<label><input type="radio" name="q<%=i%>" value="A">
					<%=question.getOptionA()%></label><br> <label><input
					type="radio" name="q<%=i%>" value="B"> <%=question.getOptionB()%></label><br>
				<label><input type="radio" name="q<%=i%>" value="C">
					<%=question.getOptionC()%></label><br> <label><input
					type="radio" name="q<%=i%>" value="D"> <%=question.getOptionD()%></label>
				<input type="hidden" name="q<%=i++%>answer"
					value="<%=question.getCorrectOpt()%>">
			</div>

			<%
				}
			%>
			<input type="hidden" name="no_of_questions" value="<%=--i%>">

			<%
			}
			%>

			<button type="submit">Submit Exam</button>
		</form>
		<%
		} else {
		response.sendRedirect("login.jsp");
			}
		} else {
		response.sendRedirect("login.jsp");
			}
		%>
	</div>
</body>
</html>
