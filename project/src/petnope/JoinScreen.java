package petnope;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class JoinScreen extends JFrame{
    IdManager ID = new IdManager();

    public JoinScreen() {
        super("회원 관리 화면");
        getContentPane().setBackground(new Color(255, 255, 255));
        
        JLabel title = new JLabel("가입하기",JLabel.CENTER);
        title.setBackground(new Color(255, 255, 255));
        title.setBounds(0, 0, 466, 81);
        title.setForeground(new Color(0, 0, 0));
        title.setFont(new Font("휴먼편지체", Font.BOLD, 50));

        //formPanel
        JPanel idPanel = new JPanel();
        idPanel.setBackground(new Color(255, 255, 255));
        idPanel.setBounds(0, 0, 442, 50);
        JTextField id = new JTextField(10);
        id.setBounds(89, 8, 341, 32);
        idPanel.setLayout(null);
        JLabel label = new JLabel("아이디");
        label.setFont(new Font("한컴 윤고딕 240", Font.PLAIN, 15));
        label.setBounds(12, 8, 65, 32);
        idPanel.add(label);
        idPanel.add(id);
        //formPanel
        JPanel pwdPanel = new JPanel();
        pwdPanel.setBackground(new Color(255, 255, 255));
        pwdPanel.setBounds(0, 50, 442, 50);
        JPasswordField pwd = new JPasswordField(10);
        pwd.setBounds(90, 10, 340, 30);
        pwdPanel.setLayout(null);
        JLabel label_1 = new JLabel("비밀번호");
        label_1.setFont(new Font("한컴 윤고딕 240", Font.PLAIN, 15));
        label_1.setBounds(12, 10, 66, 30);
        pwdPanel.add(label_1);
        pwdPanel.add(pwd);
        //formPanel
        JPanel namePanel = new JPanel();
        namePanel.setBackground(new Color(255, 255, 255));
        namePanel.setBounds(0, 100, 442, 50);
        JTextField name = new JTextField(10);
        name.setBounds(89, 10, 341, 30);
        namePanel.setLayout(null);
        JLabel label_2 = new JLabel("이    름");
        label_2.setFont(new Font("한컴 윤고딕 240", Font.PLAIN, 15));
        label_2.setBounds(12, 10, 65, 30);
        namePanel.add(label_2);
        namePanel.add(name);
        //formPanel
        JPanel phonePanel = new JPanel();
        phonePanel.setBackground(new Color(255, 255, 255));
        phonePanel.setBounds(0, 150, 442, 50);
        JTextField phone = new JTextField(10);
        phone.setBounds(89, 10, 341, 30);
        phonePanel.setLayout(null);
        JLabel label_3 = new JLabel("연 락 처");
        label_3.setFont(new Font("한컴 윤고딕 240", Font.PLAIN, 15));
        label_3.setBounds(12, 10, 65, 30);
        phonePanel.add(label_3);
        phonePanel.add(phone);

        JPanel formPanel = new JPanel();
        formPanel.setBackground(new Color(255, 255, 255));
        formPanel.setBounds(12, 20, 442, 202);
        formPanel.setLayout(null);
        formPanel.add(idPanel);
        formPanel.add(pwdPanel);
        formPanel.add(namePanel);
        formPanel.add(phonePanel);

        // radio + form panel
        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(new Color(255, 255, 255));
        contentPanel.setBounds(0, 139, 466, 624);
        contentPanel.setLayout(null);
        contentPanel.add(formPanel);
        getContentPane().setLayout(null);


        getContentPane().add(title);
        getContentPane().add(contentPanel);
        JButton join = new JButton("가입하기");
        join.setBounds(22, 567, 420, 35);
        contentPanel.add(join);
        join.setForeground(new Color(255, 255, 255));
        join.setBackground(new Color(255, 207, 64));
        join.setFont(new Font("한컴 윤고딕 240", Font.PLAIN, 15));
        
        JPanel panel = new JPanel();
        panel.setBackground(new Color(0, 0, 0));
        panel.setBounds(22, 222, 420, 0);
        contentPanel.add(panel);
        
        JLabel lblNewLabel = new JLabel("이미 계정이 있습니까?");
        lblNewLabel.setBounds(74, 91, 200, 38);
        getContentPane().add(lblNewLabel);
        lblNewLabel.setFont(new Font("한컴 윤고딕 240", Font.PLAIN, 20));
        JButton cancel = new JButton("로그인");
        cancel.setBounds(286, 100, 86, 23);
        getContentPane().add(cancel);
        cancel.setForeground(new Color(255, 128, 0));
        cancel.setBackground(new Color(255, 255, 255));
        cancel.setFont(new Font("한컴 윤고딕 240", Font.PLAIN, 15));
        
                // 취소 버튼을 클릭했을 때 이벤트 처리
                cancel.addActionListener(new ActionListener() {
        
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        new LoginScreen();
                        dispose();
                    }
                });
        
                // 이벤트 처리
                join.addActionListener(new ActionListener() {
        
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String myId = id.getText().trim();
                        String myPwd = new String(pwd.getPassword()).trim();
                        String myName = name.getText().trim();
                        String myPhone = phone.getText().trim().replace("-", "");
                        
                        if (myId.isEmpty()) {
                        	JOptionPane.showMessageDialog(null, "아이디를 입력해주세요");
                        }
                        else if (myPwd.isEmpty()) {
                        	JOptionPane.showMessageDialog(null, "비밀번호를 입력해주세요");
                        }
                        else if (myName.isEmpty()) {
                        	JOptionPane.showMessageDialog(null, "이름을 입력해주세요");
                        }
                        else if (myPhone.isEmpty()) {
                        	JOptionPane.showMessageDialog(null, "연락처를 입력해주세요");
                        }
                        // 아이디와 비밀번호가 유효한지 확인
                        else if (!isValidId(myId)) {
                        	JOptionPane.showMessageDialog(null, "아이디는 6~12자리의 영문자나 숫자로 이루어져야 합니다");
                        }
                        else if(!isValidPassword(myPwd)) {
                        	JOptionPane.showMessageDialog(null, "비밀번호는 10자리 이상으로 이루어져야 합니다");
                        }
                        else {
        	                try {
        		                myPhone = myPhone.substring(0, 3) + "-" + myPhone.substring(3, 7) + "-" + myPhone.substring(7);
        		                
        		                if(myPhone.length() != 13 ) {
        							JOptionPane.showMessageDialog(null, "연락처를 정확히 입력해주세요 \n예) 010-1234-5678");
        						}
        		                else if(ID.searchRedun(myId)) {
        		                	JOptionPane.showMessageDialog(null, "이미 존재하는 아이디입니다");
        		                }
        		                else if(ID.searchUser(myName, myPhone)) {
        		                	JOptionPane.showMessageDialog(null, "이미 등록된 사용자입니다");
        		                }
        		                else {
        		                    JOptionPane.showMessageDialog
        		                            (null, "아이디 : "+myId+", 비밀번호 : "+myPwd+
        		                                    ", 이 름 : "+myName+", 연락처 : "+myPhone);
        		                    // 유효한 경우 데이터베이스에 삽입
        		                    ID.update(myId, myPwd, myName, myPhone);     
        		                    dispose();
        		                    new LoginScreen();
        		                }
        	                } catch(Exception e1) {
        	                	JOptionPane.showMessageDialog(null, "연락처를 정확히 입력해주세요 \n예) 010-1234-5678");
        	                }
                        }
                    }
        
                    // 아이디 유효성 검사 메서드
                    public boolean isValidId(String myId) {
                        return myId.matches("^[a-zA-Z0-9]{6,12}$");
                    }
        
                    // 비밀번호 유효성 검사 메서드
                    public boolean isValidPassword(String myPwd) {
                        return myPwd.length() >= 10;
                    }
        
                });

        setBounds(200, 200, 480, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
        setVisible(true);
    }
}
