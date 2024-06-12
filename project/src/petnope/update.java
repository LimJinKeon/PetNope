package petnope;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class update extends JFrame{
    private JPanel contentPane;
    private JTextField textField,nameField,kindField,birthField;
    private JLabel lblNewLabel_1,lblNewLabel_2,lblNewLabel_3,lblNewLabel_4,lblNewLabel_5,lblNewLabel_6;
    private JFileChooser filechooser;
    DogManager dog = new DogManager();
	MyPetManager mp = new MyPetManager();
    String imagep;
    int id;
    
    public update(){
        super("Update Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450,800);
        setVisible(true);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(254, 255, 191));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        JButton btnNewButton = new RoundedButton("    <");
        btnNewButton.setFont(new Font("한컴 윤고딕 250", Font.PLAIN, 12));
        btnNewButton.setBounds(-25, 10, 55, 23);

        contentPane.setLayout(null);
        btnNewButton.setHorizontalAlignment(SwingConstants.LEFT);
        contentPane.add(btnNewButton);

        JLabel lblNewLabel = new JLabel("고유 등록번호 : ");
        lblNewLabel.setFont(new Font("한컴 윤고딕 240", Font.PLAIN, 15));
        lblNewLabel.setBounds(12, 53, 126, 29);
        contentPane.add(lblNewLabel);

        textField = new JTextField();
        textField.setBounds(120, 53, 195, 26);
        contentPane.add(textField);
        textField.setColumns(10);

        lblNewLabel_1 = new JLabel("정보수정하기");
        lblNewLabel_1.setFont(new Font("한컴 윤고딕 240", Font.PLAIN, 19));
        lblNewLabel_1.setBounds(12, 95, 108, 20);
        contentPane.add(lblNewLabel_1);

        lblNewLabel_2 = new JLabel("프로필 사진");
        lblNewLabel_2.setFont(new Font("한컴 윤고딕 240", Font.PLAIN, 13));
        lblNewLabel_2.setBounds(22, 125, 74, 16);
        contentPane.add(lblNewLabel_2);

        lblNewLabel_3 = new JLabel("반려동물 이름");
        lblNewLabel_3.setFont(new Font("한컴 윤고딕 240", Font.PLAIN, 13));
        lblNewLabel_3.setBounds(22, 281, 89, 16);
        contentPane.add(lblNewLabel_3);

        lblNewLabel_4 = new JLabel("견종");
        lblNewLabel_4.setFont(new Font("한컴 윤고딕 240", Font.PLAIN, 13));
        lblNewLabel_4.setBounds(22, 364, 37, 16);
        contentPane.add(lblNewLabel_4);

        nameField = new JTextField();
        nameField.setBounds(22, 307, 400, 26);
        contentPane.add(nameField);
        nameField.setColumns(10);

        kindField = new JTextField();
        kindField.setColumns(10);
        kindField.setBounds(22, 389, 400, 26);
        contentPane.add(kindField);

        lblNewLabel_5 = new JLabel("최대 20자까지 입력할 수 있어요");
        lblNewLabel_5.setFont(new Font("한컴 윤고딕 230", Font.PLAIN, 10));
        lblNewLabel_5.setBounds(22, 343, 172, 16);
        contentPane.add(lblNewLabel_5);

        lblNewLabel_6 = new JLabel("성별");
        lblNewLabel_6.setFont(new Font("한컴 윤고딕 240", Font.PLAIN, 15));
        lblNewLabel_6.setBounds(22, 425, 37, 16);
        contentPane.add(lblNewLabel_6);

        ButtonGroup gender = new ButtonGroup();
        
        JRadioButton FirstButton = new JRadioButton("수컷");
        FirstButton.setBackground(new Color(254, 255, 191));
        FirstButton.setBounds(67, 421, 55, 23);
        contentPane.add(FirstButton);

        JRadioButton SecondButton = new JRadioButton("암컷");
        SecondButton.setBackground(new Color(254, 255, 191));
        SecondButton.setBounds(120, 421, 55, 23);
        contentPane.add(SecondButton);
        
        gender.add(FirstButton);
		gender.add(SecondButton);

        JCheckBox Neuter = new JCheckBox("중성화");
        Neuter.setBackground(new Color(254, 255, 191));
        Neuter.setBounds(179, 421, 95, 23);
        contentPane.add(Neuter);

        JLabel birth = new JLabel("생일");
        birth.setFont(new Font("한컴 윤고딕 240", Font.PLAIN, 15));
        birth.setBounds(22, 466, 44, 16);
        contentPane.add(birth);

        birthField = new JTextField();
        birthField.setBounds(67, 463, 355, 26);
        contentPane.add(birthField);
        birthField.setColumns(10);

        JButton updateButton = new RoundedButton("수정하기");
        updateButton.setBounds(327, 524, 95, 29);
        contentPane.add(updateButton);

        JButton findButton = new RoundedButton("조회하기");
        findButton.setFont(new Font("한컴 윤고딕 240", Font.PLAIN, 13));
        findButton.setBounds(327, 51, 95, 29);
        contentPane.add(findButton);

        JButton ImageButton = new JButton("Image");
        ImageButton.setBounds(23, 151, 166, 120);
        contentPane.add(ImageButton);
        
        getContentPane().add(contentPane);
        setVisible(true);
        
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new mainPage();
            }
        });
        findButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                id = Integer.parseInt(textField.getText());
                if(mp.searchUpdate2(LoginScreen.myid, id)) {
                	JOptionPane.showMessageDialog(null, "다른 반려인의 반려견입니다");
                }
                else {
	                ResultSet resultSet = dog.searchView(id);
	
	                try {
	                    if (resultSet.next()) {
	                        String name = resultSet.getString("name");
	                        String kind = resultSet.getString("kind");
	                        String birth = resultSet.getString("birth");
	                        String gender = resultSet.getString("gender");
	                        String neuter = resultSet.getString("neuter");
	                        String imagePath = resultSet.getString("image_Path");
	
	                        ImageIcon icon = new ImageIcon(imagePath);
	                        Image image = icon.getImage().getScaledInstance(ImageButton.getWidth(), 
	                        		ImageButton.getHeight(), Image.SCALE_SMOOTH);
	                        ImageIcon changeImg = new ImageIcon(image);
	            			ImageButton.setIcon(changeImg);
	            			ImageButton.setText(null);
	            			
	                        nameField.setText(name); // 반려동물 이름 필드에 데이터베이스 값 적용
	                        kindField.setText(kind); // 견종 필드에 데이터베이스 값 적용
	                        birthField.setText(birth); // 생일 필드에 데이터베이스 값 적용
	
	                        if (gender.equals("수컷")) {
	                            FirstButton.setSelected(true);
	                            SecondButton.setSelected(false); // 여성을 선택할 때 남성 버튼 해제
	                        } else {
	                            SecondButton.setSelected(true);
	                            FirstButton.setSelected(false); // 남성을 선택할 때 여성 버튼 해제
	                        }
	
	                        // 성별 데이터가 없는 경우에 대한 처리
	                        if (!resultSet.wasNull()) {
	                            // 데이터가 있으면 선택하도록 설정
	                            FirstButton.setSelected(false);
	                            SecondButton.setSelected(false);
	                        }
	
	                        // 중성화 여부 설정
	                        if (neuter.equals("o")) {
	                            Neuter.setSelected(true);
	                        } else {
	                            Neuter.setSelected(false);
	                        }
	
	                    } else {
	                        textField.setText("");
	                        nameField.setText("");
	                        kindField.setText("");
	                        birthField.setText("");
	                        ImageButton.setText("Image");
	                        ImageButton.setIcon(null);
	                        JOptionPane.showMessageDialog(null, "해당하는 레코드가 없습니다.", "레코드 없음", 
	                        		JOptionPane.WARNING_MESSAGE);
	                    }
	                } catch (SQLException ex) {
	                    ex.printStackTrace();
	                }
                }
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String updatedName = nameField.getText().trim(); // 수정할 이름
                String updatedKind = kindField.getText().trim(); // 수정할 품종
                String updatedBirthYear = birthField.getText().trim(); // 수정할 생년월일
                String updatedNeuter = Neuter.isSelected() ? "o" : "x"; // 수정할 중성화 유무

                // 여기서 데이터베이스에서 가져온 값을 수정하고자 하는 코드를 작성합니다.
                Connection conn = null;
                Statement stmt = null;
                if(textField.getText().isEmpty()) {
                	JOptionPane.showMessageDialog(null, "등록코드를 입력해주세요");
                }
                try {
                    conn = dog.getConnection();
                    stmt = conn.createStatement();

                    String sql = String.format("UPDATE Capstone_table SET name='%s', kind='%s', birth='%s', neuter='%s'"
                    		+ " WHERE num = %d", updatedName, updatedKind, updatedBirthYear, updatedNeuter, id);


                    int rowsAffected = stmt.executeUpdate(sql);
                    if (rowsAffected > 0) {
                    	JOptionPane.showMessageDialog(null, "성공적으로 수정되었습니다");
                    } else{
                        JOptionPane.showMessageDialog(null, "등록번호가 없습니다.", "등록번호 없음", JOptionPane.WARNING_MESSAGE);
                    }

                } catch (SQLException ex) {
                    ex.printStackTrace();
                } finally {
                    try {
                        if (stmt != null) stmt.close();
                        if (conn != null) conn.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
    }
}