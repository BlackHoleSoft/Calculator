package ru.ankanashov.calc;

public interface Solver {
	
	public double getResult();	
	
	public void reset();
	
	public void add();
	
	public void subtract();
	
	public void mult();
	
	public void divide();
	
	public void sqrt();
	
	public void putNumber(double value);
	
}
