
import static org.junit.Assert.assertSame;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

class TestListDao {
	

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
		BoardArticleVO afterBoard;
		
		System.out.println("Input board num: ");
		
		afterBoard = BoardArticleDAO.readArticle(Integer.toString(BoardArticleDAO.getNextNum()));
			System.out.println("작성자: " + afterBoard.getWriter());
			System.out.println("공개여부: " + afterBoard.getIsPrivate());
			System.out.println("제목: " + afterBoard.getTitle());
			System.out.println("내용: " + afterBoard.getContent());
		
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
			
			BoardArticleVO vo=new BoardArticleVO();
							vo.setWriter(writer);
							vo.setTitle(title);
							vo.setContent(content);
							vo.setIsPrivate(is_private);
			new BoardArticleDAO().createArticle(vo);
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
	    List<BoardListVO> beforeBoard = BoardListDAO.listBoard(1);
	    String writer = request.get("writer");
	    String title = request.get("title");
	    String content = request.get("content");
	    String is_private = request.get("inputCheck");
	    BoardArticleVO vo = new BoardArticleVO();
	    vo.setWriter(writer);
	    vo.setTitle(title);
	    vo.setContent(content);
	    vo.setIsPrivate(is_private);
	    (new BoardArticleDAO()).createArticle(vo);
	    List<BoardListVO> afterBoard = BoardListDAO.listBoard(1);
	    Assertions.assertEquals(beforeBoard.size() + 1, afterBoard.size());
	  }


}
