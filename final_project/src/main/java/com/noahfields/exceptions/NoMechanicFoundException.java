package com.noahfields.exceptions;

public class NoMechanicFoundException extends Exception {
	
	public NoMechanicFoundException() {
		super("No mechanic found with that id");
	}

}
