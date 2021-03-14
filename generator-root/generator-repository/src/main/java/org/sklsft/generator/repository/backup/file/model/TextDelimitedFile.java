package org.sklsft.generator.repository.backup.file.model;

import java.util.List;

/**
 * representation of a text delimited file used to store backup data
 *
 */
public class TextDelimitedFile {
	
	/*
	 * properties
	 */
	private List<Object[]> data;
	
	
	/*
	 * getters and setters
	 */
	public List<Object[]> getData() {
		return data;
	}
	public void setData(List<Object[]> data) {
		this.data = data;
	}
}
