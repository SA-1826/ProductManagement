package model.entity;

import java.io.Serializable;

public class UserBean implements Serializable  {
	/**
	 * ユーザーID
	 */
	private String userId;
	
	/**
	 * ユーザー名
	 */
	private String userName;
	
	/**
	 * ユーザーメールアドレス
	 */
	private String userEmail;
	
	/**
	 * パスワード
	 */
	private String password;
	
	/**
	 * UserBeanを構築します
	 */
	public UserBean() {
		
	}
	
	/**
	 * フィールドuserIdの値を返します
	 * @return ユーザーID
	 */
	public String getUserId() {
		return userId;
	}
	
	/**
	 * フィールドuserIdの値を設定します
	 * @param userId ユーザーID
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	/**
	 * フィールドuserNameの値を返します
	 * @return ユーザー名
	 */
	public String getUserName() {
		return userName;
	}
	
	/**
	 * フィールドuserNameの値を設定します
	 * @param userName ユーザー名
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	/**
	 * フィールドuserEmailの値を返します
	 * @return ユーザーメールアドレス
	 */
	public String getUserEmail() {
		return userEmail;
	}
	
	/**
	 * フィールドuserEmailの値を設定します
	 * @param userEmail ユーザーメールアドレス
	 */
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	
	/**
	 * フィールドpasswordの値を返します
	 * @return パスワード
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * フィールドpasswordの値を設定します
	 * @param password パスワード
	 */
	public void setPassword(String password) {
		this.password = password;
	}
}
