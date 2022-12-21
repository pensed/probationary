package board.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.Cookie;

import board.db.DBUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BoardMemberDAO {
	private static Logger logger = LogManager.getLogger(BoardMemberDAO.class);
	public static boolean isExisted(BoardMemberVO memberVO) {
		String id = memberVO.getId();
		String pwd = memberVO.getPwd();
		
		try {
			Connection con = DBUtil.getConnection();
			String query = "SELECT "
						     + "DECODE(COUNT(*), 1 , 'true', 'false') as result "
						 + "FROM boarduser "
						 + "WHERE id = ? AND pwd = ? ";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1,  id);
			pstmt.setString(2,  pwd);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				boolean isExisted = rs.getBoolean("result");
				memberVO.setRememberId(isExisted);
			}
		}catch (Exception e) {
			logger.error("Login error",e);
		}
		return memberVO.getRememberId();
	}
	
}
