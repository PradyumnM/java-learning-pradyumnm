package com.java.learning.csv.wiki.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CSVParser implements Parser {
	public  List<String> getData(String filename)  {
		List<String> csvList = new ArrayList<String>();
		try {	
			BufferedReader br = new BufferedReader(new FileReader(filename));
			Path path = Paths.get(filename);
			String line1="";
			csvList = Files.readAllLines(path);

		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return csvList;
	}

}
