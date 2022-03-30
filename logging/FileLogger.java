package edu.upenn.cit594.logging;

import java.io.File;
import java.io.FileWriter;
import edu.upenn.cit594.*;

public class FileLogger extends Logger {

	private static FileLogger instance = new FileLogger();
	private FileWriter writer = null;
	
	private FileLogger() {
		try {
			File logFile = new File(Main.outputFileName);
			if (logFile.exists()) {
				if (!logFile.canWrite()) {
					System.out.println("Access not given to write to log file.");
			     	System.exit(1); 
				}
				writer = new FileWriter(logFile, true);
			}
			else {
				writer = new FileWriter(logFile);	
			}			
		}
		catch (Exception e) 
			{ e.printStackTrace(); }
		
	}
	
	public static FileLogger getInstance() { 
		return instance;
	}
	
	public void log(String str) {
		try {
			writer.write(str +"\n");		
			writer.flush();
		}
		catch (Exception e) { 
			System.out.println("Error writing to file.");
			System.exit(1);
		}
	}
	
	public void close() {
		try { 
			writer.close(); 
			} 
		catch (Exception e) { }
		}
	}
	

