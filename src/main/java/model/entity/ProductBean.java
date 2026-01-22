package model.entity;

public class ProductBean {
	/**
	 * 商品ID
	 */
	private String productId;
	
	/**
	 * 商品名
	 */
	private String productName;
	
	/**
	 * 単価
	 */
	private int price;
	
	/**
	 * 在庫数
	 */
	private int quantity;
	
	/**
	 * 商品説明
	 */
	private String description;
	
	/**
	 * カテゴリID
	 */
	private String categoryId;
	
	/**
	 * カテゴリ名（カテゴリテーブルを結合して取得）
	 */
	private String categoryName;
	
	/**
	 * ProductBeanを構築します
	 */
	public ProductBean() {
		
	}
	
	/**
	 * フィールドproductIdの値を返します
	 * @return 商品ID
	 */
	public String getProductId() {
		return productId;
	}
	
	/**
	 * フィールドproductIdの値を設定します
	 * @param productId 商品ID
	 */
	public void setProductId(String productId) {
		this.productId = productId;
	}
	
	/**
	 * フィールドproductNameの値を返します
	 * @return 商品名
	 */
	public String getProductName() {
		return productName;
	}
	
	/**
	 * フィールドproductNameの値を設定します
	 * @param productName 商品名
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	/**
	 * フィールドpriceの値を返します
	 * @return 単価
	 */
	public int getPrice() {
		return price;
	}
	
	/**
	 * フィールドpriceの値を設定します
	 * @param price 単価
	 */
	public void setPrice(int price) {
		this.price = price;
	}
	
	/**
	 * フィールドquantityの値を返します
	 * @return 在庫数
	 */
	public int getQuantity() {
		return quantity;
	}
	
	/**
	 * フィールドquantityの値を設定します
	 * @param quantity 在庫数
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	/**
	 * フィールドdescriptionの値を返します
	 * @return 商品説明
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * フィールドdescriptionの値を設定します
	 * @param description 商品説明
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * フィールドcategoryIdの値を返します
	 * @return カテゴリID
	 */
	public String getCategoryId() {
		return categoryId;
	}
	
	/**
	 * フィールドcategoryIdの値を設定します
	 * @param categoryId カテゴリID
	 */
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	/**
	 * フィールドcategoryNameの値を返します
	 * @return カテゴリ名
	 */
	public String getCategoryName() {
		return categoryName;
	}
	
	/**
	 * フィールドcategoryNameの値を設定します
	 * @param categoryName カテゴリ名
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
}
