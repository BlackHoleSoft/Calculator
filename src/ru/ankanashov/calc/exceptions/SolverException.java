package ru.ankanashov.calc.exceptions;

public class SolverException extends Exception {	
	
	private static final long serialVersionUID = 1L;

	public SolverException(String msg){
		super(msg);
	}
	
	public SolverException(){
		super("Error during calculation");
	}
	
}
