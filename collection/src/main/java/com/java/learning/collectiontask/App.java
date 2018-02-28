package com.java.learning.collectiontask;

import java.sql.Date;

public class App {
	/**
	 * Testing for Cache
	 */
	public static void main(String[] args) {

		LRUCache cache = new LRUCache();
		try {
		cache.put("pappu","12"); 
		Thread.sleep(4);
		cache.put("sunty","10"); 
		Thread.sleep(4);
		cache.put("bunty","7"); 
		Thread.sleep(4);
		cache.put("chunky","5"); 
		Thread.sleep(4);
		cache.put("punky","4"); 
		Thread.sleep(4);
		cache.put("munky","4"); 
		Thread.sleep(4);
		cache.put("sunty","3"); 
		Thread.sleep(4);
		cache.put("junky","2");  
		Thread.sleep(4);
		cache.put("Akash","1"); ; 
		cache.get("bunty");
		
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
		
		
	}
}
