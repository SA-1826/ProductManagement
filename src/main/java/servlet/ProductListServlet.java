package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.CategoryDAO;
import model.dao.ProductDAO;
import model.entity.CategoryBean;
import model.entity.ProductBean;

/**
 * Servlet implementation class ProductListServlet
 */
@WebServlet("/product-list-servlet")
public class ProductListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 商品リストオブジェクトを生成
		List<ProductBean> productList = null;
		
		ProductDAO dao = new ProductDAO();
		try {
			// 商品リスト情報を取得
			productList = dao.selectAll();
			// リクエストスコープに商品リストを設定
			request.setAttribute("productList", productList);
			
			// カテゴリリスト情報を取得
			List<CategoryBean> categoryList = null;
			CategoryDAO categoryDao = new CategoryDAO();
			categoryList = categoryDao.selectAll();
			// リクエストスコープにカテゴリリストを設定
			request.setAttribute("categoryList", categoryList);
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/product-list.jsp");
			rd.forward(request, response);
			
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			
			request.setAttribute("errorMessage", "データベース接続に失敗しました。時間をおいて再度お試しください。");
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/error-db.jsp");
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		// カテゴリでフィルタ
		// 選択したカテゴリIDを取得
		String categoryId = request.getParameter("categoryId");
		// 商品リストオブジェクトを生成
		List<ProductBean> productList = null;
		// 商品DAOを生成
		ProductDAO dao = new ProductDAO();
		
		try {
			if (categoryId != null && !categoryId.isEmpty()) {
				// カテゴリIDから商品リスト情報を取得
				productList = dao.selectAllByCategoryId(categoryId);
			} else {
				// すべての商品リスト情報を取得
				productList = dao.selectAll();
			}
			
			// リクエストスコープに商品リストを設定
			request.setAttribute("productList", productList);
			
			// カテゴリリスト情報を取得
			List<CategoryBean> categoryList = null;
			CategoryDAO categoryDao = new CategoryDAO();
			categoryList = categoryDao.selectAll();
			// リクエストスコープにカテゴリリストを設定
			request.setAttribute("categoryList", categoryList);
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/product-list.jsp");
			rd.forward(request, response);
			
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			
			request.setAttribute("errorMessage", "データベース接続に失敗しました。時間をおいて再度お試しください。");
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/error-db.jsp");
			rd.forward(request, response);
		}
	}

}
