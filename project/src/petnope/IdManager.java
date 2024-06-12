package petnope;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class IdManager {
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
	public void update(String myId, String myPwd, String myName, String myPhone) {

        try {
            conn = getConnection();
            stmt = conn.createStatement();

            String sql = String.format("INSERT INTO id_table (ID, PWD,name,phone) VALUES ('%s', '%s','%s','%s')", myId, myPwd, myName, myPhone);
            stmt.executeUpdate(sql);
            System.out.println("데이터베이스에 값이 성공적으로 추가되었습니다.");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
	}
	
	public boolean search(String enterID, String enterPwd) {
		boolean password = false;
		try (Connection conn = getConnection()) {
            String sql = "SELECT * FROM ID_table WHERE ID = ?"; // users 테이블에서 아이디로 검색
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, enterID);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // 데이터베이스에서 해당 아이디의 비밀번호 가져오기
                String savedPwd = rs.getString("PWD");

                // 비밀번호 일치 여부 확인
                if (enterPwd.equals(savedPwd)) {
                    // 비밀번호가 일치하면 mainPage 클래스로 이동
                    //JOptionPane.showMessageDialog(null, "아이디 : " + enterID + ", 비밀번호 : " + enterPwd);
                    password = true;
                } else {
                    // 비밀번호가 일치하지 않을 때 처리할 내용을 여기에 추가하세요
                    JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다.");
                    // 예를 들어 로그인 창을 다시 보여주거나, 다른 작업을 수행할 수 있습니다.
                    password = false;
                }
            } else{
                JOptionPane.showMessageDialog(null, "존재하지 않는 아이디입니다.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return password;
	}
	
	public boolean searchRedun(String ID) {
		boolean b = false;
		try {		
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(String.format(
					"select * from id_table where ID='%s'", ID));
			
			while(rs.next()){
				b = true;
				break; 
			}
		
		} catch (SQLException e) {
			System.out.println("error: " + e + "검색실패");
		}
		return b;
	}
	
	public boolean searchUser(String name, String phone) {
		boolean b = false;
		try {		
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(String.format(
					"select * from id_table where NAME='%s' and phone='%s'", name, phone));
			
			if(rs.next()){
				b = true;				
			}
		
		} catch (SQLException e) {
			System.out.println("error: " + e + "검색실패");
		}
		return b;
	}
	
	public String searchID(String name, String phone) {
		String id = null;
		try {		
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(String.format(
					"select * from id_table where NAME='%s' and phone='%s'", name, phone));
			rs.next();
			id = rs.getString("ID");
		
		} catch (SQLException e) {
			System.out.println("error: " + e + "검색실패");
		}
		return id;
	}
	
	public String searchName(String id) {
		String name = null;
		try {		
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(String.format(
					"select * from id_table where ID='%s'", id));
			rs.next();
			name = rs.getString("name");
		
		} catch (SQLException e) {
			System.out.println("error: " + e + "검색실패");
		}
		return name;
	}
	
	public String searchPassword(String id, String name, String phone) {
		String pass = null;
		try {		
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(String.format(
					"select * from id_table where ID='%s' and NAME='%s' and phone='%s'", id, name, phone));
			rs.next();
			pass = rs.getString("PWD");
		
		} catch (SQLException e) {
			System.out.println("error: " + e + "검색실패");
		}
		return pass;
	}
	
	public void updateId(String id, String name, String phone, String leastId) {
		try {		
			conn = getConnection();
			stmt = conn.createStatement();
			
			stmt.executeUpdate(String.format(
					"update id_table set ID='%s', NAME='%s', phone='%s' where ID='%s'",
					id, name, phone, leastId));
			
		} catch (SQLException e) {
			System.out.println("error: " + e + "검색실패");
		}
	}
	
	public boolean searchPhone(String phone) {
		int i = 0;
		try {		
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(String.format(
					"select * from id_table where phone='%s'", phone));
			
			while(rs.next()){
				i++;		
			}
		
		} catch (SQLException e) {
			System.out.println("error: " + e + "검색실패");
		}
		return i > 1 ? false : true;
	}
}
