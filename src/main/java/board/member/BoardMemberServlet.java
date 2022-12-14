package board.member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BoardMemberServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
//	@Override
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
//	throws ServletException, IOException {
//	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException {
		doHandle(request, response);
	}
	
	private void doHandle(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
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