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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException {
		doHandle(request, response);
	}
	
	private void doHandle(HttpServletRequest request,HttpServletResponse response)  
	throws ServletException, IOException {
		
		System.out.println("BoardRegisterDAO 사용하기");
		
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
	    System.out.println("BoardListVO 보내기 성공");
	}
}
