package edu.upenn.cit594.processor;
import edu.upenn.cit594.datamanagement.JsonFileReader;
import edu.upenn.cit594.datamanagement.TweetReader;


public class JsonProcessor extends FileDataProcessor {
	
	@Override
	public TweetReader createFileReader() {
		return new JsonFileReader();
	}
}
