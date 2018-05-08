package me.postmus.joris;

import java.io.IOException;
import java.util.Scanner;

public class Main {
	//Test
	public static void main(String[] args) throws IOException {
			Window window = new Window();
			window.createWindow();	
		
			Scanner sc = new Scanner(System.in);
			String text = sc.nextLine();
			
			Encryptor encryptor = new Encryptor();
			
			Decryptor decryptor = new Decryptor();
			sc.close();
	}
}
