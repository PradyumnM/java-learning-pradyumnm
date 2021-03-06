package com.javalearning.csv.task;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.logging.Level;
import java.util.logging.Logger;

import cosntants.Constants;

/**
 *
 * This class is used for testing the recursive file writer.
 */
public class App {
	private static Logger logger = Logger.getLogger(App.class.getName());

	public static void main(String[] args) {
		List<String> datalist = new ArrayList<String>();

		try {
			datalist = Files.readAllLines(Paths.get(Constants.CSV_SPLIT_FILEPATH));
		} catch (FileNotFoundException e) {
			logger.log(Level.SEVERE, "File to be read not found");
		} catch (IOException e) {
			logger.log(Level.SEVERE, "File Input/Output exception");
		}
		ForkJoinPool pool = new ForkJoinPool();
		RecursiveFileWriter task = new RecursiveFileWriter(datalist, 0, Constants.SEQ_THRESHOLD);
		pool.invoke(task);
	}
}
