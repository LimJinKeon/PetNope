package petnope;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DogManager2 {
	static String driver = "org.mariadb.jdbc.Driver";
	static String DB_NAME = "dog";
	static String DB_URL = 
			"jdbc:mariadb://localhost:3306/" + DB_NAME;
	static Connection conn = null;
	static Statement stmt = null;
	static ResultSet rs = null;
	
	public Connection getConnection() {
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(DB_URL, "project", "1234");
	
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로드 실패");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("DB 접속 실패");
			e.printStackTrace();
		}
		return conn;
	}	
	
	public void inputData(int num, String date, String place, String image_Path, String id) {
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			stmt.executeUpdate(String.format(
					"insert into lose values(%d, '%s', '%s', '%s', '%s')",
					num, date, place, image_Path, id));
		} catch (SQLException e) {
			System.out.println("error: " + e + "입력실패");
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}

				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public boolean searchFind(int num) {
		boolean b = true;
		try {		
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(String.format(
					"select * from lose where 등록코드=%d", num));
			if(rs.next()){
				b = false;				
			}
			
		} catch (SQLException e) {
			System.out.println("error: " + e + "검색실패");
		}
		return b;
	}
	
	public ResultSet searchView(int num) {
		try {		
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(String.format(
					"select * from lose where 등록코드=%d", num));
			
		} catch (SQLException e) {
			System.out.println("error: " + e + "검색실패");
		}
		return rs;
	}
	
	public boolean searchId(String id) {
		boolean b = false;
		try {		
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(String.format(
					"select * from lose where ID='%s'", id));
			if(rs.next()){
				b = true;
			}
			
		} catch (SQLException e) {
			System.out.println("error: " + e + "검색실패");
		}
		return b;
	}
	
	public ArrayList searchLosePet(String id) {
		ArrayList<Integer> list = new ArrayList<>();
		try {		
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(String.format(
					"select * from lose where ID='%s'", id));
			while(rs.next()){
				list.add(rs.getInt("등록코드"));			 
			}
		
		} catch (SQLException e) {
			System.out.println("error: " + e + "검색실패");
		}
		return list;
	}
	
	public boolean searchLosePet(String id, int num) {
		boolean b = false;
		try {		
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(String.format(
					"select * from lose where ID='%s'", id));
			
			while(rs.next()){
				if(num == rs.getInt("등록코드")) {
					b = true;
					break; 
				}
			}
		
		} catch (SQLException e) {
			
		}
		return b;
	}
	
	public void updateLosePet(String id, String leastId){
		try {		
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(String.format(
					"update lose set ID='%s' where ID='%s'", id, leastId));
			
		} catch (SQLException e) {
			System.out.println("error: " + e + "수정실패");
		}
	}
	
	public void deletePet(int num) {
		try {		
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(
					"delete from lose where 등록코드=" + num);
			
		} catch (SQLException e) {
			System.out.println("error: " + e + "삭제실패");
		}
	}
}
