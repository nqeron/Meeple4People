package com.noahfields.exceptions;

public class NoGameFoundException extends Exception {
	
	public NoGameFoundException() {
		super("Could not find game with that id");
	}

}
