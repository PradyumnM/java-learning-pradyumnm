package com.java.learning.collectiontask;

import java.util.Date;

public class Node {

private String value;
private Long timestamp;

public String getValue() {
 return value;
}

public void setValue(String value) {
 this.value = value;
}

public Long getTimestamp() {
 return timestamp;
}

public void setTimestamp(Long timestamp) {
 this.timestamp = timestamp;
}

public boolean equals(Node e) {
 return value.equals(e.getValue());
}

public Node(String value, Long timestamp) {
	super();
	this.value = value;
	this.timestamp = timestamp;
}


}
