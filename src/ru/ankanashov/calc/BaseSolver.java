package ru.ankanashov.calc;

import java.util.ArrayList;

public class BaseSolver {	
	
	private ArrayList<Double> operands;
	
	private enum Operation{
		ADD, SUBTRACT, MULT, DIV, SQRT, NONE
	}
	
	private ArrayList<Operation> operations;
	
	public BaseSolver(){
		reset();
	}
	
	public double getResult(){
		double res = operands.get(0);
		if(operations.size() == 0) return 0;
		if(operations.get(operations.size()-1) == Operation.SQRT){
			return Math.sqrt(operands.get(0));
		}
		if(operands.size() < 2 || operations.size()!= operands.size()-1) return 0;
		
		for(int i=1; i<operands.size(); i++){
			switch(operations.get(i-1)){
				case ADD: res += operands.get(i); break;
				case SUBTRACT: res -= operands.get(i); break;
				case MULT: res *= operands.get(i); break;
				case DIV: res /= operands.get(i); break;			
				default: return 0.0;
			}
		}
		reset();
		return res;
	}
	
	public void addOperand(double num){
		operands.add(num);
	}
	
	public void reset(){		
		operands = new ArrayList<Double>();
		operations = new ArrayList<Operation>();
	}
	
	public void add(){
		operations.add(Operation.ADD);		
	}
	
	public void subtract(){
		operations.add(Operation.SUBTRACT);
	}
	
	public void mult(){
		operations.add(Operation.MULT);
	}
	
	public void divide(){
		operations.add(Operation.DIV);
	}
	
	public void sqrt(){
		operations.add(Operation.SQRT);
	}
	
}
