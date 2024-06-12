package petnope;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Color;

public class FindID extends JFrame implements ActionListener{
	private JTextField textName, textPhone;
	private JButton back, findID;
	IdManager im = new IdManager();
	private JLabel lblNewLabel_1;
	
	public FindID() {
		getContentPane().setBackground(new Color(255, 255, 255));
		setTitle("아이디 찾기");
		setSize(480, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("펫노프");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("휴먼매직체", Font.PLAIN, 50));
		lblNewLabel.setBounds(12, 25, 442, 80);
		getContentPane().add(lblNewLabel);
		
		JLabel name = new JLabel("이  름");
		name.setHorizontalAlignment(SwingConstants.CENTER);
		name.setFont(new Font("한컴 윤고딕 240", Font.PLAIN, 15));
		name.setBounds(12, 169, 70, 40);
		getContentPane().add(name);
		
		JLabel phone = new JLabel("연락처");
		phone.setHorizontalAlignment(SwingConstants.CENTER);
		phone.setFont(new Font("한컴 윤고딕 240", Font.PLAIN, 15));
		phone.setBounds(12, 232, 70, 40);
		getContentPane().add(phone);
		
		textName = new JTextField();
		textName.setBounds(94, 169, 360, 40);
		getContentPane().add(textName);
		textName.setColumns(10);
		
		textPhone = new JTextField();
		textPhone.setBounds(94, 232, 360, 40);
		getContentPane().add(textPhone);
		textPhone.setColumns(10);
		
		findID = new RoundedButton("아이디 찾기");
		findID.setFont(new Font("한컴 윤고딕 240", Font.PLAIN, 20));
		findID.setBounds(12, 336, 442, 40);
		getContentPane().add(findID);
		findID.addActionListener(this);
		
		back = new RoundedButton("아이디 찾기");
		back.setFont(new Font("한컴 윤고딕 250", Font.BOLD, 15));
		back.setText("  <");
		back.setBounds(-15, 10, 44, 30);
		getContentPane().add(back);
		
		lblNewLabel_1 = new JLabel("이름과 연락처 정보를 정확하게 입력해 주세요.");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("한컴 윤고딕 230", Font.PLAIN, 12));
		lblNewLabel_1.setForeground(new Color(255, 0, 0));
		lblNewLabel_1.setBounds(22, 282, 432, 30);
		getContentPane().add(lblNewLabel_1);
		back.addActionListener(this);
		
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == back) {
			dispose();
			new LoginScreen();
		}
		if(e.getSource() == findID) {
			String name = textName.getText().trim();
			String phone = textPhone.getText().trim().replace("-", "");	
	        if(textName.getText().isEmpty()) {
	        	JOptionPane.showMessageDialog(null, "이름을 입력해주세요");
	        }
	        else if(textPhone.getText().isEmpty()) {
	        	JOptionPane.showMessageDialog(null, "연락처를 입력해주세요");
	        }
	        else {
	        	try {						
			        phone = phone.substring(0, 3) + "-" + phone.substring(3, 7) + "-" + phone.substring(7);
			       
			        if(phone.length() != 13){
			        	JOptionPane.showMessageDialog(null, "연락처를 정확히 입력해주세요");
			        }
			        else if(im.searchUser(name, phone)) {
						String id = im.searchID(name, phone);
						JOptionPane.showMessageDialog(null, name + "님의 아이디는 " + id + "입니다");
						dispose();
						new LoginScreen();
		        	}
		        	else
		        		JOptionPane.showMessageDialog(null, "존재하지 않는 정보입니다");
				} catch(Exception e1) {
					JOptionPane.showMessageDialog(null, "연락처를 정확히 입력해주세요");
				}
			}
		}
	}
}
