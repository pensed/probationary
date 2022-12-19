package board.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.Cookie;

import board.db.DBUtil;

public class BoardMemberDAO {
	public static boolean isExisted(BoardMemberVO memberVO) {
//		System.setProperty(DBUtil.DB_DIRECT_USED_KEY, "Y");
		memberVO.setRememberId(false);
		Boolean bl = memberVO.getRememberId();
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
			rs.next();
			bl = rs.getBoolean("result");
			memberVO.setRememberId(bl);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return memberVO.getRememberId();
	}
	
}
