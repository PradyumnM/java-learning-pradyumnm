package com.java.learning.csv.autosuggest;

import static java.nio.file.Files.readAllLines;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author pradyumnm This class reads the file and put the relevant list of
 *         words in appropriate datastructure
 */
public class FilesReader {
	private static Path filePath;
	private static final Logger logger = Logger.getLogger(FilesReader.class.getName());

	FilesReader(Path filePath) {
		this.filePath = filePath;
	}

	public static Trie trieData = new Trie();
	public static Trie trieDataFuzzy = new Trie();
	public static HashMap<String,String> fuzzyReferece= new HashMap<String,String>();

	static {

		try {
			List<String> list = new ArrayList<String>();
			trieData.insertAll(readAllLines(filePath, Charset.defaultCharset()));
			trieDataFuzzy.insertAllFuzzy(list,fuzzyReferece);
			trieData.autoComplete("ja");
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
