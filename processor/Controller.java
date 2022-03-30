package edu.upenn.cit594.processor;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import edu.upenn.cit594.logging.Logger;
import edu.upenn.cit594.ui.UserInterface;

public class Controller {

	private FileDataProcessor dataProcessor;
	private UserInterface interfaceUser;
	private ArrayList<Logger> loggers = new ArrayList<Logger>();
	
	public void attach(Logger l) {
		loggers.add(l);
	}

	public void log(String str) {
		for (Logger lg : loggers) 
			lg.log(str);
	}
	
	public Controller(FileDataProcessor dp, UserInterface ui) {
		dataProcessor = dp;
		interfaceUser = ui;
	}
	
	// calls on the View to print to the console
	public static void displayToScreen(String str) {
		UserInterface.display(str);
	}
}
	