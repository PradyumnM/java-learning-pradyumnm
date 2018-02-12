package com.java.learning.csv.wiki;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONException;
import org.json.JSONObject;


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
			URL url = new URL("https://en.wikipedia.org/api/rest_v1/page/summary/" + word);
			log.info("Successfully call to : " + url.getPath());
			URLConnection urc = url.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(urc.getInputStream()));
			JSONObject json = new JSONObject(in.readLine());
		//	IOUtils.writeToFile(Constants.WIKI_OUTPUT_FOLDER + word + ".txt", json.getString("extract"));
		}  catch (IOException e) {
			log.log(Level.SEVERE,"Error while reading the response from the stream :" + e.getMessage());
		} catch (JSONException e) {
			log.log(Level.SEVERE,"Error , reading the JSON object : " + e.getMessage());
		} catch (Exception e) {
			log.log( Level.SEVERE, e.getMessage());
		}
		

	}

}