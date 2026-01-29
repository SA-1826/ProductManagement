package model.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import model.entity.UserBean;

class LoginServiceTest {

	// 正しいメールアドレスとパスワードでログインできることを確認
	@Test
	void loginSuccess() throws SQLException, ClassNotFoundException {
		Authentication auth = new Authentication();
		UserBean user = auth.login("u00001@sample.com", "password00001");
		
		assertNotNull(user);
		assertEquals("A00001", user.getUserId());
	}
	
	// 誤ったメールアドレスではログインできないことを確認
	@Test
	void wrongEmail() throws SQLException, ClassNotFoundException {
		Authentication auth = new Authentication();
		UserBean user = auth.login("wronguser@sample.com", "password00001");
		
		assertNull(user);
	}
	
	// 誤ったパスワードではログインできないことを確認
		@Test
		void wrongPassword() throws SQLException, ClassNotFoundException {
			Authentication auth = new Authentication();
			UserBean user = auth.login("u00001@sample.com", "wrongpassword");
			
			assertNull(user);
		}

}
