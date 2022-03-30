package edu.upenn.cit594.datamanagement;


import java.util.List;
import edu.upenn.cit594.data.Tweet;

public interface TweetReader {
	
	public List<Tweet> getAllTweets();

}