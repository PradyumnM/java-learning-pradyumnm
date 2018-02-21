package com.java.learning.collectiontask;

public class App {
	/**
	 * Testing for Cache
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
