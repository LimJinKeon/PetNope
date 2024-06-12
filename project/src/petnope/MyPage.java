package petnope;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

public class MyPage extends JFrame implements ActionListener{
	private JButton user;
	RoundedButton back, myPet, losePet, delete, deleteLose, userInfo;
	MyPetManager mp = new MyPetManager();
	private JButton logout;
	
	public MyPage() {
		setTitle("마이페이지");
		setSize(450, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		getContentPane().setBackground(new Color(254, 253, 192));
		
		JLabel lblNewLabel = new JLabel(LoginScreen.myname + "님의 마이페이지");
		lblNewLabel.setFont(new Font("휴먼매직체", Font.PLAIN, 30));
		lblNewLabel.setBounds(102, 21, 262, 54);
		getContentPane().add(lblNewLabel);
		
		user = new JButton();
		user.setBorder(null);
		if(new File("Users/" + LoginScreen.myid + ".jpg").exists()) {
			Image img = new ImageIcon("Users/" + LoginScreen.myid + ".jpg").getImage();
			Image newImg = img.getScaledInstance(220, 220, java.awt.Image.SCALE_SMOOTH);
			ImageIcon changeImg = new ImageIcon(newImg); 
			user.setIcon(changeImg);
		}
		else {
			Image img = new ImageIcon("user.png").getImage();
			Image newImg = img.getScaledInstance(220, 220, java.awt.Image.SCALE_SMOOTH);
			ImageIcon changeImg = new ImageIcon(newImg); 
			user.setIcon(changeImg);
		}
		user.setHorizontalAlignment(SwingConstants.CENTER);
		user.setBounds(102, 85, 221, 212);
		getContentPane().add(user);
		user.addActionListener(this);
		
		back = new RoundedButton("  <");
		back.setText("<");
		back.setBounds(-13, 10, 41, 23);
		getContentPane().add(back);
		back.addActionListener(this);
		
		myPet = new RoundedButton("나의 반려견보기");
		myPet.setFont(new Font("KoPub돋움체 Medium", Font.PLAIN, 15));
		myPet.setBounds(102, 331, 221, 35);
		getContentPane().add(myPet);
		myPet.addActionListener(this);
		
		delete = new RoundedButton("반려견 정보 삭제");
		delete.setText("반려견 정보 삭제");
		delete.setFont(new Font("KoPub돋움체 Medium", Font.PLAIN, 15));
		delete.setBounds(102, 397, 221, 35);
		getContentPane().add(delete);
		delete.addActionListener(this);
		
		losePet = new RoundedButton("실종신고한 반려견보기");
		losePet.setFont(new Font("KoPub돋움체 Medium", Font.PLAIN, 15));
		losePet.setBounds(102, 463, 221, 35);
		getContentPane().add(losePet);
		losePet.addActionListener(this);
		
		logout = new RoundedButton("로그아웃");
		logout.setFont(new Font("KoPub돋움체 Medium", Font.PLAIN, 15));
		logout.setBounds(325, 728, 97, 23);
		getContentPane().add(logout);
		logout.addActionListener(this);
		
		deleteLose = new RoundedButton("실종신고한 반려견보기");
		deleteLose.setText("실종신고 취소하기");
		deleteLose.setFont(new Font("KoPub돋움체 Medium", Font.PLAIN, 15));
		deleteLose.setBounds(102, 527, 221, 35);
		getContentPane().add(deleteLose);
		deleteLose.addActionListener(this);
		
		userInfo = new RoundedButton("회원정보수정");
		userInfo.setText("회원정보수정");
		userInfo.setFont(new Font("KoPub돋움체 Medium", Font.PLAIN, 15));
		userInfo.setBounds(212, 728, 97, 23);
		getContentPane().add(userInfo);
		userInfo.addActionListener(this);
		
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == user) {
			String path = ImageCtrl.imgCtl(user);
			ImageCtrl.saveImage(path, "Users/" + LoginScreen.myid + ".jpg");
		}
		if(e.getSource() == back) {
			dispose();
			new mainPage();
		}
		if(e.getSource() == myPet) {
			if(new MyPetManager().searchMyPet(LoginScreen.myid)) {
				dispose();
				new MyPet();
			}
			else
				JOptionPane.showMessageDialog(null, "등록한 반려견이 없습니다");
		}
		if(e.getSource() == delete) {
			if(new MyPetManager().searchMyPet(LoginScreen.myid)) {
				dispose();
				new MyPetDelete();
			}
			else
				JOptionPane.showMessageDialog(null, "등록한 반려견이 없습니다");
		}
		if(e.getSource() == losePet) {
			if(new DogManager2().searchId(LoginScreen.myid)) {
				dispose();
				new LosePet();
			}
			else
				JOptionPane.showMessageDialog(null, "실종신고한 반려견이 없습니다");
		}
		if(e.getSource() == deleteLose) {
			if(new DogManager2().searchId(LoginScreen.myid)) {
				dispose();
				new LoseDelete();
			}
			else
				JOptionPane.showMessageDialog(null, "실종신고한 반려견이 없습니다");
		}
		if(e.getSource() == userInfo) {
			dispose();
			new UserInfo();
		}
		if(e.getSource() == logout) {
            int choice = JOptionPane.showConfirmDialog(
                    MyPage.this,
                    "정말 로그아웃 하시겠습니까?",
                    "로그아웃",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.WARNING_MESSAGE
            );
            if (choice == JOptionPane.YES_OPTION) {
    			dispose();
    			new LoginScreen();
            }
		}
	}
}
