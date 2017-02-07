package ru.ankanashov.calc.operations;

public class OperationsFactory {
	
	private static final int LOW_PRIORITY = 2;
	private static final int MEDIUM_PRIORITY = 1;
	private static final int HIGH_PRIORITY = 0;
	
	public static OperationElement createAddOperation(){
		CalcOperation op = new CalcOperation(Operations.ADD, LOW_PRIORITY, false);
		return op;
	}
	
	public static OperationElement createSubtractOperation(){
		CalcOperation op = new CalcOperation(Operations.SUBTRACT, LOW_PRIORITY, false);
		return op;
	}
	
	public static OperationElement createMultiplyOperation(){
		CalcOperation op = new CalcOperation(Operations.MULT, MEDIUM_PRIORITY, false);
		return op;
	}
	
	public static OperationElement createDivideOperation(){
		CalcOperation op = new CalcOperation(Operations.DIVIDE, MEDIUM_PRIORITY, false);
		return op;
	}
	
	public static OperationElement createSqrtOperation(){
		CalcOperation op = new CalcOperation(Operations.SQRT, HIGH_PRIORITY, true);
		return op;
	}
	
}
