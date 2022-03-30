package edu.upenn.cit594.processor;

import edu.upenn.cit594.datamanagement.TweetReader;
import edu.upenn.cit594.datamanagement.TxtFileReader;

public class TxtFileProcessor extends FileDataProcessor {
	
	@Override
	public TweetReader createFileReader()  {
		return new TxtFileReader();
	}
}
