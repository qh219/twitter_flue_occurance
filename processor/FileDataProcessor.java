package edu.upenn.cit594.processor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.upenn.cit594.data.State;
import edu.upenn.cit594.data.Tweet;
import edu.upenn.cit594.datamanagement.*;
import edu.upenn.cit594.logging.Logger;



public abstract class FileDataProcessor {
	
	private List<Tweet> tweets;
	private List<State> states;	
	private ArrayList<Logger> loggers = new ArrayList<Logger>();
	public abstract TweetReader createFileReader();
	public StateCSVReader csvReader;
	
	public void attach(Logger l) {
		loggers.add(l);
	}

	public void log(String str) {
		for (Logger lg : loggers) 
			lg.log(str);
	}
	
	public List<Tweet> readFileData() {
		TweetReader readFile = createFileReader();
		tweets = readFile.getAllTweets();
		return tweets;
	}
	
	public List<State> readStateCSV() {
		List<State> statesList = StateCSVReader.readStates();
		return statesList;
	}
	
	
	// use latitude and location to determine the tweet's State
	public List<String> FindTweetState(List<Tweet> tweets, List<State> states) {
		
		List<String> tweetsWithState = new ArrayList<String>();	
		
		// iterate over tweets list and determine per tweet's state
		for (int i = 0; i < tweets.size(); i++) {
			Tweet tweet = tweets.get(i);
			double dist = Math.sqrt(Math.pow(tweet.getLatitude() - states.get(0).getLatitude(), 2) +
									Math.pow(tweet.getLongitude() - states.get(0).getLongitude(), 2));
			State st = states.get(0); 
			
			for (int j = 0; j < states.size(); j++) {
				double tempDistance = Math.sqrt(Math.pow(tweet.getLatitude() - states.get(j).getLatitude(), 2) +
								   			 Math.pow(tweet.getLongitude() - states.get(j).getLongitude(), 2));
				if (tempDistance <= dist) {
					dist = tempDistance;
					st = states.get(j);
				}
			}
			
			st.setNumTweets(st.getNumTweets() + 1); //update this state's flu tweets number
			String tweetStateLine = st.getName() + "\t" + tweet.getTweet();
			tweetsWithState.add(tweetStateLine);
		}
		
		return tweetsWithState;
	}
	
	
	public List<String> TweetPerStateSorted(List<State> states){
		
		List<String> StateSorted = new ArrayList<String>();	
		
		Collections.sort(states);
		
		for (int i = 0; i < states.size(); i ++) {
			State st = states.get(i);
			String stateName = st.getName();
			Integer statesTweetNum = st.getNumTweets();
			if (statesTweetNum != 0) {
				String stateTweetInfo = stateName + ": " + statesTweetNum;
				StateSorted.add(stateTweetInfo);
			}
		}
		
		return StateSorted;
	}

		
}
	
	

	

	