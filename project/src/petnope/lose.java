package petnope;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLSyntaxErrorException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class lose {

    public JFrame frame;
    private JTextField intextField;
    private JTextField DatetextField;
    private JTextField PlacetextField;
    private String image_Path;
    DogManager2 dog2 = new DogManager2();
    
    public lose() {
        frame = new JFrame();
        frame.getContentPane().setBackground(new Color(253, 252, 202));
        frame.setBounds(100, 100, 450, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setTitle("실종 신고");
        
        // Panel 1
        JPanel panel1 = new JPanel();
        panel1.setBounds(12, 64, 410, 250);
        frame.getContentPane().add(panel1);
        panel1.setLayout(null);

        JButton imageButton = new JButton("이미지를 선택해주세요");
        imageButton.setFont(new Font("굴림", Font.PLAIN, 15));
        imageButton.setBackground(new Color(219, 219, 219));
        imageButton.setBounds(0, 0, 410, 250);
        imageButton.setBorder(null);
        panel1.add(imageButton);
        imageButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(e.getSource() == imageButton) {
        			image_Path = ImageCtrl.imgCtl(imageButton);
        		}
        	}
        });

        // Panel 2
        JPanel panel2 = new JPanel();
        panel2.setBackground(new Color(253, 252, 202));
        panel2.setBounds(12, 324, 410, 188);
        frame.getContentPane().add(panel2);
        panel2.setLayout(null);

        JLabel codeLabel = new JLabel("등록 코드:");
        codeLabel.setFont(new Font("한컴 윤고딕 240", Font.PLAIN, 13));
        codeLabel.setBounds(12, 10, 143, 34);
        panel2.add(codeLabel);

        intextField = new JTextField();
        intextField.setBounds(118, 16, 280, 26);
        panel2.add(intextField);
        intextField.setColumns(10);

        JLabel dateLabel = new JLabel("실종 날짜:");
        dateLabel.setFont(new Font("한컴 윤고딕 240", Font.PLAIN, 13));
        dateLabel.setBounds(12, 64, 70, 26);
        panel2.add(dateLabel);

        DatetextField = new JTextField();
        DatetextField.setText("예) 2023-12-25 11:47:00");
        DatetextField.setBounds(118, 64, 280, 26);
        panel2.add(DatetextField);
        DatetextField.setColumns(10);

        JLabel placeLabel = new JLabel("실종 장소:");
        placeLabel.setFont(new Font("한컴 윤고딕 240", Font.PLAIN, 13));
        placeLabel.setBounds(12, 100, 70, 26);
        panel2.add(placeLabel);

        PlacetextField = new JTextField();
        PlacetextField.setColumns(10);
        PlacetextField.setBounds(118, 100, 280, 26);
        panel2.add(PlacetextField);

        frame.setVisible(true);
        
        RoundedButton registerButton = new RoundedButton("등록하기");
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if(intextField.getText().isEmpty()) {
            		JOptionPane.showMessageDialog(null, "등록코드를 입력해주세요");
            	}
            	else if(DatetextField.getText().isEmpty()) {
            		JOptionPane.showMessageDialog(null, "실종날짜를 입력해주세요 \n예) 2023-11-27 11:47:00");
            	}
            	else if(PlacetextField.getText().isEmpty()) {
            		JOptionPane.showMessageDialog(null, "실종장소를 입력해주세요");
            	}
            	else {
	            		int code = Integer.parseInt(intextField.getText().trim());
		                String date = DatetextField.getText().trim();
		                String place = PlacetextField.getText().trim();          		
            		if(!dog2.searchFind(code)) {
            			JOptionPane.showMessageDialog(null, "이미 실종신고된 반려견입니다");
            		}
            		else {
		                try {
		                	System.out.println(2);        
		                	String path = "sample2/" + code + ".jpg";		                	
			        
		                	if(image_Path.equals(null))
								throw new Exception();
		                	ImageCtrl.saveImage(image_Path, path);

		                	dog2.inputData(code, date, place, path, LoginScreen.myid);
		                	JOptionPane.showMessageDialog(null, "실종신고가 정상적으로 완료되었습니다");
		                	frame.dispose();
		                	new mainPage();
	                    } catch (SQLSyntaxErrorException e1) {
	                    	JOptionPane.showMessageDialog(null, "실종날짜를 정확히 입력해주세요 \n예) 2023-11-27 11:47:00");
	                    } catch (Exception e2) {
	                    	JOptionPane.showMessageDialog(null, "이미지를 선택해주세요1");
		                }
            		}
            	}
            }
        });
        registerButton.setBounds(298, 145, 100, 34);
        panel2.add(registerButton);
        
        RoundedButton back = new RoundedButton("<BACK");
        back.setText("  <");
        back.setPreferredSize(new Dimension(0, 25));
        back.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 12));
        back.setBounds(-14, 7, 36, 23);
        frame.getContentPane().add(back);
        back.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		frame.dispose();
        		new mainPage();
        	}
        });
    }
}
