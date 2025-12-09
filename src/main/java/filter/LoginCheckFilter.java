package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/*")
public class LoginCheckFilter implements Filter {
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		// 現在のパス
		String path = req.getServletPath();
		
		// ログイン不要のパスを定義
		boolean isLoginPage = path.equals("/user-login-form.jsp");
		boolean isLoginServlet = path.equals("/user-login-servlet");
		boolean isCssJs = path.endsWith(".css") || path.endsWith(".js");
		
		if (isLoginPage || isLoginServlet || isCssJs) {
			chain.doFilter(request, response);
			return;
		}
		
		HttpSession session = req.getSession(false);
		Object user = (session != null) ? session.getAttribute("loginUser") : null;
		
		if (user == null) {
			HttpSession sessionMessage = req.getSession();
			sessionMessage.setAttribute("loginError", "ログインが必要です。");
			res.sendRedirect("user-login-form.jsp");
			return;
		}
		
		chain.doFilter(request, response);
	}
}
