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

import model.dao.ProductDAO;

/**
 * Servlet implementation class ProductDeleteServlet
 */
@WebServlet("/product-delete-servlet")
public class ProductDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		// 削除ボタンを押下した商品の商品IDを取得
		String productId = request.getParameter("productId");
		
		// 商品IDから商品データの削除を実行
		ProductDAO dao = new ProductDAO();
		int processingNumber = 0;
		
		try {
			processingNumber = dao.delete(productId);
			
			if (processingNumber > 0) {
				// 削除処理成功時
				// フラッシュメッセージ表示のためセッション取得
				HttpSession session = request.getSession();
				session.setAttribute("flashMessage", processingNumber + " 件の商品／商品ID：" + productId  +" を削除しました。");
				
				// 商品一覧ページへリダイレクト
				response.sendRedirect("product-list-servlet");
				
			} else {
				// 削除失敗時はメッセージを表示し商品一覧画面へ戻る
				request.setAttribute("errorMessage", "商品の削除に失敗しました。時間をおいて再度お試しください。");
				RequestDispatcher rd = request.getRequestDispatcher("product-list-servlet");
				rd.forward(request, response);
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			
			// データベース接続エラーの場合はエラー画面へ遷移
			request.setAttribute("errorMessage", "データベース接続に失敗しました。時間をおいて再度お試しください。");
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/error-db.jsp");
			rd.forward(request, response);
		}
	}

}
