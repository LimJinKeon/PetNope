package petnope;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class LoginScreen extends JFrame {
	static String myid ;
	static String myname;
	IdManager im = new IdManager();
	
    public LoginScreen() {
    	getContentPane().setBackground(new Color(255, 255, 255));
        setTitle("로그인");
        JPanel title = new JPanel();
        title.setBackground(new Color(255, 255, 255));
        title.setBounds(12, 50, 316, 62);
        JLabel login = new JLabel("PetNosePrint");
        login.setForeground(new Color(0, 0, 0));
        login.setFont(new Font("휴먼매직체", Font.PLAIN, 45));
        title.add(login);

        JPanel jp1 = new JPanel();
        jp1.setBounds(12, 10, 418, 83);

        JPanel idPanel = new JPanel();
        idPanel.setBounds(0, 0, 92, 41);
        idPanel.setBackground(new Color(255, 255, 255));
        idPanel.setLayout(null);
        JLabel jlb1 = new JLabel("아이디       ", SwingConstants.CENTER);
        jlb1.setFont(new Font("한컴 윤고딕 240", Font.PLAIN, 12));
        jlb1.setBounds(12, 10, 68, 21);
        idPanel.add(jlb1);

        JPanel idPanel2 = new JPanel();
        idPanel2.setBounds(91, 0, 327, 41);
        idPanel2.setBackground(new Color(255, 255, 255));
        idPanel2.setLayout(null);
        JTextField textId = new JTextField(10);
        textId.setBounds(5, 5, 310, 26);
        idPanel2.add(textId);
        jp1.setLayout(null);

        jp1.add(idPanel);
        jp1.add(idPanel2);

        JPanel pwdPanel = new JPanel();
        pwdPanel.setBounds(0, 41, 92, 41);
        pwdPanel.setBackground(new Color(255, 255, 255));
        JLabel jlb2 = new JLabel("비밀번호    ", JLabel.CENTER);
        jlb2.setFont(new Font("한컴 윤고딕 240", Font.PLAIN, 12));
        jlb2.setBounds(12, 10, 67, 21);

        JPanel pwdPanel2 = new JPanel();
        pwdPanel2.setBounds(91, 41, 327, 41);
        pwdPanel2.setBackground(new Color(255, 255, 255));
        JPasswordField textPass = new JPasswordField(10);
        textPass.setBounds(5, 5, 310, 26);
        pwdPanel.setLayout(null);

        pwdPanel.add(jlb2);
        pwdPanel2.setLayout(null);
        pwdPanel2.add(textPass);

        jp1.add(pwdPanel);
        jp1.add(pwdPanel2);

        JPanel jp2 = new JPanel();
        jp2.setBackground(new Color(255, 255, 255));
        jp2.setBounds(12, 122, 442, 104);
        jp2.setLayout(null);
        jp2.add(jp1);
        getContentPane().setLayout(null);

        getContentPane().add(title);
        getContentPane().add(jp2);
        
        RoundedButton join_1 = new RoundedButton("회원가입");
        join_1.setFont(new Font("한컴 윤고딕 240", Font.PLAIN, 12));
        join_1.setBounds(23, 286, 92, 23);
        getContentPane().add(join_1);
        join_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		dispose();
        		new JoinScreen();
        	}
        });
        
        RoundedButton jLogin_1 = new RoundedButton("로그인");
        jLogin_1.setFont(new Font("한컴 윤고딕 240", Font.PLAIN, 18));
        jLogin_1.setBounds(22, 236, 419, 40);
        
        getContentPane().add(jLogin_1);
        
        jLogin_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String id = textId.getText().trim();
        		String pass = textPass.getText().trim();
        		if(im.search(id, pass)) {
	        		dispose();
	        		myid = id;
	        		myname = im.searchName(id);
	        		new mainPage();
        		}
        	}
        });
        
        RoundedButton findId = new RoundedButton("아이디찾기");
        findId.setFont(new Font("한컴 윤고딕 240", Font.PLAIN, 12));
        findId.setBounds(186, 286, 92, 23);
        getContentPane().add(findId);
        findId.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		dispose();
        		new FindID();
        	}
        });
        
        RoundedButton findPass = new RoundedButton("비밀번호찾기");
        findPass.setFont(new Font("한컴 윤고딕 240", Font.PLAIN, 12));
        findPass.setBounds(349, 286, 92, 23);
        getContentPane().add(findPass);
        setBounds(200, 200, 480, 800);
        findPass.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		dispose();
        		new FindPassword();
        	}
        });
        
        setResizable(false);  // 화면 크기 고정하는 작업

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setLocationRelativeTo(null);
		
        setVisible(true);
    }
}