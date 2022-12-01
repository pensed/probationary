package board.list;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/boardlist")
public class BoardListServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		BoardListDAO dao = new BoardListDAO();
		List<BoardListVO> list = dao.listBoard();
	
		out.print("<html><body>");
		out.print("<table board=1>"
					+ "<tr>게시판</tr>"
					+ "<tr align='center' bgcolor='lightgray'>");
		out.print ("<td>번호</td><td>작성자</td><td>제목</td><td>작성일</td><td>조회수</td></tr>");
		
		for(int i=0;i<list.size(); i++) {
			BoardListVO boardlistVO = (BoardListVO) list.get(i);
			int num 	  = boardlistVO.getNum();
			String writer = boardlistVO.getWriter();
			String title  = boardlistVO.getTitle();
			Date date 	  = boardlistVO.getDate();
			int cnt 	  = boardlistVO.getCnt();
			out.print("<tr><td>" + num + "</td><td>" 
								 + writer + "</td><td>" 
								 + title + "</td><td>" 
								 + date + "</td><td>" 
								 + cnt + "</td></tr>");
		}
		out.print("</table></body></html>");
	}
}
