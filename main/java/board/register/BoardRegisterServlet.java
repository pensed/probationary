package board.register;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.list.BoardListDAO;
import board.list.BoardListVO;

public class BoardRegisterServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException {
		doHandle(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException {
		doHandle(request, response);
	}
	
	private void doHandle(HttpServletRequest request,HttpServletResponse response)  
	throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
	    response.setContentType("text/html;charset=utf-8");
	    BoardRegisterDAO dao=new BoardRegisterDAO();
	    String command=request.getParameter("command");
	      
	    if(command!= null && command.equals("addMember")){
	    	String writer	  =request.getParameter("writer");
			String title	  =request.getParameter("title");
			String content	  =request.getParameter("content");
			String is_private=request.getParameter("input_check");
			 
			BoardRegisterVO vo=new BoardRegisterVO();
			 				vo.setWriter(writer);
			 				vo.setTitle(title);
			 				vo.setContent(content);
			 				vo.setIs_private(is_private);
		   dao.addBoard(vo);
	    }
	    //
	    PrintWriter outt = response.getWriter();
		BoardListDAO daoo = new BoardListDAO();
		List<BoardListVO> list = daoo.listBoard();
		
			outt.print("<html><body>");
			outt.print("<table board=1><tr>게시판</tr><tr align='center' bgcolor='lightgray'>");
			outt.print ("<td>번호</td><td>장성자</td><td>제목</td><td>작성일</td><td>조회수</td></tr>");
			
			for(int i=0;i<list.size(); i++) {
				BoardListVO boardlistVO = (BoardListVO) list.get(i);
				
				int num 		= boardlistVO.getNum();
				String writer 	= boardlistVO.getWriter();
				String title 	= boardlistVO.getTitle();
				Date date 		= boardlistVO.getDate();
				int cnt 		= boardlistVO.getCnt();
				
				outt.print("<tr><td>" + num + "</td><td>" 
									  + writer + "</td><td>" 
									  + title + "</td><td>" 
									  + date + "</td><td>" 
									  + cnt + "</td></tr>");
			}
			outt.print("</table></body></html>");   
	   
	}
}
