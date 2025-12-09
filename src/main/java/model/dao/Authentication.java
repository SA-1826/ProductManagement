package model.dao;

import java.sql.SQLException;

import model.entity.UserBean;

/**
 * ユーザーの認証管理を行うクラスです。
 */
public class Authentication {

	/**
	 * ログイン認証を行います。
	 * 認証成功：UserBeanを返す
	 * 認証失敗：nullを返す
	 */
	public UserBean login(String userEmail, String password) throws SQLException, ClassNotFoundException {
		UserDAO dao = new UserDAO();
		UserBean user = dao.findByEmailAndPassword(userEmail, password);
		
		return user;
	}

}
