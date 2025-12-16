<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>データベース接続エラー</title>
</head>
<body>
	<h2>データベース接続エラー</h2>
	<p><%= request.getAttribute("errorMessage") %></p>
	<form action="product-register-servlet" method="GET">
		<input type="submit" value="新規商品登録画面に戻る">
	</form>
	<form action="product-list-servlet" method="GET">
		<input type="submit" value="商品一覧画面に戻る">
	</form>
	<form action="menu-servlet" method="GET">
		<input type="submit" value="商品管理メニュー画面に戻る">
	</form>
</body>
</html>