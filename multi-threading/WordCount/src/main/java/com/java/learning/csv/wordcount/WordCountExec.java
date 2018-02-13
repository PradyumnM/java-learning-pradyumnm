package com.java.learning.csv.wordcount;

import static java.nio.file.Files.readAllLines;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.RecursiveTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author pradyumnm
 *
 */
public class WordCountExec extends RecursiveTask<Map<String, Long>> {

	private Path filePath;
    private static final Logger logger = Logger.getLogger(WordCountExec.class.getName());

    WordCountExec(Path filePath) {
        this.filePath = filePath;
    }


    @Override
    protected Map<String, Long> compute() {
        try {
            List<String> strings = readAllLines(filePath, Charset.defaultCharset());
            Map<String, Long> wordCount = new ConcurrentHashMap<>();
            for(String line : strings) {
                StringTokenizer tk = new StringTokenizer(line);
                while(tk.hasMoreTokens()) {
                    String token = tk.nextToken();
                    Long count = wordCount.get(token);
                    count = count == null? 0 : count;
                    wordCount.put(token, count+1);
                }
            }
            logger.info("File [" + filePath + "] has word count [" + wordCount.size() + "]");
            return wordCount;
        } catch (IOException e) {
            logger.log(Level.SEVERE, "IO Exception occured", e);
            return Collections.emptyMap();
        }
    }


}