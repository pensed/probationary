package board.list;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import board.db.DBUtil;

public class BoardListDAO {
	public static int getTotal() {
		int result = -1;
		System.setProperty(DBUtil.DB_DRIECT_USED_KEY, "Y");
		String query = "SELECT COUNT(*) as total"
					 + " FROM board";
		try ( Connection con = DBUtil.getConnection();
				  PreparedStatement pstmt = con.prepareStatement(query);
			      ResultSet rs = pstmt.executeQuery();
				){
			if(rs.next()) {
				result = (int)(rs.getInt("total")/10+1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static List<BoardListVO> listBoard(int page) {
		
		List<BoardListVO> list = new ArrayList<>();
		String query = 	"SELECT "
						 + "num, "
						 + "writer, "
						 + "title, "
						 + "create_day, "
						 + "modify_day, "
						 + "cnt "
					   + "FROM board "
					   + "ORDER BY num DESC "
					   + "OFFSET ? ROWS FETCH FIRST 10 ROWS ONLY ";
			System.out.println("prepareStatememt: " + query);
		try { 
			Connection con = DBUtil.getConnection();
		    PreparedStatement pstmt = con.prepareStatement(query);  
		    pstmt.setInt(1,(page-1)*10);
			pstmt.executeUpdate();
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				int num			  = rs.getInt("num");
				String writer     = rs.getString("writer");
				String title      = rs.getString("title");
				Date createDate   = rs.getDate("create_day");
				int cnt 		  = rs.getInt("cnt");
				Date modiftydDate = rs.getDate("modify_day");
				
				BoardListVO vo = new BoardListVO();
				vo.setNum(num);
				vo.setWriter(writer);
				vo.setTitle(title);
				
				if(Objects.nonNull(createDate) && Objects.nonNull(modiftydDate)) {
					if( modiftydDate.getTime() - createDate.getTime() > 0) {
						vo.setDate(modiftydDate);
					}else {
						vo.setDate(createDate);
					}	
				}else if(Objects.nonNull(modiftydDate)) {
					vo.setDate(modiftydDate);
				}else if(Objects.nonNull(createDate)) {
					vo.setDate(createDate);
				}
				vo.setCnt(cnt);
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
}
