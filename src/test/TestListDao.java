
import static org.junit.Assert.assertSame;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import board.article.BoardArticleDAO;
import board.article.BoardArticleVO;
import board.db.DBUtil;
import board.list.BoardListDAO;
import board.list.BoardListVO;
import board.register.BoardRegisterDAO;
import board.register.BoardRegisterVO;

class TestListDao {
	
	@BeforeAll
	void beforeAll() {
		System.setProperty(DBUtil.DB_DRIECT_USED_KEY, "Y");
	}

	@Test	//페이지수 보기
	void viewPageTotal() {
		System.out.println("총페이지 수는 " 
						   + BoardListDAO.getTotal() 
						   + " 페이지 입니다.");
	}
	
	@Test	//페이지별 목록 보기
	void ViewPageTest() {
		
		
		 List<BoardListVO> afterBoard;
		
		 for(int i=1;i<100;i++) {
			 afterBoard = BoardListDAO.listBoard(i);
			 assertSame(afterBoard.size(), 10);
			 Assert.assertNotEquals(i, i);
			 
			 if(afterBoard.size() == 0) {
				 break;
			 } else {
				 System.out.println((i)+" 페이지");
				 for(BoardListVO vo : afterBoard) {
					 System.out.println(vo.getNum() + ", " 
							 		  + vo.getWriter() + ", " 
							 		  + vo.getTitle() + ", " 
							 		  + vo.getDate() + ", "
							 		  + vo.getCnt());
				 }
			 }
		 }
	}
	
	@Test	//게시글 상세내용 보기
	void ViewArticle() {
		Scanner sc = new Scanner(System.in);
		List<BoardArticleVO> afterBoard;
		
		System.out.println("Input board num: ");
		
		afterBoard = BoardArticleDAO.readArticle(Integer.toString(BoardArticleDAO.getNextNum()));
		for(BoardArticleVO vo : afterBoard) {
			System.out.println("작성자: " + vo.getWriter());
			System.out.println("공개여부: " + vo.getIs_private());
			System.out.println("제목: " + vo.getTitle());
			System.out.println("내용: " + vo.getContent());
		}
		
	}
	
	@Disabled("데이터를 입력한 후 비활성화")
	@Test
	void InsertArticleTest() {
		Map<String,String> request = new HashMap<String,String>();
		
		for(int i=1;i<101;i++) {
			request.put("command", "addMember");
			request.put("num", "12"+i);
			request.put("writer", "kim");
			request.put("title", "테스트"+i);
			request.put("content", "테스트내용입니다."+i);
			request.put("input_check", "N");
			
			String writer	   	= request.get("writer");
			String title	   	= request.get("title");
			String content	   	= request.get("content");
			String is_private 	= request.get("input_check");
			
			BoardRegisterVO vo=new BoardRegisterVO();
							vo.setWriter(writer);
							vo.setTitle(title);
							vo.setContent(content);
							vo.setIs_private(is_private);
			new BoardRegisterDAO().addBoard(vo);
		}
	}
	 @Test
	  void test() {
	    Map<String, String> request = new HashMap<>();
	    request.put("command", "addMember");
	    request.put("writer", "kranian");
	    request.put("title", "This is title");
	    request.put("content", "This is content");
	    request.put("input_check", "N");
	    BoardListDAO boardListDao = new BoardListDAO();
	    List<BoardListVO> beforeBoard = BoardListDAO.listBoard(1);
	    String writer = request.get("writer");
	    String title = request.get("title");
	    String content = request.get("content");
	    String is_private = request.get("input_check");
	    BoardRegisterVO vo = new BoardRegisterVO();
	    vo.setWriter(writer);
	    vo.setTitle(title);
	    vo.setContent(content);
	    vo.setIs_private(is_private);
	    (new BoardRegisterDAO()).addBoard(vo);
	    List<BoardListVO> afterBoard = BoardListDAO.listBoard(1);
	    Assertions.assertEquals(beforeBoard.size() + 1, afterBoard.size());
	  }


}
