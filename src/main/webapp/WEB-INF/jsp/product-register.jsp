<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新規商品 - 登録確認</title>
</head>
<body>
	次の商品を登録しました。
	<br>
	
	<jsp:useBean id="product" scope="request" class="model.entity.ProductBean" />
	商品ID：<jsp:getProperty property="productId" name="product"/><br>
	商品名：<jsp:getProperty property="productName" name="product"/><br>
	単価　：<jsp:getProperty property="price" name="product"/><br>
	在庫数：<jsp:getProperty property="quantity" name="product"/><br>
	商品説明：<br>
	<jsp:getProperty property="description" name="product"/><br>
	カテゴリID：<jsp:getProperty property="categoryId" name="product"/><br>
	<form action="product-list-servlet" method="GET">
		<input type="submit" value="商品一覧に戻る">
	</form>
</body>
</html>