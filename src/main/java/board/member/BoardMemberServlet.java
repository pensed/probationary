package board.member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BoardMemberServlet extends HttpServlet {
	
	private Logger logger = LogManager.getLogger(BoardMemberServlet.class);
	private static final long serialVersionUID = 1L;
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException {
		doHandle(request, response);
	}
	
	private void doHandle(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		BoardMemberVO memberVO = new BoardMemberVO();
		memberVO.setId(id);
		memberVO.setPwd(pwd);
		BoardMemberDAO dao = new BoardMemberDAO();
		boolean result = dao.isExisted(memberVO);
		
		if (result) {		
			HttpSession session = request.getSession();
			session.setAttribute("isLogon", true);
			session.setAttribute("id", id);
			response.sendRedirect("BoardListForm.jsp");
		} else {		
			HttpSession session = request.getSession();
			session.setAttribute("isLogon", false);
			response.sendRedirect("BoardLoginForm.jsp");
		}
	}
}