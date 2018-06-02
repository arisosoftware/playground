package com.ariso.vertxwiki;

import java.util.concurrent.atomic.AtomicInteger;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

// goto command line, call mvn compile to get the code changes. remember update Package-info.java if requires.

@DataObject(generateConverter = true)
public class WikiPageEntity {

	private static final AtomicInteger acc = new AtomicInteger(0);

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public WikiPageEntity() {
	    
	    
	    
	}

	public WikiPageEntity(int id, String title, String markdownText) {
		this.id = id;
		Title = title;
		MarkdownText = markdownText;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getMarkdownText() {
		return MarkdownText;
	}

	public void setMarkdownText(String markdownText) {
		MarkdownText = markdownText;
	}

	private int id;
	private String Title;
	private String MarkdownText;

	public WikiPageEntity(JsonObject obj) {
		WikiPageEntityConverter.fromJson(obj, this);
	}

	public WikiPageEntity(String jsonStr) {
		WikiPageEntityConverter.fromJson(new JsonObject(jsonStr), this);
	}

}
