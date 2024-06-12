package petnope;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class LoseDelete extends JFrame implements ActionListener{
	private JTextField textField;
	private JButton delete;
	private JButton back;
	
	public LoseDelete() {
		setTitle("실종신고 정보 삭제");
		setSize(450, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		getContentPane().setBackground(new Color(254, 253, 192));
		
		JLabel main = new JLabel("실종신고 정보 삭제");
		main.setHorizontalAlignment(SwingConstants.CENTER);
		main.setFont(new Font("KoPub돋움체 Medium", Font.PLAIN, 20));
		main.setBounds(130, 35, 160, 39);
		getContentPane().add(main);
		
		JLabel labelNum = new JLabel("삭제할 등록번호");
		labelNum.setFont(new Font("KoPub돋움체 Medium", Font.PLAIN, 15));
		labelNum.setHorizontalAlignment(SwingConstants.CENTER);
		labelNum.setBounds(34, 119, 160, 39);
		getContentPane().add(labelNum);
		
		textField = new JTextField();
		textField.setBounds(186, 125, 192, 29);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		delete = new RoundedButton("삭 제");
		delete.setFont(new Font("KoPub돋움체 Medium", Font.PLAIN, 15));
		delete.setBounds(154, 299, 115, 29);
		getContentPane().add(delete);
		delete.addActionListener(this);
		
		back = new RoundedButton("  <");
		back.setBounds(-14, 10, 52, 23);
		getContentPane().add(back);
		back.addActionListener(this);
		
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == back) {
			dispose();
			new MyPage();
		}
		if(e.getSource() == delete) {
			int num = Integer.parseInt(textField.getText().trim());
			if(new DogManager2().searchLosePet(LoginScreen.myid, num)) {
				new DogManager2().deletePet(num);
				JOptionPane.showMessageDialog(null, "삭제되었습니다");
				textField.setText(null);
				dispose();
				new MyPage();
			}
			else
				JOptionPane.showMessageDialog(null, "실종신고한 반려견이 아닙니다");
		}
	}
}
