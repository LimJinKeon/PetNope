package petnope;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MyPetManager {
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
	
	public void inputData(String id, int num) {
		
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			stmt.executeUpdate(String.format(
					"insert into mypet values('%s', %d)", id, num));
			
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
	
	public boolean searchUpdate(String id) {
		boolean b = false;
		try {		
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(String.format(
					"select * from mypet where ID='%s'", id));
			if(rs.next()){
				b = true;				 
			}
		
		} catch (SQLException e) {
			System.out.println("error: " + e + "검색실패");
		}
		return b;
	}
	
	public boolean searchUpdate2(String id, int num) {
		boolean b = true;
		try {		
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(String.format(
					"select * from mypet where ID='%s'", id));
			while(rs.next()){
				if(num == rs.getInt("num")) {
					b = false;
					break; 
				}
			}
		
		} catch (SQLException e) {
			System.out.println("error: " + e + "검색실패");
		}
		return b;
	}
	
	public boolean searchMyPet(String id) {
		boolean b = false;
		try {		
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(String.format(
					"select * from mypet where ID='%s'", id));
			if(rs.next()){
				b = true;				 
			}
		
		} catch (SQLException e) {
			System.out.println("error: " + e + "검색실패");
		}
		return b;
	}
	
	public ArrayList searchMyPet2(String id) {
		ArrayList<Integer> list = new ArrayList<>();
		try {		
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(String.format(
					"select * from mypet where ID='%s'", id));
			while(rs.next()){
				list.add(rs.getInt("num"));			 
			}
		
		} catch (SQLException e) {
			System.out.println("error: " + e + "검색실패");
		}
		return list;
	}
	
	public void updatePet(String id, String leastId){
		try {		
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(String.format(
					"update mypet set ID='%s' where ID='%s'", id, leastId));
			
		} catch (SQLException e) {
			System.out.println("error: " + e + "수정실패");
		}
	}
	
	public void deletePet(int num) {
		try {		
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(
					"delete from mypet where num=" + num);
			
		} catch (SQLException e) {
			System.out.println("error: " + e + "삭제실패");
		}
	}
}
