package petnope;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class LosePet extends JFrame implements ActionListener{
	private Timer timer;
	private int currentImageIndex;
	private String[] imagePaths;
	private JLabel image;
	
	public LosePet() {
		setTitle("실종신고");
		setSize(450, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		getContentPane().setBackground(new Color(254, 253, 192));
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(12, 251, 410, 500);
		getContentPane().add(scrollPane);
		
		JLabel main = new JLabel("실종신고된 반려견");
		main.setHorizontalAlignment(SwingConstants.CENTER);
		main.setFont(new Font("KoPub돋움체 Medium", Font.PLAIN, 23));
		main.setBounds(121, 20, 175, 47);
		getContentPane().add(main);
		
		JButton back = new RoundedButton("  <");
		back.setBounds(-14, 10, 49, 23);
		getContentPane().add(back);
		
		image = new JLabel("");
		image.setBounds(12, 80, 410, 161);
		getContentPane().add(image);
		back.addActionListener(this);
		
		ArrayList<Integer> list2 = new DogManager2().searchLosePet(LoginScreen.myid);
		imagePaths = new String[list2.size() * 2];
		int i = 0;
		for(int e : list2) {
			try {				
				ResultSet rs = new DogManager().searchMyPet(e);
				ResultSet rs2 = new DogManager2().searchView(e);
				if(rs.next()) {
					textArea.append("등록번호 : "); textArea.append(rs.getInt("num") + "\n"); 
					textArea.append("이   름      : "); textArea.append(rs.getString("name") + "\n"); 
					textArea.append("견   종      : "); textArea.append(rs.getString("kind") + "\n"); 
					textArea.append("생년월일 : "); textArea.append(rs.getString("birth") + "\n"); 
					textArea.append("성   별      : "); textArea.append(rs.getString("gender") + "\n"); 
					textArea.append("등 록 인   : "); textArea.append(rs.getString("pname") + "\n"); 
					textArea.append("연 락 처   : "); textArea.append(rs.getString("phone") + "\n");
					imagePaths[i++] = rs.getString("image_Path");
				}
				if(rs2.next()) {
					if(!new DogManager().searchFind(e)) {
						textArea.append("등록번호 : "); textArea.append(rs2.getInt("등록코드") + "\n");
					}
					textArea.append("실종날짜 : "); textArea.append(rs2.getString("실종날짜") + "\n"); 
					textArea.append("실종장소 : "); textArea.append(rs2.getString("실종장소") + "\n\n");
					imagePaths[i++] = rs2.getString("image_Path");
				}
			} catch(Exception e1) {
				e1.printStackTrace();
			}
		}		
		new Thread(() -> {
			displayImage();
			setupTimer();
		}).start();
		
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		dispose();
		new MyPage();
	}
	
	private void displayImage() {
		Image img = new ImageIcon(imagePaths[currentImageIndex]).getImage();
		Image newImg = img.getScaledInstance(image.getWidth(), image.getHeight(), java.awt.Image.SCALE_SMOOTH);
		ImageIcon changeImg = new ImageIcon(newImg);
		image.setIcon(changeImg);
    }
	
	private void setupTimer() {
        int delay = 3000; // 이미지 변경 간격 (밀리초 단위)
        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                currentImageIndex = (currentImageIndex + 1) % imagePaths.length;
                displayImage();
            }
        };
        timer = new Timer(delay, taskPerformer);
        timer.start();
    }
}
