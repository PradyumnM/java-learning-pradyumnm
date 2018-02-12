package com.java.learning.collectiontask;

/**
 * Basic implementation of LRU cache using LinkedHashMap
 */
import java.util.LinkedHashMap;

public class LRUCache<K, V> extends LinkedHashMap<K, V> {

	private static final long serialVersionUID = 1L;

	private final int capacity;

	@Override
	protected boolean removeEldestEntry(java.util.Map.Entry<K, V> eldest) {
		return (size() > this.capacity);
	}

	public LRUCache(int capacity) {
		// access order set true to implement LRU behaviour
		super(capacity + 1, 1.0f, true);
		this.capacity = capacity;
	}

	public V find(K key) {
		return super.get(key);
	}


	public void set(K key, V value) {
		super.put(key, value);
	}

	/**
	 * Testing for Cache.
	 */
	public static void main(String[] args) {

		LRUCache<Integer, Integer> cache = new LRUCache<>(3);

		cache.set(2, 1); 
		cache.set(3, 2); 
		cache.set(4, 3);
		cache.set(5, 0);
		cache.set(7, 3);

		// it should show null for 2,3 as cache size is 3
		System.out.println(cache.find(2));

		System.out.println(cache.find(3));

		System.out.println(cache.find(4));
		
		System.out.println(cache.find(7));
		
	}
}