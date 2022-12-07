package board.member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MemberServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String id = "";
		String pwd = "";
		Boolean isLogon = false;
		HttpSession session = request.getSession(false);
		
		if(session != null) {		//세션이 생성이 되었는지
			isLogon = (Boolean)session.getAttribute("isLogon");
			if(isLogon == true) {	//로그인이 되었는지
				id = (String)session.getAttribute("login.id");
				pwd = (String)session.getAttribute("login.pwd");
									//로그인을 하여 세션이 생성되었을 때 무엇을 할지
				
			} else {				//로그인하지 않았을때 로그인페이지로 이동
				response.sendRedirect("login.html");
			}
		} else {					//세션이 없을때 로그인페이지로 이동
			response.sendRedirect("login.html");
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doHandle(request, response);
	}
	
	private void doHandle(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String user_id = request.getParameter("user_id");
		String user_pwd = request.getParameter("user_pwd");
		
		MemberVO memberVO = new MemberVO();
		memberVO.setId(user_id);
		memberVO.setPwd(user_pwd);
		MemberDAO dao = new MemberDAO();
		boolean result = dao.isExisted(memberVO);
		
		if (result) {
			HttpSession session = request.getSession();
			session.setAttribute("isLogon", true);
			session.setAttribute("login.id", user_id);
			session.setAttribute("login.pwd", user_pwd);
		} else {
			response.sendRedirect("login.html");
		}
	}
}