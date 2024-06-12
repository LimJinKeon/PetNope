package petnope;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.videoio.VideoCapture;

public class WebcamViewer extends JFrame implements ActionListener {
    private JButton b1;
    private VideoCapture videoCapture;
    private Mat lastCapturedFrame;
    private JLabel jLabel;
    
    public WebcamViewer() {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        setVisible(true);
        setSize(650, 550);
        setLocationRelativeTo(null);
        b1 = new JButton("촬영");
        b1.addActionListener(this);

        jLabel = new JLabel();
        JPanel p1 = new JPanel(new BorderLayout());
        p1.add(jLabel, BorderLayout.CENTER);
        p1.add(b1, BorderLayout.SOUTH);
        add(p1);
        
        videoCapture = new VideoCapture(0);
        if(!videoCapture.isOpened()) {
        	JOptionPane.showMessageDialog(WebcamViewer.this,
        			"등록된 카메라가 없습니다",
        			"카메라 부재",
        			JOptionPane.WARNING_MESSAGE);
        }
        
        
        String xmlFile = WebcamViewer.class.getResource("haarcascades/haarcascade_frontalcatface_extended.xml").getPath()
                .replaceFirst("/", ""); 
        CascadeClassifier classifier = new CascadeClassifier(xmlFile);

        Mat Mframe = new Mat();
        new Thread(() -> {
	        try {
	            while (videoCapture.read(Mframe)) {
	            	
	                MatOfRect faceDetections = new MatOfRect();
	                classifier.detectMultiScale(Mframe, faceDetections);
	
	                // 얼굴에 사각형 박스 표시 처리
	                for (Rect rect : faceDetections.toArray()) {
	                    Imgproc.rectangle(Mframe, // where to draw the box
	                            new Point(rect.x, rect.y), // bottom left
	                            new Point(rect.x + rect.width, rect.y + rect.height), // top right
	                            new Scalar(0, 0, 255), 3 // RGB colour
	                    );
	                }
	
	                ImageIcon image = new ImageIcon(Mat2BufferedImage(Mframe));
	                SwingUtilities.invokeLater(() -> {
	                    jLabel.setIcon(image);
	                    jLabel.repaint();
	                });
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
        	}
        }).start();
    }

    public void actionPerformed(ActionEvent e) {
    	if (e.getSource() == b1) {
            captureFrame(); // Capture the current frame
            stopCamera();
            saveCapturedFrame();
            dispose();

            // 추가: 새로운 프레임을 만들어 화면을 표시
            JFrame resultFrame = new JFrame("Captured Image");
            resultFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            resultFrame.setSize(650, 550);
            resultFrame.setLocationRelativeTo(null);
            JButton b = new JButton("저장");
    		JButton retry = new JButton("재촬영");
            try {
	            JLabel resultLabel = new JLabel();
	            ImageIcon resultImage = new ImageIcon(Mat2BufferedImage(lastCapturedFrame));
	            resultLabel.setIcon(resultImage);
	            
	            JPanel p1 = new JPanel(new BorderLayout());
	            JPanel p2 = new JPanel(new GridLayout(1, 2));
	            p2.add(b);
	            p2.add(retry);
	            p1.add(resultLabel, BorderLayout.CENTER);
	            p1.add(p2, BorderLayout.SOUTH);
	            resultFrame.add(p1);
	            resultFrame.setVisible(true);
	            b.addActionListener(new ActionListener() {
	            	public void actionPerformed(ActionEvent e) {
	            		등록 d = new 등록();
	    				resultFrame.dispose();
            	}
            });
            retry.addActionListener(new ActionListener() {
            	public void actionPerformed(ActionEvent e) {
            		resultFrame.dispose();
            		new WebcamViewer();
            	}
            });
            }catch(Exception e2) {
            	e2.printStackTrace();
            }
        }
    }

    private void captureFrame() {
        if (videoCapture.isOpened()) {
            Mat currentFrame = new Mat();
            videoCapture.read(currentFrame);
            if (!currentFrame.empty()) {
                lastCapturedFrame = currentFrame.clone(); // Save the current frame
            }
        }
    }

    private void saveCapturedFrame() {
        if (lastCapturedFrame != null) {
            try {
                BufferedImage capturedImage = Mat2BufferedImage(lastCapturedFrame);
                if (capturedImage != null) {
                    String outputFileName = "nope/captured_image.jpg"; // Change the file name as needed
                    ImageIO.write(capturedImage, "jpg", new File(outputFileName));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void stopCamera() {
        if (videoCapture.isOpened()) {
            videoCapture.release();
        }
    }

    private static BufferedImage Mat2BufferedImage(Mat matrix) throws Exception {
        MatOfByte matOfByte = new MatOfByte();
        Imgcodecs.imencode(".jpg", matrix, matOfByte);
        byte ba[] = matOfByte.toArray();
        return ImageIO.read(new ByteArrayInputStream(ba));
    }
}

