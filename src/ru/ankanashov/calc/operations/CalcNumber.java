package ru.ankanashov.calc.operations;

public class CalcNumber implements OperationElement {
	
	private double value;
	
	public CalcNumber(double value){
		this.value = value;
	}

	@Override
	public boolean isNumber() {
		return true;
	}

	@Override
	public Operations getOperation() {
		return Operations.NULL;
	}

	@Override
	public int getPriority() {
		return -1;
	}

	@Override
	public double getValue() {
		return value;
	}

	@Override
	public boolean isUnary() {
		return false;
	}

	@Override
	public CalcNumber calculate(double num1, double num2) {
		return null;
	}

	@Override
	public boolean equals(Object obj) {
		return ((CalcNumber)obj).getValue() == value;
	}
	
	
	
}
