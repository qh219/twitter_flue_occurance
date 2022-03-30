package edu.upenn.cit594;

import java.util.List;
import java.util.TreeMap;

import edu.upenn.cit594.datamanagement.*;
import edu.upenn.cit594.logging.FileLogger;
import edu.upenn.cit594.logging.Logger;
import edu.upenn.cit594.processor.*;
import edu.upenn.cit594.ui.UserInterface;
import edu.upenn.cit594.data.*;

public class Main {

	public static String tweetsFileName;
	public static String statesFileName;
	public static String outputFileName;

	
	public static void main(String[] args) {
		
		// handle command-line arguments
		if (args == null || args.length < 4) {
			System.out.println("Error. Required four arguments.");
			System.exit(0);
		}
		
		String fileType = args[0];
		tweetsFileName = args[1];
		statesFileName = args[2];
		outputFileName = args[3];
	
		if (!(fileType.equalsIgnoreCase("JSON") || fileType.equalsIgnoreCase("TEXT"))) {
			System.out.println("Error. Correct file type format: JSON or TEXT.");
			System.exit(0);
		}
		
		FileDataProcessor dataProcessor = null; //create file processor according to different file type
		UserInterface interfaceUser = new UserInterface(); //create new user interface
		Controller controller = new Controller(dataProcessor,interfaceUser); //
		
		if (fileType.equalsIgnoreCase("json")) 
			dataProcessor = new JsonProcessor();
		else if (fileType.equalsIgnoreCase("text")) 
			dataProcessor = new TxtFileProcessor();
		
		// read files
		List<Tweet> tweetsWithLaLo = dataProcessor.readFileData();//read tweets file data
		List<State> StatesList = dataProcessor.readStateCSV(); // read states csv file data
		
		// find tweet's location state and store it in a list
		List<String> tweetsWithState = dataProcessor.FindTweetState(tweetsWithLaLo, StatesList);
		// iterate over tweetsWithState list and log each line into the logger file
		for (String tweetStr: tweetsWithState) {
			Logger fl = FileLogger.getInstance();
			dataProcessor.attach(fl);
			controller.attach(fl);
			fl.log(tweetStr);
		}
		
		List<String> StateWithTweetsSorted = dataProcessor.TweetPerStateSorted(StatesList);
		for (String str: StateWithTweetsSorted) {
			UserInterface.display(str);
		}
	}

}
