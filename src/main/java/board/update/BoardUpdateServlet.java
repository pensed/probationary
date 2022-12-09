package board.update;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.article.BoardArticleDAO;
import board.article.BoardArticleVO;

public class BoardUpdateServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException {
		doHandle(request, response);
	}
	
	private void doHandle(HttpServletRequest request,HttpServletResponse response)  
	throws ServletException, IOException {
		
		System.out.println("BoardArticleDAO 사용하기");
		request.setCharacterEncoding("utf-8");
	    response.setContentType("text/html;charset=utf-8");
	    BoardArticleDAO dao=new BoardArticleDAO();
	    String command=request.getParameter("command");
	      
	    if(command!= null && command.equals("updateBoard")){
	    	String num 		  =request.getParameter("num");
	    	String writer	  =request.getParameter("writer");
			String title	  =request.getParameter("title");
			String content	  =request.getParameter("content");
			String is_private=request.getParameter("input_check").toUpperCase();
//			System.out.println(is_private); 
			BoardArticleVO vo=new BoardArticleVO();
							vo.setNum(num);
			 				vo.setWriter(writer);
			 				vo.setTitle(title);
			 				vo.setContent(content);
			 				vo.setIs_private(is_private);
		   dao.updateArticle(vo);
	    }
	    System.out.println("BoardArticleVO 보내기 성공");
	    response.sendRedirect("BoardListForm.jsp");
	}
}