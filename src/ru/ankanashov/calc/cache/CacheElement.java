package ru.ankanashov.calc.cache;

import java.util.Stack;

import ru.ankanashov.calc.operations.CalcNumber;
import ru.ankanashov.calc.operations.CalcOperation;
import ru.ankanashov.calc.operations.OperationElement;

public class CacheElement {
	
	private Stack<OperationElement> elem;
	private double result;
	
	public CacheElement(Stack<OperationElement> operation, double result){
		elem = operation;
		this.result = result;
	}
	
	public double getResult(){
		return result;
	}
	
	public boolean compare(Stack<OperationElement> operation){
		if(elem.size() != operation.size()){
			return false;
		}
		for(int i=0; i<elem.size(); i++){	//compare elements of two stacks		
			if(elem.get(i) instanceof CalcOperation){
				CalcOperation op = (CalcOperation)elem.get(i);
				if(!op.equals(operation.get(i))){ //call equals from child class
					return false;
				}
			}else if(elem.get(i) instanceof CalcNumber){
				CalcNumber num = (CalcNumber)elem.get(i);
				if(!num.equals(operation.get(i))){
					return false;
				}
			}
		}
		return true;
		//return elem.equals(operation);
	}
	
}
