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

/**
 *
 * This class is used for testing the recursive file writer.
 */
public class App {
	private static Logger logger = Logger.getLogger(App.class.getName());

	public static void main(String[] args) {
		List<String> datalist = new ArrayList<String>();
		
		try {
			datalist = Files.readAllLines(Paths.get("Multithreading_Task1_Books.csv"));
		} catch (FileNotFoundException e) {
			logger.log(Level.SEVERE, "File to be read not found");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.log(Level.SEVERE, "File Input/Output exception");
		}
		ForkJoinPool pool = new ForkJoinPool();
		RecursiveFileWriter task = new RecursiveFileWriter(datalist, 0, 1000);
		pool.invoke(task);
	}
}
