package board.register;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;



import board.db.DBUtil;

public class BoardRegisterDAO {

	public int getNextNum() {
		int num = 0;
		System.setProperty(DBUtil.DB_DRIECT_USED_KEY, "Y");
		try {
			Connection con = DBUtil.getConnection();
			String query = "SELECT max(num)+1 AS num "
						 + "FROM board ";
			System.out.println("prepareStatememt: " + query);
			PreparedStatement pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				num = rs.getInt("num");
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return num;
	}
	public void addBoard(BoardRegisterVO boardRegisterVO)
	{
		System.setProperty(DBUtil.DB_DRIECT_USED_KEY, "Y");
		try {
			Connection con = DBUtil.getConnection();
			
			int num 	  		= getNextNum();
			String writer 		= boardRegisterVO.getWriter();
			String title 		= boardRegisterVO.getTitle();
			String content 		= boardRegisterVO.getContent();
			String is_private 	= boardRegisterVO.getIs_private();
			String query 		= "INSERT INTO board";
				   query += " (num, writer, title, create_day, cnt, content, is_private)";
				   query += " VALUES(?,?,?,sysdate,0,?,?)";
			
			System.out.println("prepareStatememt: " + query);
			
			PreparedStatement pstmt = con.prepareStatement(query);
			
			pstmt.setInt(1, num);
			pstmt.setString(2, writer);
			pstmt.setString(3, title);
			pstmt.setString(4, content);
			pstmt.setString(5, is_private);
			pstmt.executeUpdate();
			pstmt.close();
			con.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}