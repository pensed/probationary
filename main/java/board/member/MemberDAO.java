package board.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import board.db.DBUtil;

public class MemberDAO {
	public boolean isExisted(MemberVO memberVO) {
		memberVO.setRememberId(false);
		String id = memberVO.getId();
		String pwd = memberVO.getPwd();
		
		try {
			Connection con = DBUtil.getConnection();
			String query = "SELECT "
						     + "DECODE(COUNT(*), 1 , 'true', 'false') as result "
						 + "FROM boarduser "
						 + "WHERE id = ? pwd = ? ";
			System.out.println(query);
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1,  id);
			pstmt.setString(2,  pwd);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			memberVO.setRememberId(Boolean.parseBoolean(rs.getString("result")));
			System.out.println("result = " + memberVO.getRememberId());
		}catch (Exception e) {
			e.printStackTrace();
		}
		return memberVO.getRememberId();
	}
	
}
