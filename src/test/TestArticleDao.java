import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import board.article.BoardArticleDAO;
import board.article.BoardArticleVO;
import board.db.DBUtil;

class TestArticleDao {
	private Logger logger = LogManager.getLogger(LogTest.class);
	@BeforeAll
	void beforeAll() {
	}
	
	@AfterEach
	void readArticle() {
		int a = BoardArticleDAO.getNextNum();
		BoardArticleVO afterBoard 
		= BoardArticleDAO.readArticle( Integer.toString(a));
		
			logger.info("작성자: " + afterBoard.getWriter());
			logger.info("공개여부: " + afterBoard.getIsPrivate());
			logger.info("제목: " + afterBoard.getTitle());
			logger.info("내용: " + afterBoard.getContent());
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
					   vo.setIsPrivate(is_private);
		dao.createArticle(vo);
		
		logger.info(vo.getWriter());
		logger.info(vo.getTitle());
		logger.info(vo.getContent());
		logger.info(vo.getIsPrivate());
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
					   vo.setIsPrivate(is_private);
		dao.updateArticle(vo);			   
		
		logger.info(vo.getNum());
		logger.info(vo.getTitle());
		logger.info(vo.getContent());
		logger.info(vo.getIsPrivate());
	}

	@Test
	void deleteArticle() {
		
		BoardArticleVO vo = new BoardArticleVO();
					   vo.setNum(Integer.toString(BoardArticleDAO.getNextNum()));
		BoardArticleDAO.deleteArticle(vo.getNum());
	}	
}
