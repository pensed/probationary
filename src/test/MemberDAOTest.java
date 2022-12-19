import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import board.member.BoardMemberDAO;
import board.member.BoardMemberVO;

class MemberDAOTest {
	
	private Logger logger = LogManager.getLogger(LogTest.class);
	
	@Test
	void equalUser() {
		//System.setProperty(DBUtil.DB_DIRECT_USED_KEY,"Y");
		BoardMemberVO member = new BoardMemberVO();
		
		member.setId("kim");
		member.setPwd("1234");
		
		logger.info(BoardMemberDAO.isExisted(member));
	}

}
