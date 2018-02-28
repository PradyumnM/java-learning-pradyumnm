package com.java.learning.collectiontask;

/**
 * Basic implementation of LRU cache using LinkedHashMap
 */
import java.util.*;
import java.util.logging.Logger;
import org.apache.commons.lang.StringUtils;

public class LRUCache {
	private static final int SIZE = 5;
	private Map<String, String> map = new HashMap<String, String>();
	private static final Logger logger = Logger.getLogger(LRUCache.class.getName());

	private PriorityQueue<Node> pq = new PriorityQueue<Node>(SIZE, new Comparator() {
		@Override
		public int compare(Object arg0, Object arg1) {
			// Ordering the Nodes as per timestamp.
			if (!(arg0 instanceof Node) || !(arg1 instanceof Node))
				return 0;
			Node n1 = (Node) arg0;
			Node n2 = (Node) arg1;
			return n1.getTimestamp().compareTo(n2.getTimestamp());
		}
	});

	void insert(Node n) {
		pq.offer(n);
	}

	private String remove() {
		Node leastUsed = pq.poll();
		if (leastUsed != null) {
			logger.info("Removing " + leastUsed.getValue());
			return leastUsed.getValue();
		}
		return "";
	}

	void update(String mostRecentEleKey) {
		// update priority queue with most recent access.
		// Internal datastructure on PriorityQueue is Heap and it is partially sorted.
		// This means, any update on Nodes means to delete them and add them again.

		Iterator<Node> pqIterator = pq.iterator();
		while (pqIterator.hasNext()) {
			Node n = pqIterator.next();
			if (n.getValue().equals(mostRecentEleKey)) {
				pqIterator.remove();
				break;
			}
		}
		Node mostRecent = new Node(mostRecentEleKey, System.currentTimeMillis());
		insert(mostRecent);
	}

	public String get(String key) {
		String value = map.get(key);
		if (StringUtils.isNotEmpty(value)) {
			logger.info("Updating " + key + " with current timestamp.");
			update(key);
		} else
			logger.info("value not found");

		return value;
	}

	public void put(String key, String value) {
		logger.info("Before put opertaion, map size:" + map.size());
		if (map.containsKey(key)) {
			logger.info("Cache hit on key:" + key + ", nothing to insert!");
			update(key);
		} else {
			if (map.size() >= SIZE) {
				String leastUsedKey = remove();
				map.remove(leastUsedKey);
			}
			logger.info("Node not present in Cache: " + key);
			Node n = new Node(key, System.currentTimeMillis());
			insert(n);
			map.put(key, value);
		}
		logger.info("After put operation, following stats are generated:");
		logger.info("Least used Node:" + pq.peek().getValue() + ", last used at:" + pq.peek().getTimestamp());
		logger.info("map size:" + map.size());

	}
}