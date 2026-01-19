<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品情報編集画面</title>
</head>
<body>
	<h1>商品情報編集</h1>
	<%
		String error = (String) request.getAttribute("errorMessage");
		if (error != null) {
	%>
		<p style="color:red;"><%= error %></p>
	<%
		}
	%>
	<c:if test="${not empty errors}">
		<div>
			<ul>
				<c:forEach var="error" items="${errors.values() }">
					<li style="color:red;">${error}</li>
				</c:forEach>
			</ul>
		</div>
	</c:if>
	<jsp:useBean id="product" scope="request" class="model.entity.ProductBean" />
	<form action="product-edit-servlet" method="POST">
		商品ID：<jsp:getProperty name="product" property="productId"/>
		<input type="hidden" name="productId" value="<jsp:getProperty name="product" property="productId"/>"><br>
		商品名：<input type="text" name="productName" value="<jsp:getProperty name="product" property="productName"/>"><br>
		単価　：<input type="number" name="price" value="<jsp:getProperty name="product" property="price"/>"><br>
		在庫数：<input type="number" name="quantity" value="<jsp:getProperty name="product" property="quantity"/>"><br>
		商品説明：<br>
		<textarea name="description" rows="10" cols="50"><jsp:getProperty name="product" property="description"/></textarea><br>
		カテゴリID：
		<select name="categoryId">
			<c:forEach var="category" items="${categoryList }">
				<option value="${category.categoryId}" <c:if test="${category.categoryId == product.categoryId}">selected</c:if>>
					${category.categoryName}
				</option>
			</c:forEach>
		</select>
		<br>
		<br>
		<input type="submit" value="保存する">
	</form>
</body>
</html>