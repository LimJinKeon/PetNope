package petnope;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DogManager {
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
	
	public void inputData(int num, String name, String kind, String birth,
			String gender, String pname, String phone, String neuter, String image_Path) {
		
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			stmt.executeUpdate(String.format(
					"insert into capstone_table values(%d, '%s', '%s', '%s', '%s', '%s', '%s', "
					+ "'%s', '%s')", num, name, kind, birth, gender, pname, phone, neuter, image_Path));
			
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

	public boolean searchRedun(String name, String pname, String phone) {
		boolean b = true;
		try {		
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(String.format(
					"select * from capstone_table where name='%s'and pname='%s' and phone='%s'", name, pname, phone));
			if(rs.next()) {
				b = false;
			}
		
		} catch (SQLException e) {
			System.out.println("error: " + e + "검색실패");
		}
		return b;
	}
	
	public boolean searchRedun(int num) {
		boolean b = false;
		try {		
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(String.format(
					"select * from capstone_table where num=%d", num));
			if(rs.next()){
				b = true;				
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
					"select * from capstone_table where num=%d", num));
		
		} catch (SQLException e) {
			System.out.println("error: " + e + "검색실패");
		}
		return rs;
	}
	
	public boolean searchFind(int num) {
		boolean b = false;
		try {		
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(String.format(
					"select * from capstone_table where num=%d", num));
			if(rs.next()){
				b = true;				 
			}
		
		} catch (SQLException e) {
			System.out.println("error: " + e + "검색실패");
		}
		return b;
	}
	
	public ResultSet searchMyPet(int num) {
		try {		
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(String.format(
					"select * from capstone_table where num=%d", num));
			
		} catch (SQLException e) {
			System.out.println("error: " + e + "검색실패");
		}
		return rs;
	}
	
	public void deletePet(int num) {
		try {		
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(
					"delete from capstone_table where num=" + num);
			
		} catch (SQLException e) {
			System.out.println("error: " + e + "삭제실패");
		}
	}
}
