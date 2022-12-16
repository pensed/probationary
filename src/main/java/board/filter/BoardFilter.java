package board.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		String id = (String) httpSession.getAttribute("id");
		
		switch(uri) {
			case "/BoardArticleDeleteForm.jsp":
			case "/BoardArticleUpdateForm.jsp":
			case "/boardUpdate":
			case "/boardMember":
			case "/boardRegister":
				if(id==null || id.trim().length() <= 0) {
					httpResponse.sendRedirect("BoardListForm.jsp");
				} 
				break;
			default:
				break;
		}
		chain.doFilter(request, response);
	}
	
	@Override
	public void destroy() {
	}
	
}
