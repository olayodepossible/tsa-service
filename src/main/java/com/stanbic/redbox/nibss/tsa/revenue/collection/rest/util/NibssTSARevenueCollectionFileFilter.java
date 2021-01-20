package com.stanbic.redbox.nibss.tsa.revenue.collection.rest.util;

import org.apache.camel.component.file.GenericFile;
import org.apache.camel.component.file.GenericFileFilter;

public class NibssTSARevenueCollectionFileFilter<T> implements GenericFileFilter<T> {
	private String fileRegexPattern;

	public boolean accept(GenericFile<T> file) {
		return file.getFileName().contains(this.fileRegexPattern);
	}

	public String getFileRegexPattern() {
		return this.fileRegexPattern;
	}

	public void setFileRegexPattern(String fileRegexPattern) {
		this.fileRegexPattern = fileRegexPattern;
	}
}