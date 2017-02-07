package ru.ankanashov.calc.operations;

public class CalcOperation implements OperationElement {
	
	private Operations operation;
	private int priority;
	private boolean unary;
	
	public CalcOperation(Operations operation, int prior, boolean unary){
		priority = prior;
		this.operation = operation;
		this.unary = unary;
	}

	@Override
	public boolean isNumber() {
		return false;
	}

	@Override
	public Operations getOperation() {
		return operation;
	}

	@Override
	public int getPriority() {
		return priority;
	}

	@Override
	public double getValue() {
		return 0;
	}

	@Override
	public boolean isUnary() {
		return unary;
	}

	@Override
	public CalcNumber calculate(double num1, double num2) {
		switch(operation){
			case ADD:
				return new CalcNumber(num1+num2);
			case SUBTRACT:
				return new CalcNumber(num1-num2);
			case MULT:
				return new CalcNumber(num1*num2);
			case DIVIDE:
				return new CalcNumber(num1/num2);
			case SQRT:
				return new CalcNumber(Math.sqrt(num2));
			default:
				return new CalcNumber(0);
		}
	}

	@Override
	public boolean equals(Object obj) {
		CalcOperation op = (CalcOperation)obj;
		return (op.getOperation() == operation && op.getPriority() == priority && op.isUnary() == unary);
	}	
	
}
