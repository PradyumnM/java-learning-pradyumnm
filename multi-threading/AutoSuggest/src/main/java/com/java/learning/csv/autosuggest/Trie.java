package com.java.learning.csv.autosuggest;

import java.util.*;

public class Trie {

  protected final Map<Character, Trie> children;
  protected String content;
  protected boolean terminal = false;

  public Trie() {
    this(null);
  }

  private Trie(String content) {
    this.content = content;
    children = new HashMap<Character, Trie>();
  }

  //method to append character
  protected void add(char character) {
    String s;
    if (this.content == null) {
      s = Character.toString(character);
    } else {
      s = this.content + character;
    }
    children.put(character, new Trie(s));
  }


  //method for inserting a new diagnosis
  public void insert(String diagnosis) {
    if (diagnosis == null) {
      throw new IllegalArgumentException("Null diagnoses entries are not valid.");
    }
    Trie node = this;
    for (char c : diagnosis.toCharArray()) {
      if (!node.children.containsKey(c)) {
        node.add(c);
      }
      node = node.children.get(c);
    }
    node.terminal = true;
  }

  //method to search for a diagnosis entry
  public String find(String diagnosis) {
    Trie node = this;
    for (char c : diagnosis.toCharArray()) {
      if (!node.children.containsKey(c)) {
        return "";
      }
      node = node.children.get(c);
    }
    return node.content;
  }

  //check for fragment of an entry prefix
  //todo: fragment within the diagnosis string isn't detected.
  public Collection<String> autoComplete(String prefix) {
    Trie Trienode = this;
    for (char c : prefix.toCharArray()) {
      if (!Trienode.children.containsKey(c)) {
        return Collections.emptyList();
      }
      Trienode = Trienode.children.get(c);
    }
    return Trienode.allPrefixes();
  }

  protected Collection<String> allPrefixes() {
    List<String> diagnosisresults = new ArrayList<String>();
    if (this.terminal) {
      diagnosisresults.add(this.content);
    }
    for (Map.Entry<Character, Trie> entry : children.entrySet()) {
      Trie child = entry.getValue();
      Collection<String> childPrefixes = child.allPrefixes();
      diagnosisresults.addAll(childPrefixes);
    }
    return diagnosisresults;
  }

public List<String> insertAll(List<String> strings) {
	for ( String s : strings )	
		insert(s);
	return strings;
}

public void insertAllFuzzy(List<String> list, HashMap<String, String> fuzzyReferece) {
	for ( String s : list )	
		{
		String temp="";
		insert(s.replaceAll("[aeiouAEIOU]", ""));
		fuzzyReferece.put(s.replaceAll("[aeiouAEIOU]", ""), s);
		temp = fuzzyReferece.get(s.replaceAll("[aeiouAEIOU]", ""));
		temp = temp == null ? s : temp+","+s;
		fuzzyReferece.put(s.replaceAll("[aeiouAEIOU]", ""), temp);
		}
}

}
