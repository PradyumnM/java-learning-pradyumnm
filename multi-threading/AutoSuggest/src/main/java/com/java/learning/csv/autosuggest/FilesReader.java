package com.java.learning.csv.autosuggest;

import static java.nio.file.Files.readAllLines;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Path;
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

	static {

		try {
            List<String> strings = readAllLines(filePath, Charset.defaultCharset());
            trieData.insert(cityUpdateUpper);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
