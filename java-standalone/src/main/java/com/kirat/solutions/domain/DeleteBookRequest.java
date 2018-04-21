package com.kirat.solutions.domain;

public class DeleteBookRequest {
	String bookPath;
	String clasification;
	
	public String getBookPath() {
		return bookPath;
	}
	public void setBookPath(String bookPath) {
		this.bookPath = bookPath;
	}
	public String getClasification() {
		return clasification;
	}
	public void setClasification(String clasification) {
		this.clasification = clasification;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	String path;
}
