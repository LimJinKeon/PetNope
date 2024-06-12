package petnope;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.SocketException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.BevelBorder;

public class mainPage extends JFrame implements ActionListener{
	private JPanel p1, p2, p3, p4, p5, p6;
	private JLabel main, l1, l2, l3, l4;
	private JButton enrol, update, find, lost, commu;
    private Timer timer;
    private int currentImageIndex;
    private String p = "mainImage//";
    private String[] imagePaths = {p + "main0.jpg", p + "main1.jpg", p + "main2.jpg",
    								p + "main3.jpg", p + "main4.jpg", p + "main5.jpg"};
    private JButton myPage;
	
	public mainPage() {
		new Thread(() -> {
			new ChatServer();
		}).start();
		setTitle("펫노프");
		setSize(450, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		
		ImageIcon icon = new ImageIcon("mainimage/main0.jpg");
		main = new JLabel();
		main.setIcon(icon);
		
		p1 = new JPanel();
		p1.setLayout(new GridLayout(2, 1, 0, 1));
		p2 = new JPanel(new GridLayout(1, 2));
		p3 = new JPanel();
		p3.setLayout(new BorderLayout());
		
		l1 = new JLabel("반려견 등록하기");		
		l1.setFont(new Font("MD개성체", Font.BOLD, 20));
		l2 = new JLabel("우리의 반려견을 안전하게");
		l2.setFont(new Font("MD개성체", Font.PLAIN, 12));
		l3 = new JLabel();
		ImageIcon icon2 = new ImageIcon("반려견.jpg");
		l3.setIcon(icon2);
		
		enrol = new JButton("등록하기");
		enrol.setBackground(new Color(255, 231, 147));
		enrol.setFont(new Font("한컴 윤고딕 240", Font.PLAIN, 13));
		enrol.setPreferredSize(new Dimension(0, 30));
		enrol.addActionListener(this);	
		update = new JButton("수정하기");
		update.setBackground(new Color(255, 231, 147));
		update.setFont(new Font("한컴 윤고딕 240", Font.PLAIN, 13));
		update.addActionListener(this);
		
		p1.add(l1);
		p1.add(l2);
		p2.add(enrol);
		p2.add(update);
		p3.add(p1, BorderLayout.NORTH);
		p3.add(l3, BorderLayout.CENTER);
		p3.add(p2, BorderLayout.SOUTH);
		p1.setBackground(new Color(254, 253, 192));

		p4 = new JPanel();
		p5 = new JPanel();
		p4.setLayout(new GridLayout(5, 1, 10, 10));
		
		l4 = new JLabel("신고");
		l4.setHorizontalAlignment(SwingConstants.LEFT);
		l4.setIcon(new ImageIcon("신고.png"));
		l4.setFont(new Font("MD개성체", Font.BOLD, 20));
		l4.setBorder(null);
		l4.setPreferredSize(new Dimension(350, 30));
		
		find = new JButton("목격 신고");
		find.setBackground(new Color(255, 231, 147));
		find.setHorizontalAlignment(SwingConstants.LEFT);
		find.setIcon(new ImageIcon("목격.png"));
		find.setFont(new Font("한컴 윤고딕 240", Font.PLAIN, 15));
		find.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		find.setPreferredSize(new Dimension(350, 30));
		find.addActionListener(this);
		
		lost = new JButton("실종 신고");
		lost.setBackground(new Color(255, 231, 147));
		lost.setHorizontalAlignment(SwingConstants.LEFT);
		lost.setIcon(new ImageIcon("실종.png"));
		lost.setFont(new Font("한컴 윤고딕 240", Font.PLAIN, 15));
		lost.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		lost.setPreferredSize(new Dimension(350, 30));
		lost.addActionListener(this);
		
		p4.add(l4);
		p4.add(find);
		p4.add(lost);
		p4.setBackground(new Color(254, 253, 192));

		p5.add(p3);
		p5.add(p4);
		p5.setBackground(new Color(254, 253, 192));
		
		commu = new JButton("커뮤니티");
		commu.setBackground(new Color(255, 231, 147));
		commu.setIcon(new ImageIcon("커뮤니티.png"));
		commu.setFont(new Font("한컴 윤고딕 240", Font.PLAIN, 13));
		commu.setPreferredSize(new Dimension(0, 30));
		commu.addActionListener(this);
	
		p6 = new JPanel();
		p6.setLayout(new BorderLayout());
		
		p6.add(main, BorderLayout.PAGE_START);
		p6.add(p5, BorderLayout.CENTER);
		p6.add(commu, BorderLayout.PAGE_END);
		
		JLabel l = new JLabel("마이페이지");
		l.setFont(new Font("MD개성체", Font.BOLD, 20));	
		l.setHorizontalAlignment(SwingConstants.LEFT);
		l.setIcon(new ImageIcon("mypage.png"));
		myPage = new JButton("마이페이지");
		myPage.setIcon(new ImageIcon("마이페이지.png"));
		myPage.setBackground(new Color(255, 231, 147));
		myPage.setHorizontalAlignment(SwingConstants.LEFT);
		myPage.setFont(new Font("한컴 윤고딕 240", Font.PLAIN, 15));
		myPage.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		myPage.setPreferredSize(new Dimension(350, 30));
		myPage.addActionListener(this);
		p4.add(l);
		p4.add(myPage);
		
		getContentPane().add(p6);
		setVisible(true);
		setupTimer();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == enrol) {
			dispose();
			new 등록();
		}
		if(e.getSource() == update) {
			MyPetManager mp = new MyPetManager();
			if(!mp.searchUpdate(LoginScreen.myid)) {
				JOptionPane.showMessageDialog(mainPage.this,
                        "등록된 반려견이 없습니다",
                        "수정",
                        JOptionPane.WARNING_MESSAGE);
			}
			else {
				dispose();
				new update();
			}
		}
		if(e.getSource() == find) {
			dispose();
			new Find();
		}
		if(e.getSource() == lost) {
			dispose();
			new lose();
		}
		if(e.getSource() == myPage) {
			dispose();
			new MyPage();
		}
		if(e.getSource() == commu) {	
			String nickname = JOptionPane.showInputDialog("닉네임 입력:");
	        if (nickname == null || nickname.trim().isEmpty()) {
	            JOptionPane.showMessageDialog(null, "잘못된 입력입니다");
	        }
	        else {

	        	dispose();
				ChatUI ChatUI = new ChatUI(nickname);
			    ChatUI.ServerConnect();
			    ChatUI.ChattUI();
	        }
		}
	}
	private void displayImage() {
        ImageIcon imageIcon = new ImageIcon(imagePaths[currentImageIndex]);
        main.setIcon(imageIcon);
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