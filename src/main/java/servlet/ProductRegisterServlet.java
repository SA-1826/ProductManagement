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
 * Servlet implementation class ProductRegisterServlet
 */
@WebServlet("/product-register-servlet")
public class ProductRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductRegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<CategoryBean> categoryList = null;
		CategoryDAO dao = new CategoryDAO();
		try {
			categoryList = dao.selectAll();
			
			request.setAttribute("categoryList", categoryList);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/product-register-form.jsp");
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
		
		Map<String, String> errors = new HashMap<>();
		
		String productId = request.getParameter("productId");
		String productName = request.getParameter("productName");
		String priceStr = request.getParameter("price");
		String quantityStr = request.getParameter("quantity");
		String description = request.getParameter("description");
		String categoryId = request.getParameter("categoryId");
		
		if (productId == null || productId.trim().isEmpty()) {
			errors.put("productId", "商品IDは必須です");
		}
		
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
		
		if (!errors.isEmpty()) {
			request.setAttribute("errors", errors);
			
			ProductBean product = new ProductBean();
			product.setProductId(productId);
			product.setProductName(productName);
			product.setDescription(description);
			product.setCategoryId(categoryId);
			
			request.setAttribute("product", product);
			
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
		    
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/product-register-form.jsp");
			rd.forward(request, response);
			return;
		}
		
		ProductBean product = new ProductBean();
		product.setProductId(productId);
		product.setProductName(productName);
		product.setPrice(Integer.parseInt(priceStr));
		product.setQuantity(Integer.parseInt(quantityStr));
		product.setDescription(description);
		product.setCategoryId(categoryId);
		
		request.setAttribute("product", product);
		
		ProductDAO dao = new ProductDAO();
		int processingNumber = 0;
		
		try {
			processingNumber = dao.insert(product);
			
			if (processingNumber > 0) {
				HttpSession session = request.getSession();
				session.setAttribute("flashMessage", "商品ID：" + product.getProductId() + "／商品名：" + product.getProductName() + " を登録しました");
				
				response.sendRedirect("product-list-servlet");
				
				// 登録内容確認画面への遷移を入れるなら下記利用
				// request.setAttribute("processingNumber", processingNumber);
				// RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/product-register.jsp");
				// rd.forward(request, response);
				
			} else {
				request.setAttribute("errorMessage", "商品の登録に失敗しました。入力内容を確認してください。");
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/error-register.jsp");
				rd.forward(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			
			String message;
			if (e.getMessage().contains("Duplicate entry")) {
				message = "このIDは既に登録されています。別のIDを指定してください。";
			} else {
				message = "データベース処理中にエラーが発生しました。時間をおいて再度お試しください。";
			}
			
			request.setAttribute("errorMessage", message);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/error-register.jsp");
			rd.forward(request, response);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			
			request.setAttribute("errorMessage", "データベース接続に失敗しました。時間をおいて再度お試しください。");
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/error-db.jsp");
			rd.forward(request, response);
		}
	}

}
