package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.Authentication;
import model.entity.UserBean;

/**
 * Servlet implementation class UserLoginServlet
 */
@WebServlet("/user-login-servlet")
public class UserLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("user-login-form.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// フォームから値を取得
		String userEmail = request.getParameter("userEmail");
		String password = request.getParameter("password");
		
		Authentication auth = new Authentication();
		UserBean user = null;
		
		try {
			// ユーザー情報の取得
			user = auth.login(userEmail, password);
			
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "ログインに失敗しました。時間をおいて再度お試しください。");
			RequestDispatcher rd = request.getRequestDispatcher("user-login-form.jsp");
			rd.forward(request, response);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "データベース接続に失敗しました。時間をおいて再度お試しください。");
			RequestDispatcher rd = request.getRequestDispatcher("user-login-form.jsp");
			rd.forward(request, response);
		}
		
		if (user != null) {
			// 認証成功
			// セキュリティのためパスワード削除
			user.setPassword(null);
			
			// セッション取得＆保存
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", user);
			
			response.sendRedirect("menu.jsp");
			
		} else {
			// 認証失敗
			request.setAttribute("errorMessage", "メールアドレスまたはパスワードが違います。");
			RequestDispatcher rd = request.getRequestDispatcher("user-login-form.jsp");
			rd.forward(request, response);
		}
	}

}
