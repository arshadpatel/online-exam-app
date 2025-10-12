<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.myapp.utils.Exam"%>
<%@ page import="com.myapp.utils.User"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Available Exams</title>
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
			Hello,
			<%=username%>
			Your Available Exams are :
		</h2>
		<table>
			<thead>
				<tr>
					<th>Exam Name</th>
					<th>Description</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
				<%
				ArrayList<Exam> examList = (ArrayList<Exam>) request.getAttribute("examList");
				if (examList != null) {
					for (Exam exam : examList) {
				%>
				<tr>
					<td><%=exam.getExamName()%></td>
					<td><%=exam.getDescription()%></td>
					<td><a
						href="LoadQuestionsServlet?examId=<%=exam.getId()%>&page=examPage&examName=<%=exam.getExamName()%>"
						class="btn">Start Exam</a></td>
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
