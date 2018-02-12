package com.java.learning.csv.wiki;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.java.learning.csv.wiki.parser.*;

/**
 * 
 * @author pradyumnm
 *
 */
public class WikiMain {

	private static final Logger log = Logger.getLogger(WikiMain.class.getName());

	public static void main() {
		Parser parser = new FileParser2();

		try {
			List<String> wordsList = parser.parseFile(inputTextFilePath);
			ExecutorService executor = Executors.newFixedThreadPool(10);
			for (String word : wordsList) {
				System.out.println("word is " + word);
				WikiCall wikicallThread = new WikiCall(word);
				executor.execute(wikicallThread);
			}
		} catch (IOException e) {
			log.log(Level.SEVERE, "File to be read not found");
		}
	}

}