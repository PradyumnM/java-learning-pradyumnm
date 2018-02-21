package com.java.learning.csv.wordcount;

import static java.nio.file.Files.readAllLines;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;
import java.util.logging.Level;
import java.util.logging.Logger;

import cosntants.Constants;

public class App {
	private static Logger logger = Logger.getLogger(App.class.getName());

	public static void main(String[] args) {
	    Map<String, Long> wordCount = new ConcurrentHashMap<>();
	    File folder = new File(Constants.FOLDER);
	    File[] listOfFiles = folder.listFiles();
		Path filePath; 
		List<String> strings = new ArrayList<String>();
		try {
			for (File file : listOfFiles) {
			filePath= Paths.get(file.getAbsolutePath());
			strings.addAll(readAllLines(filePath, Charset.defaultCharset()));
			}
		} catch (FileNotFoundException e) {
			logger.log(Level.SEVERE, "File to be read not found");
		} catch (IOException e) {
			logger.log(Level.SEVERE, "File Input/Output exception");
		}
		ForkJoinPool pool = new ForkJoinPool();
		WordCountExec task = new WordCountExec(strings, wordCount, Constants.FORK_MID);
		pool.invoke(task);
	}
}
