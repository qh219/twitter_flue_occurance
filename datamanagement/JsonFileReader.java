package edu.upenn.cit594.datamanagement;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import edu.upenn.cit594.*;

import edu.upenn.cit594.data.*;

public class JsonFileReader implements TweetReader{
	
	@Override
	public List<Tweet> getAllTweets() {

		String t = Main.tweetsFileName;
		File tweets_file = new File(t);

		if (!tweets_file.exists() || !tweets_file.canRead()) {
			System.out.println("Tweets JSON file does not exist or cannot be read");
			System.exit(0);
		}
		
		ArrayList<Tweet> tweets = new ArrayList<Tweet>();
		String  line = null;

		try {
			
			JSONParser parser = new JSONParser();
            JSONArray tweetFiles;
			try {
				tweetFiles = (JSONArray)parser.parse(new FileReader(t));
				Iterator iter = tweetFiles.iterator();

	            while (iter.hasNext()){

	                JSONObject obj = (JSONObject) iter.next();
	                String tweet = (String)obj.get("text"); 
	                
	                //check if it's the flu tweet
	            	boolean isFluTweet = tweet.matches("^(?i)\bflu\b|[#](?i)flu\b|\\s+(?i)flu\b");
	            	
	            	//find the word "flu" in the string
	                String patternString1 = ".*\\b(?i)flu\\b.*| .*\\b(?i)flu[^\\w].*";
	                boolean isMatch = Pattern.matches(patternString1, tweet);
	                // find the hashtag #flu in the string
	                String patternString2 = ".*[#]\\b(?i)flu\\b[^\\w]|.*[#]\\b(?i)flu\\b[^\\w].*";
	                boolean isMatch2 = Pattern.matches(patternString2, tweet);
	            	
	                // if the word "flu" or the hashtag "#flu" is in the string 
	            	if (isMatch | isMatch2) {
	            		JSONArray location = (JSONArray)obj.get("location");
						Object[] locationArray = location.toArray();
						Double lat = (Double)locationArray[0];
						Double lon = (Double)locationArray[1];	
						Tweet tw = new Tweet(lat, lon, tweet);
						tweets.add(tw);
	            	}
	                
	         
	            }
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
           
		}
		catch (IOException ioe) {
			System.out.println("IO Error reading tweets JSON file.");
			System.exit(1); 
		}
		return tweets;
	}
}
