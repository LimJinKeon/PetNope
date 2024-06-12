package petnope;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Find extends JFrame implements ActionListener {
	DogManager dog = new DogManager();
	DogManager2 dog2 = new DogManager2();
	private RoundedButton back, found;
	private JTextField tfCode;
	
	public Find() {
		getContentPane().setBackground(new Color(255, 248, 193));
		setTitle("강아지 발견");
		setSize(450, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
			
		back = new RoundedButton("<BACK");
		back.setText("  <");
		back.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 12));
		back.setPreferredSize(new Dimension(0, 25));
		back.setBounds(-14, 7, 36, 23);
		getContentPane().add(back);
		back.addActionListener(this);
		
		JLabel main = new JLabel("길을 잃은 반려견을 발견했어요");
		main.setFont(new Font("한컴 윤고딕 240", Font.PLAIN, 18));
		main.setBounds(82, 58, 268, 27);
		getContentPane().add(main);
		
		JLabel code = new JLabel("등록코드");
		code.setFont(new Font("한컴 윤고딕 240", Font.PLAIN, 15));
		code.setBounds(25, 111, 73, 27);
		getContentPane().add(code);
		
		tfCode = new JTextField();
		tfCode.setBounds(110, 110, 307, 30);
		getContentPane().add(tfCode);
		tfCode.setColumns(10);
		
		found = new RoundedButton("찾  기");
		found.setFont(new Font("한컴 윤고딕 240", Font.PLAIN, 17));
		found.setBounds(325, 150, 92, 30);
		getContentPane().add(found);
		found.addActionListener(this);
		
		setVisible(true);
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == back) {
			dispose();
			new mainPage();
		}
		if(e.getSource() == found) {
			try {
					int num = Integer.parseInt(tfCode.getText().trim());
					if(dog2.searchFind(num)) {
						JOptionPane.showMessageDialog(null, "실종신고된 반려견이 아닙니다");
					}
					else if(dog.searchFind(num)) {
						new View(num);
					}
					else {
						new View2(num);
					}				
				}
			  catch(NumberFormatException e1) {
				JOptionPane.showMessageDialog(Find.this,
	                    "정보를 입력해주세요",
	                    "반려견 정보",
	                    JOptionPane.WARNING_MESSAGE);
			}
		}
	}
}
