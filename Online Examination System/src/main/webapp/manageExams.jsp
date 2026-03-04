<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.myapp.utils.User"%>
<%@ page import="com.myapp.utils.Exam"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Manage Exams - Online Examination System</title>
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
		<h2>Manage Exams</h2>
		<nav>
			<ul>
				<li><a href="adminDashboard.jsp">Dashboard</a></li>
				<li><a href="LoadExamsServlet?page=2">Manage Questions</a></li>
				<li><a href="LoadExamsServlet?page=adminScores">Admin
						Scores</a></li>
			</ul>
		</nav>
		<div class="content">
			<h3>Exam List</h3>
			<table>
				<thead>
					<tr>
						<th>Exam ID</th>
						<th>Exam Name</th>
						<th>Description</th>
						<th>Delete</th>
					</tr>
				</thead>
				<tbody>
					<%
					ArrayList<Exam> examList = (ArrayList<Exam>) request.getAttribute("examList");
					if (examList != null) {
						for (Exam exam : examList) {
					%>

					<tr>
						<td><%=exam.getId()%></td>
						<td><%=exam.getExamName()%></td>
						<td><%=exam.getDescription()%></td>
						<td>
							<!-- Delete button -->
							<form action="AdminServlet" method="post"
								style="display: inline;">
								<input type="hidden" name="action" value="delete"> <input
									type="hidden" name="examId" value="<%=exam.getId()%>">
								<button type="submit">Delete</button>
							</form>
						</td>
					</tr>

					<%
						}
					}
					%>
				</tbody>
			</table>
			<%
			String res = (String) request.getAttribute("error");
			if (res != null && res.equals("1")) {
			%>
			<p style="color: red;">Exam Already Exists !!</p>
			<p style="color: green;">Create New Exam Name</p>
			<%
			}
			%>
			<button onclick="showAddExamForm()">Add New Exam</button>

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

	<!-- Add/Edit Exam Modal -->
	<div id="examModal" class="modal">
		<div class="modal-content">
			<span class="close" onclick="closeModal()">&times;</span>
			<h3>Add/Edit Exam</h3>
			<form action="AdminServlet" method="post">
				<label for="examName">Exam Name:</label> <input type="text"
					id="examName" name="examName" required> <label
					for="description">Description:</label>
				<textarea id="description" name="description"></textarea>

				<button type="submit">Save</button>

			</form>
		</div>
	</div>
	<script>
		function showAddExamForm() {
			document.getElementById('examModal').style.display = 'block';
		}

		function closeModal() {
			document.getElementById('examModal').style.display = 'none';
		}
	</script>
</body>
</html>
