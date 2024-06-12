package petnope;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;


public class ChatUI {
    private static final String SERVER_IP = "localhost";
    private static final int SERVER_PORT = 1111;

    private JFrame frame;
    private JTextArea chatArea;
    private JTextField inputField;
    private JButton sendButton;
    private JButton backButton;

    private Socket socket;
    private PrintWriter writer;
    private String nickname;

    public ChatUI(String nickname) {
        this.nickname = nickname;
    }

    public void ChattUI() {
    	if (nickname == null || nickname.trim().isEmpty())frame.dispose();
    		 
        frame = new JFrame("커뮤니케이션");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450, 800);
        frame.setLocationRelativeTo(null);

        chatArea = new JTextArea();
        chatArea.setEditable(false);
 
        JScrollPane scrollPane = new JScrollPane(chatArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        inputField = new JTextField();
        sendButton = new JButton("보내기");
        backButton = new JButton("뒤로가기");
        
        backButton.setBackground(new Color(255,128,0));

        // 사이즈 조정
        inputField.setPreferredSize(new Dimension(220, 80));
        sendButton.setPreferredSize(new Dimension(80, 80));

        
        // 보내기 버튼
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });

        
        // 뒤로가기 버튼
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	writer.println(nickname+"님이 퇴장하셨습니다");
            	frame.dispose();
            	new mainPage();
            }
        });
 
        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(backButton, BorderLayout.CENTER);
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(inputPanel, BorderLayout.SOUTH);
        frame.setVisible(true);

    }

    public void ServerConnect() {
        try {
            socket = new Socket(SERVER_IP, SERVER_PORT);
            System.out.println("서버에 연결되었습니다.");

            // 서버에 닉네임 전송
            writer = new PrintWriter(socket.getOutputStream(), true);
            //닉네임 출력
            writer.println(nickname+"님이 입장하셨습니다");

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            Thread receiveThread = new Thread(() -> {
                String message;
                try {
                    while ((message = reader.readLine()) != null) {
                        appendToChatArea(message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            receiveThread.start();

        } catch (SocketException e) {

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    private void sendMessage() {
        String message = inputField.getText();
  
        if (!message.isEmpty()) {
            writer.println(nickname + ": " + message);
            inputField.setText("");
        }
    }

    private void appendToChatArea(String message) {
        SwingUtilities.invokeLater(() -> {
            chatArea.append(message + "\n");
        });
    }
    public void setInitialChatLogs(List<String> initialLogs) {
        SwingUtilities.invokeLater(() -> {
            for (String log : initialLogs) {
                chatArea.append(log + "\n");
            }
        });
    }
}

