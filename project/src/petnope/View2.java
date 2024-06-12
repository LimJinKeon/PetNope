package petnope;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class View2 extends JFrame implements ActionListener{
	private JPanel p1, pa;
	private JLabel lostarea, lostdate;
	private JLabel lostarea2, lostdate2;
	private RoundedButton enter;
	DogManager2 dog2 = new DogManager2();
	
	public View2(int code) {
		setTitle("검색결과");
		setSize(450, 800);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		setLocation(screenSize.width / 2 + 220, screenSize.height / 2 - 420);
		
		try {
			ResultSet rs2 = dog2.searchView(code);
			
			pa = new JPanel();
			Color c=new Color(255,212,2);
			pa.setBackground(new Color(255, 248, 193));
			pa.setLayout(null);
			p1 = new JPanel(new GridLayout(2, 2, 50, 0));
			p1.setBackground(new Color(255, 248, 193));
			lostdate = new JLabel("   실종시간: ");
			lostdate.setFont(new Font("한컴 윤고딕 240", Font.PLAIN, 14));
			lostarea = new JLabel("   실종장소: ");
			lostarea.setFont(new Font("한컴 윤고딕 240", Font.PLAIN, 14));
			
			rs2.next();
			lostdate2 = new JLabel(rs2.getString("실종날짜"));
			lostdate2.setFont(new Font("한컴 윤고딕 240", Font.PLAIN, 14));
			lostarea2 = new JLabel(rs2.getString("실종장소"));
			lostarea2.setFont(new Font("한컴 윤고딕 240", Font.PLAIN, 14));
			
			enter = new RoundedButton("확 인");
			enter.setFont(new Font("한컴 윤고딕 240", Font.PLAIN, 14));
			enter.setText("확  인");
			enter.setPreferredSize(new Dimension(30, 15));
			enter.addActionListener(this);
		
			p1.add(lostdate);
			p1.add(lostdate2);
			p1.add(lostarea);
			p1.add(lostarea2);
			
			pa.add(p1);
			p1.setBounds(12, 276, 410, 85);
			pa.add(enter);
			enter.setBounds(333, 475, 89, 30);
			
			getContentPane().add(pa);
			
			JLabel image = new JLabel();
			image.setBounds(12, 10, 410, 241);
			image.setPreferredSize(new Dimension(300, 200));
			ImageIcon icon = new ImageIcon(rs2.getString("image_Path"));
			Image img = icon.getImage();
			Image newImg = img.getScaledInstance(410, 200, java.awt.Image.SCALE_SMOOTH);
			ImageIcon changeImg = new ImageIcon(newImg);
			image.setIcon(changeImg);
			pa.add(image);
			
			setVisible(true);
			
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, "등록되지 않은 반려견입니다");
		}
	}
	public void actionPerformed(ActionEvent e) {
		dispose();
	}
}
