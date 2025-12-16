<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザーログインフォーム</title>
</head>
<body>
	<h1>ユーザーログイン</h1>
	<%
		String error = (String) request.getAttribute("errorMessage");
		String logout = (String) request.getAttribute("logoutMessage");
		String loginError = (String) session.getAttribute("loginError");
		if (error != null) {
	%>
			<p style="color:red;"><%= error %></p>
	<%
		} else if (logout != null) {
	%>
			<p style="color:green;"><%= logout %></p>
	<%
		} else if (loginError != null) {
	%>
			<p style="color:red;"><%= loginError %></p>
	<%
			session.removeAttribute("loginError");
		}
	%>
	
	<form action="user-login-servlet" method="POST">
		メールアドレス：<input type="text" name="userEmail"><br>
		パスワード：<input type="password" name="password"><br>
		<input type="submit" value="ログイン">
	</form>
</body>
</html>