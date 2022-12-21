package board.article;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import board.db.DBUtil;

public class BoardArticleDAO {
	
	public static String getRoot() {
		return "admin";
	}
	public static int getNextNum() {
		int num = 0;
		try {
			Connection con = DBUtil.getConnection();
			String query = "SELECT max(num)+1 AS num "
						 + "FROM board ";
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
	
	public void createArticle(BoardArticleVO boardArticleVO) {
		try {
			Connection con = DBUtil.getConnection();
			
			int num 	  		= getNextNum();
			String writer 		= boardArticleVO.getWriter();
			String title 		= boardArticleVO.getTitle();
			String content 		= boardArticleVO.getContent();
			String isPrivate 	= boardArticleVO.getIsPrivate();
			String query 		= "INSERT INTO board";
				   query += " (num, writer, title, create_day, cnt, content, is_private)";
				   query += " VALUES(?,?,?,sysdate,0,?,?)";
			
			PreparedStatement pstmt = con.prepareStatement(query);
			
			pstmt.setInt(1, num);
			pstmt.setString(2, writer);
			pstmt.setString(3, title);
			pstmt.setString(4, content);
			pstmt.setString(5, isPrivate);
			pstmt.executeUpdate();
			pstmt.close();
			con.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	public static void addCnt(String num) {
		String query = "UPDATE board SET cnt = cnt+1 WHERE num= ?";
		try {
			Connection con = DBUtil.getConnection();
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt = con.prepareCall(query);
			pstmt.setString(1, num);
			pstmt.executeUpdate();
			pstmt.close();
			con.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static BoardArticleVO readArticle(String num) {
		List<BoardArticleVO> list = new ArrayList<>();
		String query = 	"SELECT "
						+ "writer, "
						+ "title, "
						+ "content, "
						+ "is_private "
					  + "FROM board "
					  + "WHERE num = ?";
		BoardArticleVO vo = new BoardArticleVO();		
		vo.setNum("-1");
		vo.setWriter("");
		vo.setTitle("");
		vo.setContent("");
		vo.setIsPrivate("");
		try {
			Connection con = DBUtil.getConnection();
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, num);
			pstmt.executeUpdate();
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			String writer     = rs.getString("writer");
			String title      = rs.getString("title");
			String content	  = rs.getString("content");
			String isPrivate = rs.getString("is_private");

			vo.setNum(num);
			vo.setWriter(writer);
			vo.setTitle(title);
			vo.setContent(content);
			vo.setIsPrivate(isPrivate);
			
			pstmt.close();
			con.close();
			addCnt(num);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vo;
	}
		
	public void updateArticle(BoardArticleVO boardArticleVO) {
		try {
			Connection con = DBUtil.getConnection();
			con.setAutoCommit(false);
			String num 	  		= boardArticleVO.getNum();
			String title 		= boardArticleVO.getTitle();
			String content 		= boardArticleVO.getContent();
			String isPrivate 	= boardArticleVO.getIsPrivate();
			String query 		= "UPDATE board "
				   		 		+ "SET "
				   		 			+ "title = ?, "
				   		 			+ "content = ?, "
				   		 			+ "modify_day = SYSDATE, "
				   		 			+ "is_private = ? "
				   		 		+ "WHERE num = ? ";
			PreparedStatement pstmt = con.prepareStatement(query);
			
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setString(3, isPrivate);
			pstmt.setString(4, num);
			pstmt.executeUpdate();
			pstmt.close();
			con.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
		
	public static void deleteArticle(String num) {
		System.setProperty(DBUtil.DB_DIRECT_USED_KEY, "Y");
		try {
			Connection con = DBUtil.getConnection();
			con.setAutoCommit(false);
			String query = "DELETE FROM board "
						 + "WHERE num = ? ";
			
			PreparedStatement pstmt = con.prepareStatement(query);
			
			pstmt.setString(1, num);
			pstmt.executeUpdate();
			
			pstmt.close();
			con.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

}
