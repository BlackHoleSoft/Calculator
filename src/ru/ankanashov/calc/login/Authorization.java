package ru.ankanashov.calc.login;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class Authorization {
	
	private static final String filename = "users.dat";
	
	public static boolean logIn(String username, String password){
		
		ArrayList<String> lines = new ArrayList<String>();
		try {
			FileReader in = new FileReader(filename);			
			int c;
			StringBuilder sb = new StringBuilder();
            while((c=in.read())!=-1){
                if((char)c == '\n'){
                	lines.add(sb.toString());
                	sb = new StringBuilder();
                }else{
                	sb.append((char)c);
                }                
            }
            in.close();
			
		} catch (FileNotFoundException e) {			
			e.printStackTrace();
			return false;
		} catch (IOException e) {			
			e.printStackTrace();
		}
		
		if(lines.size() == 0){
			return false;
		}else{
			for(String s : lines){
				StringBuilder hash= new StringBuilder();
				for(byte b : getUserHash(username, password)){
					hash.append(b+"");
				}
				if(hash.toString().equals(s)){
					System.out.println("Logged in: "+username);
					return true;
				}
			}
		}
		
		return false;
	}
	
	public static void addUser(String username, String password){
		
		byte[] hash = getUserHash(username, password);
		
		if(hash == null) return;
		
		try {
			FileWriter out = new FileWriter(filename, true);
			for(int i=0; i<hash.length; i++){
				out.write(hash[i]+"");
			}
			out.write("\n");
			out.flush();
			out.close();
			System.out.println("User " + username + " added");
		} catch (IOException e) {			
			e.printStackTrace();
		}
	}
	
	public static byte[] getUserHash(String username, String password){
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("md5");
		} catch (NoSuchAlgorithmException e) {			
			e.printStackTrace();
		}
		
		if(md == null) return null;
		
		String str = username + password;
		
		md.reset();
		md.update(str.getBytes());
		return md.digest();
	}
	
}
