<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.myapp.utils.User"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Exam Results</title>
<link rel="stylesheet" type="text/css" href="../styles.css">
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
		<h2>Exam Results</h2>
		<p>
			<strong>Exam Name:</strong> Sample Exam 1
		</p>
		<p>
			<strong>Your Score:</strong> 85%
		</p>
		<p>
			<strong>Number of Correct Answers:</strong> 17/20
		</p>
		<p>
			<strong>Date Taken:</strong> 2024-08-11
		</p>

		<h3>Correct Answers</h3>
		<ul>
			<li>Question 1: Correct answer was B (4)</li>
			<li>Question 2: Correct answer was C (Paris)</li>
			<!-- List all questions and their correct answers -->
		</ul>

		<a href="examList.jsp" class="btn">Back to Exams</a>
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
