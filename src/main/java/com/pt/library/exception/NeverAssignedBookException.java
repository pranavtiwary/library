package com.pt.library.exception;

public class NeverAssignedBookException extends RuntimeException {

	private static final long serialVersionUID = 8322551771791743375L;

	public NeverAssignedBookException(String msg){
		super(msg);
	}
}
