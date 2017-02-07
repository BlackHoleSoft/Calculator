package ru.ankanashov.calc.operations;

public interface OperationElement {
	
	boolean isNumber();
	boolean isUnary();
	Operations getOperation();
	int getPriority();
	double getValue();
	CalcNumber calculate(double num1, double num2);
	
}
