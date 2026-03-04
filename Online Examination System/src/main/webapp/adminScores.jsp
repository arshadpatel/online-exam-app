<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.myapp.utils.User"%>
<%@ page import="com.myapp.utils.Exam"%>
<%@ page import="com.myapp.utils.UserScore"%>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Scores</title>
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
			if (user.getRole().equals("admin")) {
		%>
		<h2>Scores</h2>
		<h3>Select Exam</h3>
		<form action="AdminScoresServlet" method="post">
		  	<select id="examSelect" name="examId" onchange="this.form.submit()">
		      <option value="" disabled selected>Select Exam</option>
		      <%
		     ArrayList<Exam> examList = (ArrayList<Exam>) request.getAttribute("examList");
		     if (examList != null) {
		         for (Exam exam : examList) {
		     %>
		     <option value="<%= exam.getId() %>" 
		         <%= String.valueOf(exam.getId()).equals(request.getParameter("examId")) ? "selected" : "" %>>
		         <%= exam.getExamName() %>
		     </option>
		     <%
		         }
		     }
		     %>
		 	</select>
		</form>
		<h3>Results</h3>
		<table>
			<thead>
				<tr>
					<th>Student Name</th>
					<th>Score</th>
				</tr>
			</thead>
			<tbody>
				<%
				ArrayList<UserScore> scoreList = (ArrayList<UserScore>) request.getAttribute("adminScoresList");
				if (scoreList != null) {
					for (UserScore userScore : scoreList) {
				%>
				<tr>
					<th><%=userScore.getUserName()%></th>
					<th><%=userScore.getScore()%></th>
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
