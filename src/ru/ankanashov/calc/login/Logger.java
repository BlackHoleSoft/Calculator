package ru.ankanashov.calc.login;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {
	
	private final int MAX_ENTRIES_COUNT = 10;
	
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
        
        new Thread(new LogAudition(filename)).start();		
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	private int getLinesCount(String fname){
		int linesCount = 0;
		FileReader fr;
		try {
			fr = new FileReader(fname);
			BufferedReader br = new BufferedReader(fr);		
		
			while(br.readLine() != null){
				linesCount++;
			}
			br.close();
			
		}catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		return linesCount;		
	}
	
	private class LogAudition implements Runnable {
		
		String filename;
		
		public LogAudition(String filename){
			this.filename = filename;
		}
		
		@Override
		public void run() {
			int count = getLinesCount(filename);
			if(count >= MAX_ENTRIES_COUNT){
				System.out.println("copy log file");
				Date d = new Date();
		        SimpleDateFormat format = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		        String date = format.format(d);
		        
				File current = new File(filename);
				try {
					Files.copy(current.toPath(), new File("log_"+date+".txt").toPath());
					current.delete();
				} catch (IOException e) {
					e.printStackTrace();
				}				
			}			
		}		
		
	}
	
	
}
