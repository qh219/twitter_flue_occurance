package edu.upenn.cit594.datamanagement;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.upenn.cit594.data.State;
import edu.upenn.cit594.ui.UserInterface;
import edu.upenn.cit594.*;

public class StateCSVReader {
	
	public static List<State> readStates(){
		String s = Main.statesFileName;
		File statesFile = new File(s);
		ArrayList<State> states = new ArrayList<State>();
		String line = "";
		
		if (!statesFile.exists() || !statesFile.canRead()) {
			UserInterface.display("States file does not exist or cannot be read");
			System.exit(0);
		}
		
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(statesFile));
			while ((line = br.readLine()) != null) {
				
				String[] stateinfo = line.split(",", 3); //split the line into tokens
				double la = Double.parseDouble(stateinfo[1].trim());
				double lo = Double.parseDouble(stateinfo[2].trim());
				State st = new State(stateinfo[0], la, lo);
				int commaIndex = line.indexOf(',');
				String name = line.substring(0, commaIndex); 
				
				//int second_comma_index = line.indexOf(',', commaIndex + 1);
				//double la = Double.parseDouble(line.substring(commaIndex + 1, second_comma_index)); 
				//double lo = Double.parseDouble(line.substring(second_comma_index + 1, line.length()));
				
				State state = new State(name, la, lo);
				states.add(state);
			}
			br.close();
		}
		catch (IOException ioe) {
			UserInterface.display("IO Error reading states file.");
	     	System.exit(1); 
		}
		
		return states;
	}
	
}

