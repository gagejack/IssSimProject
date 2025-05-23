package mainPackage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UserInfo {

	private final String fileName = "creds.txt";
	private String encryptedPassword;
	
	public void saveUser(String username, String password) {
		try {
			encryptedPassword = encryptString(password);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))){
			writer.write(username + "," + encryptedPassword);
			writer.newLine();
			writer.close();
			System.out.println("User Saved: " + username);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("error saving user" + e.getMessage());
		}
		
	}
	
	public boolean verifyCreds(String username, String password) {
		try(BufferedReader reader = new BufferedReader(new FileReader(fileName))){
			try {
				encryptedPassword = encryptString(password);
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			String line;
			while((line = reader.readLine()) != null) {
				String[] credentials = line.split(",");
				if(credentials.length == 2) {
					String storedUser = credentials[0];
					String storedPass = credentials[1];
					
					if(storedUser.equals(username) && storedPass.equals(encryptedPassword)) {
						return true;
					}
				}
			}
		} catch (IOException e) {
			System.out.println("Error reading user file");
		}
		
		return false;
	}
	
	public boolean usernameValidate(String username) {
		boolean isOriginal = true;
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(fileName));
			String line;
			try {
				while((line = reader.readLine()) != null) {
					String[] credentials = line.split(",");
					if(credentials.length == 2) {
						String storedUser = credentials[0];
						
						if(storedUser.equals(username)) {
							isOriginal = false;
						}
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return isOriginal;
	}
	
	public boolean passwordValidate(String password) {
		boolean capitalFlag = false;
		boolean lengthFlag = false;
		boolean digitFlag = false;
		boolean validFlag = false;
		char ch;
		
		if(password.length() >= 9) { 
			lengthFlag = true;
		}
		
		for(int i=0;i<password.length();i++) {
			ch = password.charAt(i);
			if(Character.isUpperCase(ch));
			capitalFlag = true;
			if(Character.isDigit(ch));
			digitFlag = true;
		}
		
		if(capitalFlag && lengthFlag && digitFlag) {
			validFlag = true;
		}
		
		return validFlag;
	}

		public String encryptString(String input) throws NoSuchAlgorithmException {
			
			MessageDigest md = MessageDigest.getInstance("MD5");
			
			byte[] messageDigest = md.digest(input.getBytes());
			
			BigInteger bigInt = new BigInteger(1,messageDigest);
			
			return bigInt.toString(16);
			
		}
}
