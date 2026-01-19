package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.entity.ProductBean;

public class ProductDAO {
	/**
	 * すべての商品のリストを返します。
	 * @return 商品リスト
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public List<ProductBean> selectAll() throws SQLException, ClassNotFoundException {
		List<ProductBean> productList = new ArrayList<ProductBean>();
		
		try (Connection con = ConnectionManager.getConnection();
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM products")) {
			while (rs.next()) {
				String productId = rs.getString("product_id");
				String productName = rs.getString("product_name");
				int price = rs.getInt("price");
				int quantity = rs.getInt("quantity");
				String description = rs.getString("description");
				String categoryId = rs.getString("category_id");
				ProductBean product = new ProductBean();
				product.setProductId(productId);
				product.setProductName(productName);
				product.setPrice(price);
				product.setQuantity(quantity);
				product.setDescription(description);
				product.setCategoryId(categoryId);
				productList.add(product);
			}
		}
		
		return productList;
	}
	
	/**
	 * 入力された商品情報を登録します
	 * @param product 商品オブジェクト
	 * @return 処理件数
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public int insert(ProductBean product) throws SQLException, ClassNotFoundException {
		int processingNumber = 0;
		String sql = "INSERT INTO products (product_id, product_name, price, quantity, description, category_id) VALUES (?, ?, ?, ?, ?, ?)";
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			
			String productId = product.getProductId();
			String productName = product.getProductName();
			int price = product.getPrice();
			int quantity = product.getQuantity();
			String description = product.getDescription();
			String categoryId = product.getCategoryId();
			
			pstmt.setString(1, productId);
			pstmt.setString(2, productName);
			pstmt.setInt(3, price);
			pstmt.setInt(4, quantity);
			pstmt.setString(5, description);
			pstmt.setString(6, categoryId);
			
			processingNumber = pstmt.executeUpdate();
		}
		
		return processingNumber;
	}
	
	/**
	 * 商品IDをキーにして商品情報を削除します
	 * @param produtId 商品ID
	 * @return 処理件数
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public int delete(String productId) throws SQLException, ClassNotFoundException {
		int processingNumber = 0;
		String sql = "DELETE FROM products WHERE product_id = ?";
		
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			
			pstmt.setString(1, productId);
			processingNumber = pstmt.executeUpdate();
		}
		
		return processingNumber;
	}
	
	/**
	 * 商品IDをキーにして商品情報を取得します
	 * @param productID 商品ID
	 * @return 商品オブジェクト
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public ProductBean select(String productId) throws SQLException, ClassNotFoundException {
		ProductBean product = new ProductBean();
		String sql = "SELECT * FROM products WHERE product_id = ?";
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			
			pstmt.setString(1, productId);
			ResultSet rs = pstmt.executeQuery();
			// 結果セットを操作して商品情報を取得
			if (rs.next()) {
				product.setProductId(rs.getString("product_id"));
				product.setProductName(rs.getString("product_name"));
				product.setPrice(rs.getInt("price"));
				product.setQuantity(rs.getInt("quantity"));
				product.setDescription(rs.getString("description"));
				product.setCategoryId(rs.getString("category_id"));
				
			}
		}
		
		return product;
	}
	
	/**
	 * 指定された内容の商品情報を更新します
	 * @param product 商品オブジェクト
	 * @return 処理件数
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public int update(ProductBean product) throws SQLException, ClassNotFoundException {
		int processingNumber = 0;
		String sql = "UPDATE products SET product_name = ?, price = ?, quantity = ?, description = ?, category_id = ? WHERE product_id = ?";
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			
			String productId = product.getProductId();
			String productName = product.getProductName();
			int price = product.getPrice();
			int quantity = product.getQuantity();
			String description = product.getDescription();
			String categoryId = product.getCategoryId();
			
			pstmt.setString(1, productName);
			pstmt.setInt(2, price);
			pstmt.setInt(3, quantity);
			pstmt.setString(4, description);
			pstmt.setString(5, categoryId);
			pstmt.setString(6, productId);
			
			processingNumber = pstmt.executeUpdate();
		}
		
		return processingNumber;
	}
}
