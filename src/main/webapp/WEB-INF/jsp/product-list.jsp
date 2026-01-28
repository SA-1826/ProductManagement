<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List, model.entity.ProductBean"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
		String error = (String) request.getAttribute("errorMessage");
		if (success != null) {
	%>
		<p style="color:green;"><%= success %></p>
	<%
			session.removeAttribute("flashMessage");
		} else if (error != null) {
	%>
		<p style="color:red;"><%= error %></p>
	<%
		}
	%>
	
	<form action="product-list-servlet" method="POST">
		<select name="categoryId">
			<option value="">カテゴリで絞り込み</option>
			<c:forEach var="category" items="${categoryList}">
				<option value="${category.categoryId}">${category.categoryName}</option>
			</c:forEach>
		</select>
		<input type="submit" value="絞り込む">
	</form>
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
			<th>カテゴリ名</th>
			<th></th>
			<th></th>
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
			<td><%= product.getCategoryName() %></td>
			<td>
				<form action="product-delete-servlet" method="POST">
					<input type="hidden" name="productId" value="<%= product.getProductId() %>">
					<input type="submit" value="削除" onclick="return Delete_Dialog()">
				</form>
			</td>
			<td>
				<form action="product-edit-servlet" method="GET">
					<input type="hidden" name="productId" value="<%= product.getProductId() %>">
					<input type="submit" value="編集">
				</form>
			</td>
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
<script type="text/javascript">
	function Delete_Dialog() {
		var res = confirm("選択した商品を本当に削除しますか？");
		if (res) {
			return true;
		} else {
			return false;
		};
	};
</script>
</html>