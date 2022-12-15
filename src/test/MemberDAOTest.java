import org.junit.jupiter.api.Test;

import board.db.DBUtil;
import board.member.BoardMemberVO;

class MemberDAOTest {

	@Test
	void equalUser() {
		System.setProperty(DBUtil.DB_DIRECT_USED_KEY,"Y");
		BoardMemberVO member = new BoardMemberVO();
		
		member.setId("kim");
		member.setPwd("1234");
		
		//System.out.println(BoardMemberDAO.isExisted(member));
	}

}
