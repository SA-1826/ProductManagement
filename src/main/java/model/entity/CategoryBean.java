package model.entity;

public class CategoryBean {
	/**
	 * カテゴリID
	 */
	private String categoryId;
	
	/**
	 * カテゴリ名
	 */
	private String categoryName;
	
	/**
	 * CtegoryBeanを構築します
	 */
	public CategoryBean() {
		
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
