package com.java.learning.csv.wiki;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.java.learning.csv.wiki.parser.*;

import cosntants.Constants;

/**
 * 
 * @author pradyumnm
 *
 */
public class WikiMain {

	private static final Logger log = Logger.getLogger(WikiMain.class.getName());

	public static void main(String args[]) {
		Parser parser = new CSVParser();
		List<String> wordsList = new ArrayList<String>();
		try {
			wordsList = parser.getData(Constants.FILEPATH);
			ExecutorService executor = Executors.newFixedThreadPool(10);
			for (String word : wordsList) {
				System.out.println("word is " + word);
				WikiCall wikicallThread = new WikiCall(word);
				executor.execute(wikicallThread);
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, "Exception encountered");
			e.printStackTrace();
		}
	}

}