package edu.upenn.cit594.datamanagement;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import edu.upenn.cit594.data.Tweet;
import edu.upenn.cit594.*;

public class TxtFileReader implements TweetReader {
	
	@Override
	public List<Tweet> getAllTweets(){
		
		String t = Main.tweetsFileName;
		File tweetsFile = new File(t);
		List<Tweet> tweetsList = new ArrayList<Tweet>();
		BufferedReader file = null;
		String[] arrOfStr = null;
		
		try{
            file = new BufferedReader(new FileReader(tweetsFile));
            String line ="";
            
          //read each line
            while ((line = file.readLine())!= null){
            	
            	//check if it's the flu tweet
            	boolean isFluTweet = line.matches("^(?i)\bflu\b|[#](?i)flu\b|\\s+(?i)flu\b");
            	
            	//find the word "flu" in the string
                String patternString1 = ".*\\b(?i)flu\\b.*| .*\\b(?i)flu[^\\w].*";
                boolean isMatch = Pattern.matches(patternString1, line);
                // find the hashtag #flu in the string
                String patternString2 = ".*[#]\\b(?i)flu\\b[^\\w]|.*[#]\\b(?i)flu\\b[^\\w].*";
                boolean isMatch2 = Pattern.matches(patternString2, line);
            	
                // if the word "flu" or the hashtag "#flu" is in the string 
            	if (isMatch | isMatch2) {
            	
            	
	            	int comma_index = line.indexOf(',');
					int bracket_index = line.indexOf(']');
					double la = Double.parseDouble(line.substring(1, comma_index)); 
					double lo = Double.parseDouble(line.substring(comma_index + 1, bracket_index));
					int date_index = line.indexOf('-', bracket_index) - 4;
					int tweet_index = line.indexOf('\t', date_index);
					String tweet = line.substring(tweet_index + 1, line.length());
					
					Tweet tw = new Tweet(la, lo, tweet);
					tweetsList.add(tw);
            	}
            }
		}
		catch (FileNotFoundException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        finally{
            try{
                file.close();
            }
            catch (IOException e){
                e.printStackTrace();
            }
            
        }
		
		return tweetsList;
		
		
	}
}
