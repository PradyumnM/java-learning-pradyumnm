package com.java.learning.csv.wiki.parser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileParser2 implements Parser {
	public  List<String> getData(String filename)  {
		
		List<String> csvList = new ArrayList<String>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			Path path = Paths.get(filename);
			String line1="";
			String csvSplitBy = " +";
			String [] contents;
			 while ((line1 = br.readLine()) != null)
				{
				 contents= line1.split(csvSplitBy);
				 csvList.add(contents[1]);
				}
			br.close(); 
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
