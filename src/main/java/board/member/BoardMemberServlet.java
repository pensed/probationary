package board.member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BoardMemberServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
//	@Override
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
//	throws ServletException, IOException {
//		request.setCharacterEncoding("utf-8");
//	    response.setContentType("text/html;charset=utf-8");
//		String cookie = "";
//		Cookie[] list =request.getCookies();
//		cookie = BoardMemberDAO.getCookieValue(list);
//		
//		if(cookie != "") {
//			response.sendRedirect("BoardListForm.jsp");
//				
//		} else {				
//			response.sendRedirect("LoginForm.jsp");
//		}
//	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String user_id = request.getParameter("id");
		String user_pwd = request.getParameter("pwd");
		
		BoardMemberVO memberVO = new BoardMemberVO();
		memberVO.setId(user_id);
		memberVO.setPwd(user_pwd);
		BoardMemberDAO dao = new BoardMemberDAO();
		boolean result = dao.isExisted(memberVO);
		
		if (result) {		// 아이디와 비밀번호가 같을때
			//쿠키를 생성하여 저장한다.
			Cookie cookie = new Cookie("AUTH", user_id);
			cookie.setMaxAge(60*30);
			cookie.setPath("/");
			response.addCookie(cookie);
			System.out.println("쿠키생성 완료(30분)");
			out.print("alert(\"로그인 성공!!\");");
			response.sendRedirect("BoardListForm.jsp");
		} else {			// 아이디와 비밀번호가 다를때
			//로그인 페이지를 호출한다.
			response.sendRedirect("BoardLoginForm.jsp");
		}
	}
	
//	private void doHandle(HttpServletRequest request, HttpServletResponse response)
//	throws ServletException, IOException {
//		request.setCharacterEncoding("utf-8");
//		response.setContentType("text/html;charset=utf-8");
//		String user_id = request.getParameter("id");
//		String user_pwd = request.getParameter("pwd");
//		
//		BoardMemberVO memberVO = new BoardMemberVO();
//		memberVO.setId(user_id);
//		memberVO.setPwd(user_pwd);
//		BoardMemberDAO dao = new BoardMemberDAO();
//		boolean result = dao.isExisted(memberVO);
//		
//		if (result) {		// 아이디와 비밀번호가 같을때
//			//쿠키를 생성하여 저장한다.
//			Cookie cookie = new Cookie("AUTH", user_id);
//			cookie.setMaxAge(60*30);
//			cookie.setPath("/");
//			response.addCookie(cookie);
//			System.out.println("쿠키생성 완료(30분)");
//			response.sendRedirect("BoardListForm.jsp");
//		} else {			// 아이디와 비밀번호가 다를때
//			//로그인 페이지를 호출한다.
//			response.sendRedirect("BoardLoginForm.jsp");
//		}
//	}
}