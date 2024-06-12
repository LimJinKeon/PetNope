package petnope;

import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class ImageCtrl {
	
	public static String imgCtl(JButton component) {
		JFileChooser fc = new JFileChooser();
		String path = null;
		int returnVal = fc.showOpenDialog(null);
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			Image img = new ImageIcon(String.valueOf(file)).getImage();
			Image newImg = img.getScaledInstance(component.getWidth(), component.getHeight(), java.awt.Image.SCALE_SMOOTH);
			component.setIcon(new ImageIcon(newImg));
			component.setText(null);
			path = String.valueOf(file);
		}
		return path;
	}
	
	public static void saveImage(String image, String path) {
		try {
			InputStream in = new FileInputStream(image);
			OutputStream os = new FileOutputStream(path);
			while(true){
				int inputData = in.read();
				if(inputData==-1) break;
				os.write(inputData);
			}
			in.close();
			os.close();
		} catch(Exception e) {
			
		}
	}
}
