package me.postmus.joris;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Window {
	JFrame window;
	JPanel titlePanel, imgPanel, encPanel, decPanel, datePanel;
	JLabel titleLabel, imgLabel, encLabel, decUpLabel, encUpLabel, decLabel, dateLabel;
	JButton encButton, decButton, encStartButton, decStartButton;
	Container con;
	Font titleFont = new Font("Monospaced", Font.BOLD, 85);
	Font labFont = new Font("Monospaced", Font.BOLD, 30);
	Font smallFont = new Font("Monospaced", Font.BOLD, 20);
	Font extraSmallFont = new Font("Monospaced", Font.BOLD, 10);
	//Color backgroundColor = new Color(199, 255, 245);
	Color backgroundColor = new Color(20, 20, 20);
	Color buttonBackgroundColor = new Color(80, 80, 80);
	Color panelBackgroundColor = new Color(150, 150, 150);
	Color startButtonBackgroundColor = new Color(105, 189, 48);
	private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
	JFileChooser textChooser = new JFileChooser();
	JFileChooser picChooser = new JFileChooser();
	FileNameExtensionFilter textFilter = new FileNameExtensionFilter(
            "Text Files", "txt");
	FileNameExtensionFilter picFilter = new FileNameExtensionFilter(
            "PNG Images", "png");
	Encryptor encryptor = new Encryptor();
	String encFile = "";
	String decFile = "";
	
	public void createWindow() {
		window = new JFrame("JorisCode");
		window.setSize(860, 640);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().setBackground(backgroundColor);
		window.setLayout(null);
		window.setLocationRelativeTo(null);
		window.setResizable(false);
		con = window.getContentPane();
		
		titlePanel = new JPanel();
		titlePanel.setBounds(0, 20, 860, 100);
		titlePanel.setBackground(backgroundColor);
		titleLabel = new JLabel("JPCode", SwingConstants.CENTER);
		titleLabel.setForeground(panelBackgroundColor);
		titleLabel.setFont(titleFont);
		titlePanel.add(titleLabel);
		con.add(titlePanel);
		
		imgPanel = new JPanel();
		imgPanel.setBounds(25, 140, 410, 410);
		imgPanel.setBackground(panelBackgroundColor);
		//imgPanel.setBorder(BorderFactory.createEmptyBorder(0,10,10,10));
		ImageIcon image = new ImageIcon(".//Res//Output.png");
		window.setIconImage(image.getImage());
		Image imageImg = image.getImage();
		Image resizedImage = imageImg.getScaledInstance(400, 400, java.awt.Image.SCALE_SMOOTH);
		image = new ImageIcon(resizedImage);
		imgLabel = new JLabel();
		imgLabel.setIcon(image);
		imgPanel.add(imgLabel);
		con.add(imgPanel);
		
		encPanel = new JPanel(new BorderLayout(10, 0));
		encPanel.setBounds(450, 160, 380, 175);
		encPanel.setBackground(panelBackgroundColor);
		encLabel = new JLabel("Encryption", SwingConstants.CENTER);
		encLabel.setFont(labFont);
		encButton = new JButton("Select File");
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
			    }
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
			    try {
			    		String textRead = encryptor.readFile(encFile);
					int[][] encryptedText = encryptor.encryptText(textRead);
					encryptor.generateImg(encryptedText);
				} catch (IOException e1) {
					System.out.println("Problem with text file");
				}
		    }
		});
		encUpLabel = new JLabel("Upload Text file to Encrypt", SwingConstants.CENTER);
		encUpLabel.setFont(smallFont);
		encPanel.add(encLabel, BorderLayout.PAGE_START);
		encPanel.add(encUpLabel, BorderLayout.PAGE_END);
		encPanel.add(encButton, BorderLayout.CENTER);
		encPanel.add(encStartButton, BorderLayout.EAST);
		con.add(encPanel);
		
		decPanel = new JPanel(new BorderLayout(10, 0));
		decPanel.setBounds(450,  355, 380, 175);
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
			    System.out.println("Starting Decryption");
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
		datePanel.setBounds(-25, 545, 400, 30);
		datePanel.setBackground(backgroundColor);
		LocalDateTime now = LocalDateTime.now();
		dateLabel = new JLabel("Default picture generated on " + dtf.format(now));
		dateLabel.setFont(extraSmallFont);
		dateLabel.setForeground(panelBackgroundColor);
		datePanel.add(dateLabel);
		con.add(datePanel);
		
		window.setVisible(true);
	}
	
	public void addPicture(BufferedImage img) {
		//System.out.println(img);
		//imgLabel.setIcon(new ImageIcon(img));
	}
}