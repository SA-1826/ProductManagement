package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.entity.UserBean;

/**
 * userテーブルのDAOクラスです。
 */
public class UserDAO {
	/**
	 * ユーザー情報を参照します。
	 * @param userEmail
	 * @param password
	 * @return UserBean
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public UserBean findByEmailAndPassword(String userEmail, String password) {
		String sql = "SELECT * FROM user WHERE user_email = ? AND password = ?";
		
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			
			pstmt.setString(1, userEmail);
			pstmt.setString(2, password);
			
			try (ResultSet rs = pstmt.executeQuery()) {
				
				if (rs.next()) {
					UserBean user = new UserBean();
					
					user.setUserId(rs.getString("user_id"));
					user.setUserName(rs.getString("user_name"));
					user.setUserEmail(rs.getString("user_email"));
					user.setPassword(rs.getString("password"));
					
					return user;
				}
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		// 該当なし or エラー時
		return null;
	}
}
