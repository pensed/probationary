package board.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBUtil {
	public final static String DB_DIRECT_USED_KEY="DB.DIRECT.USED.KEY";
	final static String DB_URL="jdbc:oracle:thin:@10.89.82.65:1522:DSCPDB";
	final static String DB_USER="jhyeom";
	final static String DB_PASS="jhyeom";

	public static Connection getConnection() throws ClassNotFoundException, NamingException, SQLException {
//		if(Objects.equals(System.getProperty(DB_DIRECT_USED_KEY),"Y"))
			return getDirect();
//		else
//			return getDataSource();
	}
	
	private static Connection getDataSource() throws NamingException, SQLException {
		Context env = (Context) new InitialContext().lookup("java:comp/env");
		DataSource _pool = (DataSource) env.lookup("jdbc/oracle");
		return _pool.getConnection();
	}
	
	private static Connection getDirect() throws NamingException, SQLException, ClassNotFoundException {
		
	     Class.forName("oracle.jdbc.OracleDriver"); 
//	           forname(string classname) => 동적 클래스 로드 
//	     	   		  ("oracle.jdbc.OracleDriver") => oracleDriver이라는 클래스를 로드하는것
		return  DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
//					  		  getConnection => 지정된 데이터베이스 URL에 대한 연결 설정 시도
	}		
}
