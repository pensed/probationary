package board.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.Boolean;
import java.util.Objects;
public class BoardFilter implements Filter {
	private FilterConfig filterConfig;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	throws IOException, ServletException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		HttpServletRequest httpRequest   = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		String uri = httpRequest.getRequestURI();
		HttpSession httpSession = httpRequest.getSession();
		Boolean isLogin= (Boolean) httpSession.getAttribute("isLogon");
		
		switch(uri) {
			case "/BoardArticleDeleteForm.jsp":
			case "/BoardArticleUpdateForm.jsp":
			case "/boardUpdate":
			case "/boardRegister":
				if(Objects.nonNull(isLogin) && isLogin) {
					chain.doFilter(request, response);
				}else {
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("/BoardLoginForm.jsp");
					requestDispatcher.forward(httpRequest, httpResponse);
				}
				break;
			default:
				chain.doFilter(request, response);
				break;
		}
	}
	
	@Override
	public void destroy() {
	}
	
}
