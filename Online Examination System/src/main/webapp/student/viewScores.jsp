<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.myapp.utils.User"%>
<%@ page import="com.myapp.utils.ExamScore"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View Scores</title>
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
		<h2>
			Your Scores Mr.
			<%=username%></h2>
		<table>
			<thead>
				<tr>
					<th>Exam Name</th>
					<th>Score</th>
				</tr>
			</thead>
			<tbody>
				<%
				ArrayList<ExamScore> userResultList = (ArrayList<ExamScore>) request.getAttribute("userResultList");
				if (userResultList != null) {
					for (ExamScore examScore : userResultList) {
				%>
				<tr>
					<td><%=examScore.getExamName()%></td>
					<td><%=examScore.getScore()%></td>
				</tr>
				<%
					}
				}
				%>
			</tbody>
		</table>
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
