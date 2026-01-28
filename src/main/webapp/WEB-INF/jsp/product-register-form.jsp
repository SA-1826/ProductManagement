<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新規商品登録</title>
</head>
<body>
	<h1>新規商品登録</h1>
	<c:if test="${not empty errors}">
		<div>
			<ul>
				<c:forEach var="error" items="${errors.values() }">
					<li style="color:red;">${error}</li>
				</c:forEach>
			</ul>
		</div>
	</c:if>
	<form action="product-register-servlet" method="POST">
		商品ID：<input type="text" name="productId" value="${product.productId}"><br>
		商品名：<input type="text" name="productName" value="${product.productName}"><br>
		単価　：<input type="number" name="price" value="${param.price}"><br>
		在庫数：<input type="number" name="quantity" value="${param.quantity}"><br>
		商品説明：<br>
		<textarea name="description" rows="10" cols="50">${product.description}</textarea><br>
		カテゴリ名：
		<select name="categoryId">
			<option value="">-- 選択してください --</option>
			<c:forEach var="category" items="${categoryList}">
				<option value="${category.categoryId}">${category.categoryName}</option>
			</c:forEach>
		</select>
		<br>
		<br>
		<input type="submit" value="登録する">
	</form>
</body>
</html>