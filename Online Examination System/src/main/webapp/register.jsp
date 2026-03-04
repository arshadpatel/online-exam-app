<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Register - Online Examination System</title>
<link rel="stylesheet" href="styles.css">
</head>
<body>
	<div class="container">
		<h2>Create an Account</h2>
		<form action="RegisterServlet" method="post">
			<label for="username">Username:</label> <input type="text"
				id="username" name="username" required>

			<%
			String error = request.getParameter("error");
			if (error != null && error.equals("2")) {
			%>
			<p style="color: red;">Username already exits.</p>
			<%
			}
			%>

			<label for="password">Password:</label> <input type="password"
				id="password" name="password" required> <label
				for="confirmPassword">Confirm Password:</label> <input
				type="password" id="confirmPassword" name="confirmPassword" required>

			<%
			String error2 = request.getParameter("error");
			if (error2 != null && error2.equals("1")) {
			%>
			<p style="color: red;">Password Not Matched!!</p>
			<%
			}
			%>

			<label for="role">Role:</label> <select id="role" name="role"
				required>
				<option value="" disabled selected>Select your role</option>
				<option value="student">Student</option>
				<option value="admin">Admin</option>
			</select>

			<button type="submit">Register</button>



			<p>
				Already have an account? <a href="login.jsp">Login here</a>
			</p>
		</form>
	</div>
	<footer>&copy; 2026 Online Examination System</footer>
</body>
</html>
