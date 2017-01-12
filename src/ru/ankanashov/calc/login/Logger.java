package ru.ankanashov.calc.login;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {
	
	private String filename;
	private String username;
	
	public Logger(String filename, String username){
		this.filename = filename;
		this.username = username;
	}
	
	public void log(String text){
		Date d = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy hh:mm");
        String date = format.format(d);
        
        String result = date + "; " + username + "; " + text + "\n";
        
        try {
			FileWriter out = new FileWriter(filename, true);			
			out.write(result);			
			out.flush();
			out.close();			
		} catch (IOException e) {			
			e.printStackTrace();
		}
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	
}
