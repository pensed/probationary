package board.article;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import board.db.DBUtil;
import board.register.BoardRegisterVO;

public class BoardArticleDAO {

	public static int getNextNum() {
		int num = 0;
//		System.setProperty(DBUtil.DB_DIRECT_USED_KEY, "Y");
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
//		System.setProperty(DBUtil.DB_DIRECT_USED_KEY, "Y");
		try {
			Connection con = DBUtil.getConnection();
			
			int num 	  		= getNextNum();
			String writer 		= boardArticleVO.getWriter();
			String title 		= boardArticleVO.getTitle();
			String content 		= boardArticleVO.getContent();
			String is_private 	= boardArticleVO.getIs_private();
			String query 		= "INSERT INTO board";
				   query += " (num, writer, title, create_day, cnt, content, is_private)";
				   query += " VALUES(?,?,?,sysdate,0,?,?)";
			
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
	
	public static void addCnt(String num) {
//		System.setProperty(DBUtil.DB_DIRECT_USED_KEY, "Y");
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
//		System.setProperty(DBUtil.DB_DIRECT_USED_KEY, "Y");
		List<BoardArticleVO> list = new ArrayList<>();
		String query = 	"SELECT "
						+ "writer, "
						+ "title, "
						+ "content, "
						+ "is_private "
					  + "FROM board "
					  + "WHERE num = ?";
		//게시글 조회수 추가 쿼리
		BoardArticleVO vo = new BoardArticleVO();		
		vo.setNum("-1");
		vo.setWriter("");
		vo.setTitle("");
		vo.setContent("");
		vo.setIs_private("");
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
			String is_private = rs.getString("is_private");

			vo.setNum(num);
			vo.setWriter(writer);
			vo.setTitle(title);
			vo.setContent(content);
			vo.setIs_private(is_private);
			
			pstmt.close();
			con.close();
			addCnt(num);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vo;
	}
		
	public void updateArticle(BoardArticleVO boardArticleVO) {
//		System.setProperty(DBUtil.DB_DIRECT_USED_KEY, "Y");
		try {
			Connection con = DBUtil.getConnection();
//			con.setAutoCommit(false);
			String num 	  		= boardArticleVO.getNum();
			String title 		= boardArticleVO.getTitle();
			String content 		= boardArticleVO.getContent();
			String is_private 	= boardArticleVO.getIs_private();
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
			pstmt.setString(3, is_private);
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
//			con.setAutoCommit(false);
			String query = "DELETE FROM board "
						 + "WHERE num = ? ";
			
			System.out.println("prepareStatememt: " + query + num);
			PreparedStatement pstmt = con.prepareStatement(query);
			
			pstmt.setString(1, num);
			pstmt.executeUpdate();
//			con.setAutoCommit(true);
			
			pstmt.close();
			con.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

}
