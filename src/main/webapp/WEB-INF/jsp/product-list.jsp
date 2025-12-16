<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List, model.entity.ProductBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品一覧</title>
</head>
<body>
	<h1>商品一覧</h1>
	<%
		String success = (String) session.getAttribute("flashMessage");
		if (success != null) {
	%>
		<p style="color:green;"><%= success %></p>
	<%
			session.removeAttribute("flashMessage");
		}
	%>
	
	<%
		List<ProductBean> productList = (List<ProductBean>) request.getAttribute("productList");
	%>
	
	<table border="1">
		<tr>
			<th>商品ID</th>
			<th>商品名</th>
			<th>単価</th>
			<th>在庫数</th>
			<th>商品説明</th>
			<th>カテゴリID</th>
		</tr>
		<%
			for (ProductBean product : productList) {
		%>
		<tr>
			<td><%= product.getProductId() %></td>
			<td><%= product.getProductName() %></td>
			<td><%= product.getPrice() %></td>
			<td><%= product.getQuantity() %></td>
			<td><%= product.getDescription() %></td>
			<td><%= product.getCategoryId() %></td>
		</tr>
		<%
			}
		%>
	</table>
	<br>
	<form action="product-register-servlet" method="GET">
		<input type="submit" value="新規商品登録">
	</form>
	<br>
	<form action="menu-servlet" method="GET">
		<input type="submit" value="商品管理メニュー画面に戻る">
	</form>
</body>
</html>