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
import javax.swing.Timer;

public class View extends JFrame implements ActionListener{
	private JPanel p1, pa;
	private JLabel name, kind, birth, gender, people, phone, neuter, lostday, lostarea;
	private JLabel name2, kind2, birth2, gender2, people2, phone2, neuter2, lostday2, lostarea2;
	private JLabel image;
	private RoundedButton enter;
    private Timer timer;
	private int currentImageIndex;
	public String[] imagePaths;
	DogManager dog = new DogManager();
	DogManager2 dog2 = new DogManager2();
	
	public View(int num) {
		setTitle("검색결과");
		setSize(450, 800);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		setLocation(screenSize.width / 2 + 220, screenSize.height / 2 - 420);
		
		try {
			ResultSet rs = dog.searchView(num);
			ResultSet rs2 = dog2.searchView(num);
			rs.next();
			rs2.next();

			imagePaths = new String[] {rs.getString("image_Path"), rs2.getString("image_Path")};
			new Thread(() -> {
				displayImage();
				setupTimer();
			}).start();

			image = new JLabel();
			image.setPreferredSize(new Dimension(300, 200));
			ImageIcon icon = new ImageIcon(rs.getString("image_Path"));
			Image img = icon.getImage();
			Image newImg = img.getScaledInstance(410, 200, java.awt.Image.SCALE_SMOOTH);
			ImageIcon changeImg = new ImageIcon(newImg);
			image.setIcon(changeImg);

			pa = new JPanel();
			Color c=new Color(255,212,2);
			pa.setBackground(new Color(255, 248, 193));
			pa.setLayout(null);
			p1 = new JPanel(new GridLayout(9, 2, 50, 20));
			p1.setBackground(new Color(255, 248, 193));
			name = new JLabel("   이 름 : ");
			name.setFont(new Font("한컴 윤고딕 240", Font.PLAIN, 15));
			kind = new JLabel("   품 종 : ");
			kind.setFont(new Font("한컴 윤고딕 240", Font.PLAIN, 15));
			birth = new JLabel("   생년월일 : ");
			birth.setFont(new Font("한컴 윤고딕 240", Font.PLAIN, 15));
			gender = new JLabel("   성 별: ");
			gender.setFont(new Font("KoPub돋움체 Medium", Font.BOLD, 15));
			people = new JLabel("   등록자 이름 : ");
			people.setFont(new Font("한컴 윤고딕 240", Font.PLAIN, 15));
			phone = new JLabel("   등록자 연락처 : ");
			phone.setFont(new Font("한컴 윤고딕 240", Font.PLAIN, 15));
			neuter = new JLabel("   중성화 유무: ");
			neuter.setFont(new Font("KoPub돋움체 Medium", Font.BOLD, 15));
			lostday = new JLabel("   실종시간 : ");
			lostday.setFont(new Font("한컴 윤고딕 240", Font.PLAIN, 15));
			lostarea = new JLabel("   실종장소 : ");
			lostarea.setFont(new Font("한컴 윤고딕 240", Font.PLAIN, 15));
			
			name2 = new JLabel(rs.getString("name"));
			name2.setFont(new Font("한컴 윤고딕 240", Font.PLAIN, 15));
			kind2 = new JLabel(rs.getString("kind"));
			kind2.setFont(new Font("한컴 윤고딕 240", Font.PLAIN, 15));
			birth2 = new JLabel(rs.getString("birth"));
			birth2.setFont(new Font("한컴 윤고딕 240", Font.PLAIN, 15));
			gender2 = new JLabel(rs.getString("gender"));
			gender2.setFont(new Font("KoPub돋움체 Medium", Font.BOLD, 15));
			people2 = new JLabel(rs.getString("pname"));
			people2.setFont(new Font("한컴 윤고딕 240", Font.PLAIN, 15));
			phone2 = new JLabel(rs.getString("phone"));
			phone2.setFont(new Font("한컴 윤고딕 240", Font.PLAIN, 15));
			neuter2 = new JLabel(rs.getString("neuter"));
			neuter2.setFont(new Font("KoPub돋움체 Medium", Font.BOLD, 15));
			lostday2 = new JLabel(rs2.getString("실종날짜"));
			lostday2.setFont(new Font("한컴 윤고딕 240", Font.PLAIN, 15));
			lostarea2 = new JLabel(rs2.getString("실종장소"));
			lostarea2.setFont(new Font("한컴 윤고딕 240", Font.PLAIN, 15));

			enter = new RoundedButton("확 인");
			enter.setFont(new Font("한컴 윤고딕 240", Font.PLAIN, 13));
			enter.setText("확   인");
			enter.setPreferredSize(new Dimension(30, 15));
			enter.addActionListener(this);
		
			p1.add(name);
			p1.add(name2);
			p1.add(kind);
			p1.add(kind2);
			p1.add(birth);
			p1.add(birth2);
			p1.add(people);
			p1.add(people2);
			p1.add(phone);
			p1.add(phone2);
			p1.add(lostday);
			p1.add(lostday2);
			p1.add(lostarea);
			p1.add(lostarea2);
			
			pa.add(image);
			image.setBounds(12, 10, 410, 279);
			pa.add(p1);
			p1.setBounds(12, 299, 410, 333);
			pa.add(enter);
			enter.setBounds(335, 642, 87, 30);
			
			getContentPane().add(pa);
			
			setVisible(true);
			
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, "존재하지 않는 정보입니다");
		}
	}
	private void displayImage() {
        ImageIcon imageIcon = new ImageIcon(imagePaths[currentImageIndex]);
		Image img = imageIcon.getImage();
		Image newImg = img.getScaledInstance(410, 200, java.awt.Image.SCALE_SMOOTH);
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
	public void actionPerformed(ActionEvent e) {
		dispose();
	}
}
