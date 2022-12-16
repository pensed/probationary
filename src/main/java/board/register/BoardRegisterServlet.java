package board.register;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.article.BoardArticleDAO;
import board.article.BoardArticleVO;

public class BoardRegisterServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException {
		doHandle(request, response);
	}
	
	private void doHandle(HttpServletRequest request,HttpServletResponse response)  
	throws ServletException, IOException {
		
	    BoardArticleDAO dao=new BoardArticleDAO();
	    String command=request.getParameter("command");
	      
	    if(command!= null && command.equals("addBoard")){
	    	String writer	  =request.getParameter("writer");
			String title	  =request.getParameter("title");
			String content	  =request.getParameter("content");
			String isPrivate  =request.getParameter("inputCheck").toUpperCase();
			BoardArticleVO vo=new BoardArticleVO();
			 				vo.setWriter(writer);
			 				vo.setTitle(title);
			 				vo.setContent(content);
			 				vo.setIsPrivate(isPrivate);
		   dao.createArticle(vo);
	    }
	    System.out.println("BoardArticleVO 보내기 성공");
	    response.sendRedirect("BoardListForm.jsp");
	}
}
