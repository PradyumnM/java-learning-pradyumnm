package com.java.learning.csv.wordcount;

import java.nio.file.Path;
import java.util.Collections;
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

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Path filePath;
	private List<String> data;
	private int SEQUENTIAL_THRESHOLD;
	private static final Logger logger = Logger.getLogger(WordCountExec.class.getName());
	private Map<String, Long> wordCount = new ConcurrentHashMap<>();

	WordCountExec(Path filePath) {
		this.filePath = filePath;
	}

	public WordCountExec(List<String> data, Map<String, Long> wordCount, int lineLimit) {
		this.data = data;
		this.SEQUENTIAL_THRESHOLD = lineLimit;
		this.wordCount = wordCount;
	}

	@Override
	protected Map<String, Long> compute() {
		try {

			if (data.size() <= SEQUENTIAL_THRESHOLD) {
				computeDirectly();
			} else {

				int mid = data.size()/SEQUENTIAL_THRESHOLD;
				WordCountExec firstSubtask = new WordCountExec(data.subList(0, mid), wordCount, SEQUENTIAL_THRESHOLD);
				WordCountExec secondSubtask = new WordCountExec(data.subList(mid, data.size()), wordCount,
						SEQUENTIAL_THRESHOLD);

				invokeAll(firstSubtask, secondSubtask);
			}

			return wordCount;
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Exception occoured", e);
			return Collections.emptyMap();
		}
	}

	private void computeDirectly() {
		try {
			for (String line : data) {
				StringTokenizer tk = new StringTokenizer(line);
				while (tk.hasMoreTokens()) {
					String token = tk.nextToken();
					Long count = wordCount.get(token);
					count = count == null ? 1 : count;
					wordCount.put(token, count + 1);
				}
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE, "  exception");

		}

	}

}