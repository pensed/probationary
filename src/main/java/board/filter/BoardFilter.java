package board.filter;

import java.io.IOException;

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
	private FilterConfig fc;

	@Override
	public void init(FilterConfig fc) throws ServletException {
		this.fc = fc;
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		String uri = httpRequest.getRequestURI();
		HttpSession httpSession = httpRequest.getSession();
		String id = (String) httpSession.getAttribute("id");
		
		if(uri.contains("Delete")||uri.contains("Update")) {
			if(id==null || id.trim().length() <=0) {
				httpResponse.sendRedirect("/BoardListForm.jsp");
			}
		}
		chain.doFilter(request, response);
	}
	
	@Override
	public void destroy() {
	}
	
}