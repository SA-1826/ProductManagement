package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.CategoryDAO;
import model.dao.ProductDAO;
import model.entity.CategoryBean;
import model.entity.ProductBean;

/**
 * Servlet implementation class ProductEditServlet
 */
@WebServlet("/product-edit-servlet")
public class ProductEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductEditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		// リクエストパラメータから商品IDを取得
		String productId = request.getParameter("productId");
		// ProductDAOのインスタンスを生成
		ProductDAO productDao = new ProductDAO();
		try {
			// 商品IDを使って商品情報を取得
			ProductBean product = productDao.select(productId);
			// リクエストスコープに商品情報を設定
			request.setAttribute("product", product);
			
			// カテゴリリスト情報を取得
			List<CategoryBean> categoryList = null;
			CategoryDAO categoryDao = new CategoryDAO();
			categoryList = categoryDao.selectAll();
			// リクエストスコープにカテゴリリストを設定	
			request.setAttribute("categoryList", categoryList);
			
			// 編集画面へ転送
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/product-edit-form.jsp");
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
		
		// 入力データの確認
		Map<String, String> errors = new HashMap<>();
		
		String productId = request.getParameter("productId");
		String productName = request.getParameter("productName");
		String priceStr = request.getParameter("price");
		String quantityStr = request.getParameter("quantity");
		String description = request.getParameter("description");
		String categoryId = request.getParameter("categoryId");
		
		if (productName == null || productName.trim().isEmpty()) {
			errors.put("productName", "商品名は必須です");
		}
		
		if (priceStr == null || priceStr.trim().isEmpty()) {
			errors.put("price", "単価は必須です");
		} else {
			try {
				int price = Integer.parseInt(priceStr);
				if (price < 0) {
					errors.put("price", "単価は0以上で入力してください");
				}
			} catch (NumberFormatException e) {
				errors.put("price", "単価は数値で入力してください");
			}
		}
		
		if (quantityStr == null || quantityStr.trim().isEmpty()) {
			errors.put("quantity", "在庫数は必須です");
		} else {
			try {
				int quantity = Integer.parseInt(quantityStr);
				if (quantity < 0) {
					errors.put("quantity", "在庫数は0以上で入力してください");
				}
			} catch (NumberFormatException e) {
				errors.put("quantity", "在庫数は数値で入力してください");
			}
		}
		
		if (categoryId == null || categoryId.trim().isEmpty()) {
			errors.put("categoryId", "カテゴリIDは必須です");
		}
		
		// 入力データに不備がある場合
		if (!errors.isEmpty()) {
			// エラーメッセージをリクエストスコープに設定
			request.setAttribute("errors", errors);
			
			// 入力した商品情報を商品オブジェクトとして生成
			ProductBean product = new ProductBean();
			product.setProductId(productId);
			product.setProductName(productName);
			product.setDescription(description);
			product.setCategoryId(categoryId);
			
			request.setAttribute("product", product);
			
			// カテゴリリストの再取得
			List<CategoryBean> categoryList = null;
			CategoryDAO categoryDao = new CategoryDAO();
			try {
				categoryList = categoryDao.selectAll();
				request.setAttribute("categoryList", categoryList);
			} catch (SQLException | ClassNotFoundException e) {
				e.printStackTrace();
				
				request.setAttribute("errorMessage", "データベース接続に失敗しました。時間をおいて再度お試しください。");
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/error-db.jsp");
				rd.forward(request, response);
				return;
			}
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/product-edit-form.jsp");
			rd.forward(request, response);
			return;
		}
		
		// 入力データに不備がない場合
		int price = Integer.parseInt(priceStr);
		int quantity = Integer.parseInt(quantityStr);
		// 商品テーブルの各カラムのデータを入力内容に更新
		ProductBean product = new ProductBean();
		product.setProductId(productId);
		product.setProductName(productName);
		product.setPrice(price);
		product.setQuantity(quantity);
		product.setDescription(description);
		product.setCategoryId(categoryId);
		
		// エラー時にリクエストスコープに商品オブジェクトを持たせる
		request.setAttribute("product", product);
		
		// ProductDAOのインスタンスを生成
		ProductDAO productDao = new ProductDAO();
		int processingNumber = 0;
		try {
			// DAOを使用して更新処理を実行
			processingNumber = productDao.update(product);
			
			if (processingNumber > 0) {
				// 更新処理が成功した場合フラッシュメッセージ表示用セッションを取得
				HttpSession session = request.getSession();
				session.setAttribute("flashMessage", "商品ID：" + product.getProductId() + " の商品情報を更新しました。");
				
				// 商品一覧ページへリダイレクト
				response.sendRedirect("product-list-servlet");
				
			} else {
				// 更新処理に失敗した場合
				// カテゴリリストを再取得して編集画面を再表示
				List<CategoryBean> categoryList = null;
				CategoryDAO categoryDao = new CategoryDAO();
				categoryList = categoryDao.selectAll();
				request.setAttribute("categoryList", categoryList);
				request.setAttribute("errorMessage", "商品情報の更新に失敗しました。入力内容を確認して再度お試しください。");
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/product-edit-form.jsp");
				rd.forward(request, response);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			
			request.setAttribute("errorMessage", "データベース処理中にエラーが発生しました。時間をおいて再度お試しください。");
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/error-db.jsp");
			rd.forward(request, response);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			
			request.setAttribute("errorMessage", "データベース接続に失敗しました。時間をおいて再度お試しください。");
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/error-db.jsp");
			rd.forward(request, response);
		}
	}

}
