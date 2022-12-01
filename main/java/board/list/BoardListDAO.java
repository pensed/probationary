package board.list;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


import board.db.DBUtil;



public class BoardListDAO {
	
	private Connection con;
	private PreparedStatement pstmt;
	
	public List<BoardListVO> listBoard() {
		List<BoardListVO> list = new ArrayList<>();
		try {
			
			con = DBUtil.getConnection();
			String query = 	"SELECT "
								+ "num, "
								+ "writer, "
								+ "title, "
								+ "create_day, "
								+ "modify_day, "
								+ "cnt "
						  + "FROM board ";
			System.out.println("prepareStatememt: " + query);
			
			pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				int num = rs.getInt("num");
				String writer = rs.getString("writer");
				String title = 	rs.getString("title");
				Date date = 	rs.getDate("create_day");
				int cnt = 		rs.getInt("cnt");
				if(rs.getDate("modify_day")!= null)
					date = 		rs.getDate("modify_day");
				
				BoardListVO vo = new BoardListVO();
				vo.setNum(num);
				vo.setWriter(writer);
				vo.setTitle(title);
				vo.setDate(date);
				vo.setCnt(cnt);
				list.add(vo);
			}
			rs.close();
			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
