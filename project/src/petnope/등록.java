package petnope;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;


public class 등록 extends JFrame implements ActionListener{
	DogManager dog = new DogManager();
	MyPetManager mp = new MyPetManager();
	private JPanel pa, p1, middle, check;
	private JLabel code, name, kind, birth, people, phone, l1;
	private JTextField tc, tn, tk, tb, tp, tph;
	private RoundedButton back, enrol, camera;
	public JButton image;
	private JRadioButton male, female;
	private JCheckBox cb;
	public String imagep;
	
	public 등록() {			
		setTitle("강아지 등록하기");
		setSize(450, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);		
		
		p1 = new JPanel(new BorderLayout());
		p1.setBackground(new Color(254, 255, 191));
		back = new RoundedButton("<BACK");
		back.setText("  <");
		back.setPreferredSize(new Dimension(0, 25));
		back.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 12));
		back.addActionListener(this);
		
		camera = new RoundedButton("촬 영");
		camera.setText("비문 촬영");
		camera.setPreferredSize(new Dimension(0, 25));
		camera.setFont(new Font("한컴 윤고딕 240", Font.PLAIN, 12));
		camera.addActionListener(this);
		
		middle = new JPanel(new GridLayout(16, 0, 0, 5));
		middle.setBackground(new Color(254, 255, 191));
		image = new JButton("이미지를 선택하세요");
		image.setFont(new Font("한컴 윤고딕 240", Font.PLAIN, 12));
		image.setBackground(new Color(219, 219, 219));
		image.setBorder(null);
		image.setPreferredSize(new Dimension(300, 200));
		image.addActionListener(this);	
		l1 = new JLabel("이미지를 터치하면 대표 사진이 변경돼요");
		l1.setFont(new Font("한컴 윤고딕 230", Font.PLAIN, 10));
		
		code = new JLabel("  등록번호");
		code.setFont(new Font("한컴 윤고딕 240", Font.PLAIN, 14));
		name = new JLabel("  반려견 이름");
		name.setFont(new Font("한컴 윤고딕 240", Font.PLAIN, 14));
		kind = new JLabel("  반려견 품종");
		kind.setFont(new Font("한컴 윤고딕 240", Font.PLAIN, 14));
		birth = new JLabel("  반려견 생년월일");
		birth.setFont(new Font("한컴 윤고딕 240", Font.PLAIN, 14));
		ButtonGroup gender = new ButtonGroup();
		male = new JRadioButton("수컷");
		male.setBackground(new Color(254, 255, 191));
		male.setSelected(true);
		male.setFont(new Font("한컴 윤고딕 240", Font.PLAIN, 14));
		female = new JRadioButton("암컷");
		female.setFont(new Font("한컴 윤고딕 240", Font.PLAIN, 14));
		female.setBackground(new Color(254, 255, 191));
		check = new JPanel();
		check.setBackground(new Color(254, 255, 191));
		gender.add(male);
		gender.add(female);
		check.add(male);
		check.add(female);
		people = new JLabel("  등록자 이름");
		people.setFont(new Font("KoPub돋움체 Medium", Font.BOLD, 15));
		phone = new JLabel("  등록자 연락처");
		phone.setFont(new Font("KoPub돋움체 Medium", Font.BOLD, 15));
		cb = new JCheckBox("중성화");
		cb.setFont(new Font("한컴 윤고딕 240", Font.PLAIN, 13));
		cb.setHorizontalAlignment(JLabel.CENTER);
		cb.setBackground(new Color(254, 255, 191));
		enrol = new RoundedButton("등 록");
		enrol.setPreferredSize(new Dimension(10, 30));
		enrol.setFont(new Font("한컴 윤고딕 240", Font.BOLD, 14));
		enrol.addActionListener(this);
		tc = new JTextField(10);
		tn = new JTextField(10);
		tk = new JTextField(10);
		tb = new JTextField(10);
		tp = new JTextField(10);
		tph = new JTextField(10);

		middle.add(l1);
		middle.add(code);
		middle.add(tc);
		middle.add(name);
		middle.add(tn);
		middle.add(kind);
		middle.add(tk);
		middle.add(birth);
		middle.add(tb);
		middle.add(check);
		middle.add(people);
		middle.add(tp);
		middle.add(phone);
		middle.add(tph);
		middle.add(cb);
		
		p1.add(image, BorderLayout.NORTH);
		p1.add(middle, BorderLayout.CENTER);
		p1.add(enrol, BorderLayout.SOUTH);
		
		pa = new JPanel();
		pa.setLayout(null);
		pa.setBackground(new Color(254, 255, 191));
		
		pa.add(back);
		back.setBounds(-14, 7, 36, 23);
		pa.add(camera);
		camera.setBounds(34, 7, 76, 23);
		pa.add(p1);
		p1.setBounds(12, 40, 410, 711);

		getContentPane().add(pa);
		setVisible(true);
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == back) {
			dispose();
			new mainPage();
		}
		if(e.getSource() == camera) {
			dispose();
		    new WebcamViewer();
		}		
		if(e.getSource() == enrol) {
			if(tc.getText().isEmpty())
				JOptionPane.showMessageDialog(등록.this,
                        "등록번호를 입력하세요",
                        "등록번호",
                        JOptionPane.WARNING_MESSAGE);
			else if(tn.getText().isEmpty())
				JOptionPane.showMessageDialog(등록.this,
                        "반려견 이름을 입력하세요",
                        "반려견 이름",
                        JOptionPane.WARNING_MESSAGE);
			else if(tk.getText().isEmpty())
				JOptionPane.showMessageDialog(등록.this,
                        "품종을 입력하세요",
                        "품종",
                        JOptionPane.WARNING_MESSAGE);
			else if(tb.getText().isEmpty())
				JOptionPane.showMessageDialog(등록.this,
                        "생년월일을 입력하세요",
                        "생년월일",
                        JOptionPane.WARNING_MESSAGE);
			else if(tp.getText().isEmpty())
				JOptionPane.showMessageDialog(등록.this,
                        "등록자 이름을 입력하세요",
                        "등록자 이름",
                        JOptionPane.WARNING_MESSAGE);
			else if(tph.getText().isEmpty())
				JOptionPane.showMessageDialog(등록.this,
                        "등록자 연락처를 입력하세요",
                        "등록자 연락처",
                        JOptionPane.WARNING_MESSAGE);
			else {
				try {
					int num = Integer.parseInt(tc.getText().trim());
					String name = tn.getText().trim(); 
					String kind = tk.getText().trim(); 
					String birth = tb.getText().replace("/", "").replace("-", "").trim();
					if(birth.length() == 6)
						birth = "20" + birth;
					birth = birth.substring(0, 4) + "-" + birth.substring(4, 6) + "-" + birth.substring(6);
					String gender = male.isSelected() ? male.getText() : female.getText();
					String pname = tp.getText().trim(); 
					String phone = tph.getText().replace("-", "").trim();				
					phone = phone.substring(0, 3) + "-" + phone.substring(3, 7) + "-" + phone.substring(7);
					String path = "sample/" + num + ".jpg";
					String neuter = cb.isSelected() ? "o" : "x"; 

					if(dog.searchRedun(num)) {
						JOptionPane.showMessageDialog(null, "이미 존재하는 등록번호입니다");
					}
					else if(phone.length() != 13) {
						JOptionPane.showMessageDialog(null, "연락처를 정확히 입력해주세요 \n예) 010-1234-5678");
					}
					else if(birth.length() != 10) {
						JOptionPane.showMessageDialog(null, "생년월일을 정확히 입력해주세요 \n예) 2023-12-25");
					}
					else if(dog.searchRedun(name, pname, phone)) {
						if(imagep.equals(null))
							throw new Exception();
						ImageCtrl.saveImage(imagep, path);
						
						dog.inputData(num, name, kind, birth, gender, pname, phone, neuter, path);
						mp.inputData(LoginScreen.myid, num);
						JOptionPane.showMessageDialog(null, "등록되었습니다");
						dispose();
						new mainPage();
					}	
					else
						JOptionPane.showMessageDialog(null, "이미 등록된 강아지입니다");
				}
				catch(StringIndexOutOfBoundsException e1) {
					JOptionPane.showMessageDialog(등록.this,
	                        "생년월일 예) 2023-12-25 \n등록자 연락처 예) 010-1234-5678",
	                        "생년월일, 등록자 연락처",
	                        JOptionPane.WARNING_MESSAGE);
				}
				catch(Exception e2) {
					JOptionPane.showMessageDialog(등록.this,
	                        "이미지를 선택해주세요",
	                        "사진",
	                        JOptionPane.WARNING_MESSAGE);
				}
			}
		}
		if(e.getSource() == image) {
			imagep = ImageCtrl.imgCtl(image);
		}
	}
}
