package edu.upenn.cit594.data;



public class Tweet {
	
	private double latitude;
	private double longitude;
	private String tweet;
	
	public Tweet(double la, double lo, String tw) {
		latitude = la;
		longitude = lo;	
		tweet = tw;
	}
	
	public double getLatitude()  { return latitude;  }
	public double getLongitude() { return longitude; }
	public String getTweet()     { return tweet; }
	
	
}