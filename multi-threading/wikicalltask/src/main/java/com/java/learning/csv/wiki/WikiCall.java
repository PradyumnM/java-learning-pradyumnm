package com.java.learning.csv.wiki;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import cosntants.Constants;


/**
 * 
 * @author pradyumnm
 *
 */
public class WikiCall implements Runnable {

	private static final Logger log = Logger.getLogger(WikiCall.class.getName());

	private String word;

	public WikiCall(String word) {
		this.word = word;
	}
   
	public void run() {
		log.info("Running the thread for word :" + word);

		try {
			URL url = new URL(Constants.WIKI_URL + word);
			log.info("Successfully call to : " + url.getPath());
			URLConnection urc = url.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(urc.getInputStream()));
			JSONObject json = new JSONObject(in.readLine());
		    writeFile(Constants.WIKI_OP+Constants.EXT, json.getString("extract"));
		}  catch (IOException e) {
			log.log(Level.SEVERE,"Error while reading the response from the stream :" + e.getMessage());
		} catch (JSONException e) {
			log.log(Level.SEVERE,"Error , reading the JSON object : " + e.getMessage());
		} catch (Exception e) {
			log.log( Level.SEVERE, e.getMessage());
		}
		

	}

	private void writeFile(String keyword, String content) {
		BufferedWriter writer = null;

		try {
			writer = Files.newBufferedWriter(Paths.get(Constants.WIKI_OP+keyword + ".txt"));

			writer.write(content.toString());
			writer.flush();
		} catch (IOException e) {
			log.log( Level.SEVERE, e.getMessage());
		}

	}

}