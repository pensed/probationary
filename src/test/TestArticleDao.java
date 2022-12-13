import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import board.article.BoardArticleDAO;
import board.article.BoardArticleVO;
import board.db.DBUtil;

class TestArticleDao {
	
	@BeforeAll
	void beforeAll() {
		System.setProperty(DBUtil.DB_DIRECT_USED_KEY,"Y");
	}
	
	@AfterEach
	void readArticle() {
		int a = BoardArticleDAO.getNextNum();
		BoardArticleVO afterBoard 
		= BoardArticleDAO.readArticle( Integer.toString(a));
		
			System.out.println("작성자: " + afterBoard.getWriter());
			System.out.println("공개여부: " + afterBoard.getIs_private());
			System.out.println("제목: " + afterBoard.getTitle());
			System.out.println("내용: " + afterBoard.getContent());
	}
	
	@Test
	void createArticle() {
		
		String writer =	"kim";
		String title = "글생성테스트제목";
		String content = "글생성테스트내용";
		String is_private = "N";
		
		if(is_private == "Y" || is_private == "y") {
			is_private = "Y";
		} else {
			is_private = "N";
		}
		
		BoardArticleDAO dao=new BoardArticleDAO();
		BoardArticleVO vo = new BoardArticleVO();
					   vo.setWriter(writer);
					   vo.setTitle(title);
					   vo.setContent(content);
					   vo.setIs_private(is_private);
		dao.createArticle(vo);
		
		System.out.println(vo.getWriter());
		System.out.println(vo.getTitle());
		System.out.println(vo.getContent());
		System.out.println(vo.getIs_private());
	}
	
	@Test
	void updateArticle() {
		
		int num 	  	  = BoardArticleDAO.getNextNum();
		String title 	  = "제목수정테스트";
		String content 	  = "내용수정테스트";
		String is_private = "N";
		
		if(is_private == "Y" || is_private == "y") {
			is_private = "Y";
		} else {
			is_private = "N";
		}
		
		BoardArticleDAO dao = new BoardArticleDAO();
		BoardArticleVO vo = new BoardArticleVO();
					   vo.setNum( Integer.toString(num));
					   vo.setTitle(title);
					   vo.setContent(content);
					   vo.setIs_private(is_private);
		dao.updateArticle(vo);			   
		
		System.out.println(vo.getNum());
		System.out.println(vo.getTitle());
		System.out.println(vo.getContent());
		System.out.println(vo.getIs_private());
	}

	@Test
	void deleteArticle() {
		
		BoardArticleDAO dao = new BoardArticleDAO();
		BoardArticleVO vo = new BoardArticleVO();
					   vo.setNum(Integer.toString(BoardArticleDAO.getNextNum()));
		dao.deleteArticle(vo.getNum());
	}	
}
