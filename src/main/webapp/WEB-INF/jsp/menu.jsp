<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品管理メニュー画面</title>
</head>
<body>
	<h1>商品管理メニュー</h1>
	<form action="product-list-servlet" method="GET">
		<input type="submit" value="商品一覧">
	</form>
	<form action="product-register-servlet" method="GET">
		<input type="submit" value="新規商品登録">
	</form>
	<br>
	<br>
	<form action="user-logout-servlet" method="POST">
		<input type="submit" value="ログアウト">
	</form>
</body>
</html>