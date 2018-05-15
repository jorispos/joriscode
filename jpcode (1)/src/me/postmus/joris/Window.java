package me.postmus.joris;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;	
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Window {
	JPanel titlePanel, encPanel, decPanel, datePanel, pathPanel, creditPanel, editPanel, charMaxPanel, moreInfoPanel;
	JLabel titleLabel, encLabel, decUpLabel, encUpLabel, decLabel, dateLabel, pathLabel, pathPath, creditLabel;
	JButton encButton, decButton, encStartButton, decStartButton, pathChooser, pathOpener, textWriter, moreInfoButton;
	Container con, con1;
	Font titleFont = new Font("Monospaced", Font.BOLD, 85);
	Font labFont = new Font("Monospaced", Font.BOLD, 30);
	Font smallFont = new Font("Monospaced", Font.BOLD, 20);
	Font extraSmallFont = new Font("Monospaced", Font.BOLD, 10);
	//Color backgroundColor = new Color(20, 20, 20);
	Color backgroundColor = new Color(35, 0, 0);
	Color buttonBackgroundColor = new Color(80, 80, 80);
	Color panelBackgroundColor = new Color(150, 150, 150);
	Color startButtonBackgroundColor = new Color(105, 189, 48);
	Color cancelButtonBackgroundColor = new Color(255, 96, 96);
	Color pathBackgroundColor = new Color(201, 201, 201);
	private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
	private static final DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy.MM.dd HH.mm.ss");
	JFileChooser textChooser = new JFileChooser();
	JFileChooser picChooser = new JFileChooser();
	JFileChooser directoryChooser = new JFileChooser();
	FileNameExtensionFilter textFilter = new FileNameExtensionFilter("Text Files", "txt");
	FileNameExtensionFilter picFilter = new FileNameExtensionFilter("PNG Images", "png");
	Encryptor encryptor = new Encryptor();
	JEditorPane textPane;
	String encFile = "";
	String decFile = "";
	String encryptionMode = "";
	String outDecPath = "outputDec.txt";
	String outEncPath = "outputEnc.txt";
	String currentDirectory = "./Outputs/";
	String enteredText = "";
	JLabel imgLabel = new JLabel();
	JFrame window = new JFrame("JorisCode");
	JFrame doc = new JFrame("Text Editor");
	JPanel imgPanel = new JPanel();
	
	public void createWindow() {
		
		window.setSize(860, 640);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setBackground(backgroundColor);
		window.setLayout(null);
		window.setLocationRelativeTo(null);
		window.setResizable(false);
		con = window.getContentPane();
		
		titlePanel = new JPanel();
		titlePanel.setBounds(0, 0, 860, 100);
		titlePanel.setBackground(backgroundColor);
		titleLabel = new JLabel("JPCode", SwingConstants.CENTER);
		titleLabel.setForeground(panelBackgroundColor);
		titleLabel.setFont(titleFont);
		titlePanel.add(titleLabel);
		con.add(titlePanel);
		
		imgPanel.setBounds(25, 140, 410, 410);
		imgPanel.setBackground(panelBackgroundColor);
		ImageIcon image = new ImageIcon(".//Res//Output.png");
		window.setIconImage(image.getImage());
		Image imageImg = image.getImage();
		Image resizedImage = imageImg.getScaledInstance(400, 400, java.awt.Image.SCALE_SMOOTH);
		image = new ImageIcon(resizedImage);
		
		imgLabel.setIcon(image);
		imgPanel.add(imgLabel);
		con.add(imgPanel);
		
		encPanel = new JPanel(new BorderLayout(10, 0));
		encPanel.setBounds(455, 140, 380, 160);
		encPanel.setBackground(panelBackgroundColor);
		encLabel = new JLabel("Encryption", SwingConstants.CENTER);
		encLabel.setFont(labFont);
		encButton = new JButton(".txt File");
		encButton.setBackground(buttonBackgroundColor);
		encButton.setFocusPainted(false);
		encButton.setOpaque(true);
		encButton.setBorderPainted(false);
		encButton.setFont(smallFont);
		encButton.addActionListener( new ActionListener()
		{
		    @Override
		    public void actionPerformed(ActionEvent e)
		    {
			    textChooser.setFileFilter(textFilter);
			        int returnVal = textChooser.showOpenDialog(null);
			        if(returnVal == JFileChooser.APPROVE_OPTION) {
			           encUpLabel.setText("file: " + textChooser.getSelectedFile().getName());
			           encFile = textChooser.getSelectedFile().getPath();
			           System.out.println(encFile);
			           encryptionMode = "File";
			    }
		    }
		});
		textWriter = new JButton("Write");
		textWriter.setBackground(buttonBackgroundColor);
		textWriter.setOpaque(true);
		textWriter.setFont(smallFont);
		textWriter.setOpaque(true);
		textWriter.setBorderPainted(false);
		textWriter.setFocusPainted(false);
		textWriter.addActionListener( new ActionListener()
		{
		    @Override
		    public void actionPerformed(ActionEvent e)
		    {
		    	doc.setSize(640, 480);
		    	doc.getContentPane().setBackground(panelBackgroundColor);
		   		doc.setLayout(null);
		   		doc.setLocationRelativeTo(null);
		   		doc.setResizable(false);
		   		ImageIcon image = new ImageIcon(".//Res//Output.png");
		   		doc.setIconImage(image.getImage());
		   		con1 = doc.getContentPane();
	    		
	    		editPanel = new JPanel();
		    	editPanel.setBounds(5, 5, 620, 410);
		    	editPanel.setBackground(backgroundColor);
		   		
		   		charMaxPanel = new JPanel();
		   		charMaxPanel.setBounds(0, 420, 100, 20);
		   		charMaxPanel.setBackground(backgroundColor);
		   		charMaxPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		    		
		   		JLabel charMaxLabel = new JLabel(0 + "/1200000", SwingConstants.LEFT);
		   		charMaxLabel.setFont(extraSmallFont);
		   		charMaxPanel.add(charMaxLabel);
		    		
		   		JTextArea display = new JTextArea(25, 55);
	    		display.setEditable(true);
	    		display.setLineWrap(true);
		    	display.addKeyListener(new KeyListener() {
		    		@Override
		    		public void keyPressed(java.awt.event.KeyEvent e) {
		    			charMaxLabel.setText(display.getText().length() + "/1200000");
		    		}
		    		
					@Override
					public void keyReleased(java.awt.event.KeyEvent e) {
						charMaxLabel.setText(display.getText().length() + "/1200000");
					}

					@Override
					public void keyTyped(java.awt.event.KeyEvent e) {
						charMaxLabel.setText(display.getText().length() + "/1200000");
					}
		   		});
		    	JScrollPane scroll = new JScrollPane(display);
		    	scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		    	
		    	JButton enterButton = new JButton("Save");
		    	enterButton.setBackground(startButtonBackgroundColor);
		    	enterButton.setFocusPainted(false);
		    	enterButton.setOpaque(true);
		    	enterButton.setBorderPainted(false);
		    	enterButton.setFont(smallFont);
		    	enterButton.setBounds(515, 420, 110, 25);
		    	enterButton.addActionListener( new ActionListener()
				{

					@Override
					public void actionPerformed(ActionEvent arg0) {
						enteredText = display.getText();
						encUpLabel.setText("Entered Text");
						encryptionMode = "Text";
						doc.setVisible(false);
					}
					 
				});
		    	
		    	JButton cancelButton = new JButton("Cancel");
		    	cancelButton.setBackground(cancelButtonBackgroundColor);
		    	cancelButton.setFocusPainted(false);
		    	cancelButton.setOpaque(true);
		    	cancelButton.setBorderPainted(false);
		    	cancelButton.setFont(smallFont);
		    	cancelButton.setBounds(390, 420, 110, 25);
		    	cancelButton.addActionListener( new ActionListener()
				{

					@Override
					public void actionPerformed(ActionEvent arg0) {
						doc.setVisible(false);
					}
					 
				});
		    	
		   		editPanel.add(scroll);
		   		con1.add(editPanel);
		   		con1.add(charMaxPanel);
		   		con1.add(enterButton);
		   		con1.add(cancelButton);
				
		   		doc.setVisible(true);
		    }
		});
		
		encStartButton = new JButton("Start");
		encStartButton.setBackground(startButtonBackgroundColor);
		encStartButton.setOpaque(true);
		encStartButton.setBorderPainted(false);
		encStartButton.setFocusPainted(false);
		encStartButton.setFont(smallFont);
		encStartButton.addActionListener( new ActionListener()
		{
			//Start Encryption
		    @Override
		    public void actionPerformed(ActionEvent e)
		    {
			    System.out.println("Starting Encryption");
			    if(encryptionMode == "File") {
			    try {
			    	String textRead = encryptor.readFile(encFile);
					int[][] encryptedText = encryptor.encryptText(textRead);
					BufferedImage img = encryptor.generateImg(encryptedText);
					Image resizedImg = img.getScaledInstance(400, 400, java.awt.Image.SCALE_SMOOTH);
					ImageIcon newIco = new ImageIcon(resizedImg);
					imgLabel.setIcon(newIco);
					imgLabel.revalidate();
					imgLabel.repaint();
					
					LocalDateTime now = LocalDateTime.now();
					dateLabel.setText("Picture generated on " + dtf.format(now));
					dateLabel.revalidate();
					dateLabel.repaint();
					
					JOptionPane.showMessageDialog(null, "Image Succesfully Printed to: " + currentDirectory, "", JOptionPane.INFORMATION_MESSAGE);
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "Please upload a valid text file", "", JOptionPane.INFORMATION_MESSAGE);
				}
			  }else if(encryptionMode == "Text") {
				  	String textRead = enteredText;
				  	int[][] encryptedText = encryptor.encryptText(textRead);
					BufferedImage img = encryptor.generateImg(encryptedText);
					Image resizedImg = img.getScaledInstance(400, 400, java.awt.Image.SCALE_SMOOTH);
					ImageIcon newIco = new ImageIcon(resizedImg);
					imgLabel.setIcon(newIco);
					imgLabel.revalidate();
					imgLabel.repaint();
					
					LocalDateTime now = LocalDateTime.now();
					dateLabel.setText("Picture generated on " + dtf.format(now));
					dateLabel.revalidate();
					dateLabel.repaint();
					
					JOptionPane.showMessageDialog(null, "Image Succesfully Printed to: " + currentDirectory, "", JOptionPane.INFORMATION_MESSAGE);
			  }
		    }
		});
		encUpLabel = new JLabel("Upload Text file to Encrypt", SwingConstants.CENTER);
		encUpLabel.setFont(smallFont);
		encPanel.add(encLabel, BorderLayout.PAGE_START);
		encPanel.add(encUpLabel, BorderLayout.PAGE_END);
		encPanel.add(encButton, BorderLayout.CENTER);
		encPanel.add(encStartButton, BorderLayout.EAST);
		encPanel.add(textWriter, BorderLayout.WEST);
		con.add(encPanel);
		
		decPanel = new JPanel(new BorderLayout(10, 0));
		decPanel.setBounds(455, 320, 380, 160);
		decPanel.setBackground(panelBackgroundColor);
		decLabel = new JLabel("Decryption", SwingConstants.CENTER);
		decLabel.setFont(labFont);
		decButton = new JButton("Select File");
		decButton.setBackground(buttonBackgroundColor);
		decButton.setOpaque(true);
		decButton.setBorderPainted(false);
		decButton.setFocusPainted(false);
		decButton.setFont(smallFont);
		decButton.addActionListener( new ActionListener()
		{
		    @Override
		    public void actionPerformed(ActionEvent e)
		    {
		        picChooser.setFileFilter(picFilter);
		        int returnVal = picChooser.showOpenDialog(null);
		        if(returnVal == JFileChooser.APPROVE_OPTION) {
		           decUpLabel.setText("file: " + picChooser.getSelectedFile().getName());
		           decFile = picChooser.getSelectedFile().getPath();
		        }
		    }
		});
		decStartButton = new JButton("Start");
		decStartButton.setBackground(startButtonBackgroundColor);
		decStartButton.setFocusPainted(false);
		decStartButton.setOpaque(true);
		decStartButton.setBorderPainted(false);
		decStartButton.setFont(smallFont);
		decStartButton.addActionListener( new ActionListener()
		{
		    @Override
		    public void actionPerformed(ActionEvent e)
		    {
			    Decryptor dec = new Decryptor();
			    try {
					BufferedImage img = ImageIO.read(new File(decFile));
					String decryptedString = dec.arrayToString(dec.pictureToArray(img));
					LocalDateTime now = LocalDateTime.now();
					BufferedWriter out = new BufferedWriter(new FileWriter(currentDirectory + "Decryption " + dtf2.format(now) + ".txt"));
					out.write(decryptedString);
					out.close();
					if (Desktop.isDesktopSupported()) {
					    Desktop.getDesktop().edit(new File(currentDirectory + "Decryption " + dtf2.format(now) + ".txt"));
					    JOptionPane.showMessageDialog(null, "Text Succesfully Printed to: " + currentDirectory, "", JOptionPane.INFORMATION_MESSAGE);
					} else {
					    //System.out.println("rip");
					}
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, "Please upload a valid JPCode image", "", JOptionPane.INFORMATION_MESSAGE);
				}
		    }
		});
		decUpLabel = new JLabel("Upload picture to Decrypt", SwingConstants.CENTER);
		decUpLabel.setFont(smallFont);
		decPanel.add(decLabel, BorderLayout.PAGE_START);
		decPanel.add(decButton, BorderLayout.CENTER);
		decPanel.add(decUpLabel, BorderLayout.PAGE_END);
		decPanel.add(decStartButton, BorderLayout.EAST);
		con.add(decPanel);
		
		datePanel = new JPanel();
		datePanel.setBounds(-25, 545, 400, 20);
		datePanel.setBackground(backgroundColor);
		LocalDateTime now = LocalDateTime.now();
		dateLabel = new JLabel("Default picture generated on " + dtf.format(now));
		dateLabel.setFont(extraSmallFont);
		dateLabel.setForeground(panelBackgroundColor);
		datePanel.add(dateLabel);
		con.add(datePanel);
		
		pathPanel = new JPanel(new FlowLayout());
		pathPanel.setBounds(455, 500, 380, 50);
		pathPanel.setBackground(panelBackgroundColor);
		pathPath = new JLabel(currentDirectory, SwingConstants.LEFT);
		pathPath.setFont(extraSmallFont);
		pathPath.setPreferredSize(new Dimension(300,40));
		pathPath.setBackground(pathBackgroundColor);
		pathPath.setOpaque(true);
		pathPanel.add(pathPath);
		ImageIcon openFolder = new ImageIcon("./Res/openFolder.png");
		pathOpener = new JButton(openFolder);
		pathOpener.setPreferredSize(new Dimension(32,32));
		pathOpener.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		pathOpener.setBorderPainted(false);
		pathOpener.setFont(extraSmallFont);
		pathOpener.setBackground(panelBackgroundColor);
		pathOpener.addActionListener( new ActionListener()
		{
		    @Override
		    public void actionPerformed(ActionEvent e)
		    {
		    	try {
				Desktop.getDesktop().open(new File(currentDirectory));
				} catch (IOException e1) {
					// 
				e1.printStackTrace();
				}
		    }
		});
		ImageIcon changeFolder = new ImageIcon("./Res/updateFolder.png");
		pathChooser = new JButton(changeFolder);
		pathChooser.setPreferredSize(new Dimension(32,32));
		pathChooser.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		pathChooser.setBorderPainted(false);
		pathChooser.setBackground(panelBackgroundColor);
		pathChooser.setFont(extraSmallFont);
		pathChooser.addActionListener( new ActionListener()
		{
		    @Override
		    public void actionPerformed(ActionEvent e)
		    {
		    		
		    		directoryChooser = new JFileChooser();
		    		directoryChooser.setCurrentDirectory(new java.io.File("."));
		    		directoryChooser.setDialogTitle("Choose Directory");
		    		directoryChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		    		directoryChooser.setAcceptAllFileFilterUsed(false);

		        if (directoryChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
		          currentDirectory = directoryChooser.getSelectedFile().getPath();
		          pathPath.setText(currentDirectory);
		        } else {
		          System.out.println("No Selection ");
		        }
		    }
		});
		pathPanel.add(pathChooser);
		pathPanel.add(pathOpener);
		con.add(pathPanel);
		
		creditPanel = new JPanel();
		creditPanel.setBounds(700, 590, 175, 40);
		creditPanel.setBackground(backgroundColor);
		creditLabel = new JLabel("made by Joris Postmus");
		creditLabel.setFont(extraSmallFont);
		creditLabel.setForeground(panelBackgroundColor);
		creditPanel.add(creditLabel);
		con.add(creditPanel);
		
		moreInfoPanel = new JPanel();
		moreInfoPanel.setBounds(10, 750, 24, 24);
		moreInfoPanel.setBackground(Color.WHITE);
		ImageIcon moreInfo = new ImageIcon("./Res/moreInfo.png");
		moreInfoButton = new JButton(moreInfo);
		moreInfoButton.setPreferredSize(new Dimension(24,24));
		moreInfoButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		moreInfoButton.setBorderPainted(false);
		moreInfoButton.setFont(extraSmallFont);
		moreInfoButton.setBackground(panelBackgroundColor);
		moreInfoButton.addActionListener( new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) {

			}
			 
		});
		moreInfoPanel.add(moreInfoButton);
		con.add(moreInfoPanel);
		
		window.setVisible(true);
		changeColorBackground();
	}
	
	public String getDirectory() {
		return currentDirectory;
	}
	
	public void changeColorBackground() {
		boolean loop = true;
		int r = 130;
		int g = 0;
		int b = 0;
		backgroundColor = new Color(r, g, b);
		
		while (loop) {
		for(int phases = 1; phases < 7; phases++) {
			for(int step = 0; step < 130; step++) {
				if 		(phases == 1) {g++;} //Ends with (35, 35, 0)
				else if (phases == 2) {r--;} //Ends with (0, 35, 0)
				else if (phases == 3) {b++;} //Ends with (0, 35, 35)
				else if (phases == 4) {g--;} //Ends with (0, 0, 35)
				else if (phases == 5) {r++;} //Ends with (35, 0, 35)
				else if (phases == 6) {b--;} //Ends with (35, 0, 0)
				
				backgroundColor = new Color(r, g, b);
				titlePanel.setBackground(backgroundColor);
				creditPanel.setBackground(backgroundColor);
				datePanel.setBackground(backgroundColor);
				window.getContentPane().setBackground(backgroundColor);
				doc.getContentPane().setBackground(backgroundColor);
				
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
					}
				}
			}
		}
	}
}