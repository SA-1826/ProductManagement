package model.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.Test;

import model.entity.ProductBean;

class ProductServiceTest {

	// すべての商品情報をリストとして取得できることを確認
	@Test
	void selectAllProductsExist() throws SQLException, ClassNotFoundException {
		ProductDAO dao = new ProductDAO();
		List<ProductBean> productList = dao.selectAll();
		
		assertNotNull(productList);
		assertFalse(productList.isEmpty(), "商品リストが空でないこと");
	}
	
	// カテゴリIDで指定した商品情報をリストとして取得できることを確認
	@Test
	void selectAllByCategoryIdValidCategory() throws SQLException, ClassNotFoundException {
		ProductDAO dao = new ProductDAO();
		
		String categoryId = "c00003";
		List<ProductBean> productList = dao.selectAllByCategoryId(categoryId);
		
		assertNotNull(productList);
		assertFalse(productList.isEmpty(), "商品リストが空でないこと");
		
		for (ProductBean product : productList) {
			assertEquals(categoryId, product.getCategoryId());
		}
	}
	
	// 新規商品が正しくデータベースに登録されることを確認
	@Test
	void insertSuccess() throws SQLException, ClassNotFoundException {
		ProductDAO dao = new ProductDAO();
		
		ProductBean product = new ProductBean();
		product.setProductId("p99999");
		product.setProductName("JUnitテスト商品");
		product.setPrice(9999);
		product.setQuantity(9);
		product.setDescription("JUnitテスト登録用");
		product.setCategoryId("c00003");
		
		// insertメソッドは、登録成功時は1件、失敗時は0件と返ってくるため結果は整数
		int result = dao.insert(product);
		
		// INSERTが1件行われること
		assertEquals(1, result, "商品が1件登録されること");
		
		// 登録後にデータの取得ができること
		ProductBean inserted = dao.select("p99999");
		assertNotNull(inserted, "登録した商品データが取得できること");
		
		// テスト登録した商品を削除
		dao.delete("p99999");
	}
	
	// 指定した商品IDで商品削除が正しく行われることを確認
	@Test
	void deleteSuccess() throws SQLException, ClassNotFoundException {
		ProductDAO dao = new ProductDAO();
		
		// 削除テスト用商品を登録
		ProductBean product = new ProductBean();
		product.setProductId("p88888");
		product.setProductName("JUnitテスト商品");
		product.setPrice(8888);
		product.setQuantity(8);
		product.setDescription("JUnitテスト削除用");
		product.setCategoryId("c00001");
		
		dao.insert(product);
		
		// 削除実行
		int result = dao.delete("p88888");
		
		// 削除件数の確認
		assertEquals(1, result);
		
		// 本当に削除されたか商品IDによる確認
		ProductBean deleted = dao.select("p88888");
		assertNull(deleted.getProductId());
	}
	
	// 指定した商品IDで商品削除後該当商品が商品一覧に含まれないことを確認
	@Test
	void deleteNotInList() throws SQLException, ClassNotFoundException {
		ProductDAO dao = new ProductDAO();
		
		// 削除テスト用商品を登録
		ProductBean product = new ProductBean();
		product.setProductId("p77777");
		product.setProductName("JUnitテスト商品");
		product.setPrice(7777);
		product.setQuantity(7);
		product.setDescription("JUnitテスト削除後一覧に含まれないこと確認用");
		product.setCategoryId("c00002");
		
		dao.insert(product);
		
		// 削除実行
		dao.delete("p77777");
		
		// 商品一覧（リスト）取得
		List<ProductBean> productList = dao.selectAll();
		
		// 一覧に含まれていないことを確認
		boolean exists = productList.stream().anyMatch(p -> "p77777".equals(p.getProductId()));
		assertFalse(exists);
	}
	
	// 商品編集機能が正しく実行されることを確認
	@Test
	void updateSuccess() throws SQLException, ClassNotFoundException {
		ProductDAO dao = new ProductDAO();
		
		// 編集テスト用データの事前登録
		ProductBean product = new ProductBean();
		product.setProductId("p66666");
		product.setProductName("JUnitテスト商品");
		product.setPrice(6666);
		product.setQuantity(6);
		product.setDescription("JUnitテスト編集用");
		product.setCategoryId("c00004");
		
		dao.insert(product);
		
		// 更新処理
		product.setProductName("JUnitテスト編集後商品");
		product.setPrice(5555);
		product.setQuantity(5);
		product.setDescription("JUnitテスト編集後更新完了");
		product.setCategoryId("c00005");
		
		int result = dao.update(product);
		
		// 更新結果の確認
		assertEquals(1, result, "UPDATEが1件実行されること");
		
		ProductBean updated = dao.select("p66666");
		assertNotNull(updated);
		
		assertEquals("JUnitテスト編集後商品", updated.getProductName());
		assertEquals(5555, updated.getPrice());
		assertEquals(5, updated.getQuantity());
		assertEquals("JUnitテスト編集後更新完了", updated.getDescription());
		assertEquals("c00005", updated.getCategoryId());
		
		// テスト登録した商品を削除
		dao.delete("p66666");
	}

}
