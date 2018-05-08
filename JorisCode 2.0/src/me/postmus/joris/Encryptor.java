package me.postmus.joris;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Encryptor {
	int width = 200;
	int height = 200;
	BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
	File f = null;
	JFrame frame = new JFrame("Code");
	Color backgroundColor = new Color(119,255,245);
	
	public String readFile(String pathname) throws IOException {

	    File file = new File(pathname);
	    StringBuilder fileContents = new StringBuilder((int)file.length());
	    Scanner scanner = new Scanner(file);
	    String lineSeparator = System.getProperty("line.separator");

	    try {
	        while(scanner.hasNextLine()) {
	            fileContents.append(scanner.nextLine() + lineSeparator);
	        }
	        return fileContents.toString();
	    } finally {
	        scanner.close();
	    }
	}
	
	public int[][] encryptText(String text) {
		int textLength = text.length();
		int[][] encryptedText = new int[textLength/3+1][3];
		
		int currentPixel = -1;
		for(int i = 0; i < textLength; i++) {
			char curChar = text.charAt(i);
			int charNumeric = Character.getNumericValue(curChar);
			if (i%3 == 0) {currentPixel++;}
			if (charNumeric > 0 && charNumeric < 36) {
				encryptedText[currentPixel][i%3] = charNumeric*7;
			}else {
				encryptedText[currentPixel][i%3] = 1;
			}
		}
		return encryptedText;
	}
	
	public void generateImg(int[][] encryptedText) {
		int currentPixel = 0;
		int pixelLength = encryptedText.length;
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				if (currentPixel < pixelLength) {
				//Actual Words
				int r = encryptedText[currentPixel][0]; //red
		        int g = encryptedText[currentPixel][1]; //green
		        int b = encryptedText[currentPixel][2]; //blue
		        Color color = new Color(r, g, b);
		        int p = color.getRGB();
		        img.setRGB(x, y, p);
		        currentPixel++; } else {
		        //Random
		        int r = (int)(Math.random()*256); //red
		        int g = (int)(Math.random()*256); //green
		        int b = (int)(Math.random()*256); //blue
		        Color color = new Color(r, g, b);
			    int p = color.getRGB();
			    img.setRGB(x, y, p);
		        }
			}
		}
		
		frame.getContentPane().setLayout(new FlowLayout());
		frame.getContentPane().add(new JLabel(new ImageIcon(img)));
		frame.setSize(width, height);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setAlwaysOnTop(true);
		frame.setIconImage(img);
		frame.setVisible(true);
		
		Window window = new Window();
		window.addPicture(img);
		
		try{
			//f = new File("F:\\Output.png");
			f = new File("H:\\JorisCode-Outputs\\Output.png");
			ImageIO.write(img, "png", f);
			System.out.println("Printing on " + f.getPath());
			System.out.println("Encrypted Image succesfully printed.");
		}catch(IOException e){
		    System.out.println("Error: " + e);
		}
	}	
}
