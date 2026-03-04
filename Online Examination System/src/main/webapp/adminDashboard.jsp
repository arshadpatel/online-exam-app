<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.HttpSession"%>
<%@ page import="com.myapp.utils.User"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Admin Dashboard - Online Examination System</title>
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
		<h2>Admin Dashboard</h2>
		<nav>
			<ul>
				<li><a href="LoadExamsServlet?page=1">Manage Exams</a></li>
				<li><a href="LoadExamsServlet?page=2">Manage Questions</a></li>
				<li><a href="LoadExamsServlet?page=adminScores">Admin
						Scores</a></li>
				<li><a href="LogoutServlet">Logout</a></li>
			</ul>
		</nav>

		<div class="content">
			<h3>
				Welcome,
				<%=username%>!
			</h3>
			<p>Select an option from the navigation to manage exams,
				questions, or view student scores.</p>
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
	<footer>&copy; 2026 Online Examination System</footer>
</body>
</html>
