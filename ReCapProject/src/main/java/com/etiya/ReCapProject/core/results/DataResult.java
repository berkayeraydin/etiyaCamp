package com.etiya.ReCapProject.core.results;

public class DataResult<T> extends Result {
	
	private T data;
	
	public DataResult(T data,boolean succes, String message) {
		super(succes, message);
		this.data=data;
	}
	
	public DataResult(T data,boolean succes) {
		super(succes);
		this.data=data;
	}
	
	public T getData() {
		return this.data;
	}
}
