package petnope;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class UserInfo extends JFrame implements ActionListener{
	private JTextField textName, textPhone, textId;
	private JButton updateBtn, redunBtn, back;
	private JLabel enable;
	IdManager im = new IdManager();
	boolean IdRedun = false;
	
	public UserInfo() {
		setTitle("회원정보수정");
		setSize(450, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		getContentPane().setBackground(new Color(254, 253, 192));
		
		JLabel main = new JLabel("회원정보수정");
		main.setHorizontalAlignment(SwingConstants.CENTER);
		main.setFont(new Font("KoPub돋움체 Medium", Font.PLAIN, 20));
		main.setBounds(139, 25, 149, 44);
		getContentPane().add(main);
		
		enable = new JLabel("");
		enable.setBounds(66, 146, 337, 33);
		getContentPane().add(enable);
			
		JLabel name = new JLabel("이  름");
		name.setHorizontalAlignment(SwingConstants.CENTER);
		name.setFont(new Font("KoPub돋움체 Medium", Font.PLAIN, 17));
		name.setBounds(35, 198, 75, 34);
		getContentPane().add(name);
		
		JLabel phone = new JLabel("연락처");
		phone.setHorizontalAlignment(SwingConstants.CENTER);
		phone.setFont(new Font("KoPub돋움체 Medium", Font.PLAIN, 17));
		phone.setBounds(35, 254, 75, 34);
		getContentPane().add(phone);
		
		textName = new JTextField();
		textName.setBounds(139, 200, 188, 34);
		getContentPane().add(textName);
		textName.setColumns(10);
		
		textPhone = new JTextField();
		textPhone.setColumns(10);
		textPhone.setBounds(139, 256, 188, 34);
		getContentPane().add(textPhone);
		
		updateBtn = new RoundedButton("수 정");
		updateBtn.setFont(new Font("KoPub돋움체 Medium", Font.PLAIN, 17));
		updateBtn.setBounds(161, 353, 106, 34);
		getContentPane().add(updateBtn);
		updateBtn.addActionListener(this);
		
		JLabel id = new JLabel("아이디");
		id.setHorizontalAlignment(SwingConstants.CENTER);
		id.setFont(new Font("KoPub돋움체 Medium", Font.PLAIN, 17));
		id.setBounds(35, 102, 75, 34);
		getContentPane().add(id);
		
		textId = new JTextField();
		textId.setColumns(10);
		textId.setBounds(139, 102, 188, 34);
		getContentPane().add(textId);
		
		redunBtn = new RoundedButton("중복확인");
		redunBtn.setFont(new Font("KoPub돋움체 Medium", Font.PLAIN, 15));
		redunBtn.setBounds(340, 102, 82, 34);
		getContentPane().add(redunBtn);
		redunBtn.addActionListener(this);
		
		back = new RoundedButton("수 정");
		back.setText("  <");
		back.setFont(new Font("KoPub돋움체 Medium", Font.PLAIN, 15));
		back.setBounds(-14, 10, 56, 25);
		getContentPane().add(back);
		back.addActionListener(this);
	
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == back) {
			dispose();
			new MyPage();
		}
		if(e.getSource() == redunBtn) {
			String id = textId.getText();
			if(!isValidId(id)) {
				enable.setText("아이디는 6~12자리의 영문자나 숫자로 이루어져야 합니다");
				enable.setForeground(Color.red);
				IdRedun = false;
			}
			else if(!im.searchRedun(id)) {
				enable.setText("                         사용가능한 아이디입니다");
				enable.setForeground(Color.blue);
				IdRedun = true;
			}
			else {
				enable.setText("                         중복된 아이디입니다");
				enable.setForeground(Color.red);
				IdRedun = false;
			}
		}
		if(e.getSource() == updateBtn) {
			String id = textId.getText().trim();
			String name = textName.getText().trim();
			String phone = textPhone.getText().replace("-", "").trim();	
			phone = phone.substring(0, 3) + "-" + phone.substring(3, 7) + "-" + phone.substring(7, 11);
			if(IdRedun) {
				if(im.searchPhone(phone)) {
					im.updateId(id, name, phone, LoginScreen.myid);
					new MyPetManager().updatePet(id, LoginScreen.myid);
					new DogManager2().updateLosePet(id, LoginScreen.myid);
					File file = new File("Users/" + LoginScreen.myid + ".jpg");
					LoginScreen.myid = id;
					File newFile = new File("Users/" + LoginScreen.myid + ".jpg");
					boolean result = file.renameTo(newFile);
					JOptionPane.showMessageDialog(null, "수정되었습니다");
					dispose();
					new MyPage();
				}
				else {
					JOptionPane.showMessageDialog(null, "이미 등록된 연락처입니다");
				}
			} else {
				JOptionPane.showMessageDialog(null, "아이디 중복확인을 해주세요");
			}
		}
	}
	
    public boolean isValidId(String myId) {
        return myId.matches("^[a-zA-Z0-9]{6,12}$");
    }
}
