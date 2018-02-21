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
		// access order set true to implement LRU
		super(capacity + 1, 1.0f, true);
		this.capacity = capacity;
	}

	public V find(K key) {
		return super.get(key);
	}


	public void set(K key, V value) {
		super.put(key, value);
	}

	
}