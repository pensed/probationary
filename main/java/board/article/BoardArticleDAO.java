package board.article;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import board.db.DBUtil;

public class BoardArticleDAO {
	
	public static List<BoardArticleVO> viewArticle(int num) {
		System.setProperty(DBUtil.DB_DRIECT_USED_KEY, "Y");
		List<BoardArticleVO> list = new ArrayList<>();
		String query = 	"SELECT "
						+ "writer, "
						+ "title, "
						+ "content, "
						+ "is_private "
					  + "FROM board "
					  + "WHERE num = ?";
		System.out.println("PreparedStatement:" + query);
		try {
			Connection con = DBUtil.getConnection();
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String writer     = rs.getString("writer");
				String title      = rs.getString("title");
				String content	  = rs.getString("content");
				String is_private = rs.getString("is_private");
				
				BoardArticleVO vo = new BoardArticleVO();
				vo.setNum(num);
				vo.setWriter(writer);
				vo.setTitle(title);
				vo.setContent(content);
				vo.setIs_private(is_private);
				list.add(vo);
			}
			pstmt.close();
			con.close();
			System.out.println("done");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
		
	}

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
	
}
