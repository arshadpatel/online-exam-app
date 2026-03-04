<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Login - Online Examination System</title>
<link rel="stylesheet" href="styles.css">
</head>
<body>
	<script>
		function togglePassword() {
			var passwordField = document.getElementById("password");
			var checkbox = document.getElementById("showPassword");
			if (passwordField.type === "password") {
				passwordField.type = "text";
			} else {
				passwordField.type = "password";
			}
		}
	</script>
	<div class="container">
		<h2>Online Examination System</h2>
		<form action="LoginServlet" method="post">
			<label for="username">Username:</label> <input type="text"
				id="username" name="username" required> <label
				for="password">Password:</label> <input type="password"
				id="password" name="password" required>
				<div>
					<input type="checkbox" id="showPassword" onclick="togglePassword()" style="margin-bottom: 20px;"> Show Password
				</div>

			<button type="submit">Login</button>
			<p>
				Don't have an account? <a href="register.jsp">Register here</a>
			</p>
			<%
			String error = request.getParameter("error");
			if (error != null && error.equals("1")) {
			%>
			<p style="color: red;">Invalid Username or Password</p>
			<%
			}
			%>

			<%
			String res = request.getParameter("registration");
			if (res != null && res.equals("success")) {
			%>
			<p style="color: green;">Registration Successful. Please Login</p>
			<%
			}
			%>

		</form>
	</div>
	<footer>&copy; 2026 Online Examination System</footer>
</body>
</html>
