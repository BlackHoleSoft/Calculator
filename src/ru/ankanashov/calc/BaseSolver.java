package ru.ankanashov.calc;

import java.util.LinkedList;
import java.util.Stack;

import ru.ankanashov.calc.cache.CacheElement;
import ru.ankanashov.calc.cache.FirstCache;
import ru.ankanashov.calc.operations.CalcNumber;
import ru.ankanashov.calc.operations.OperationElement;
import ru.ankanashov.calc.operations.OperationsFactory;

public class BaseSolver implements Solver {
	
	private Stack<OperationElement> operations;
	private FirstCache cache;
	private double result;
	
	public BaseSolver(){
		operations = new Stack<OperationElement>();
		cache = new FirstCache();
		reset();
	}
	
	public boolean lastIsNumber(){
		if(operations.size() == 0){
			return false;
		}
		return operations.peek().isNumber();
	}
	
	private void solve(){
		OperationElement[] copied = new OperationElement[operations.size()];
		operations.copyInto(copied);
		CacheElement elem = cache.find(operations);
		if(elem != null){
			result = elem.getResult();
			return;
		}
		
		LinkedList<OperationElement> lst = new LinkedList<OperationElement>();
		int size = operations.size();
		for(int i=0; i<size; i++){
			lst.addFirst(operations.pop());
		}
				
		int priority = 0;
		while(lst.size() > 1 && priority < 100){			
			for(int i=0; i<lst.size(); i++){
				OperationElement curr = lst.get(i);
				if(!curr.isNumber() && curr.getPriority() == priority){
					CalcNumber tmp;					
					if(curr.isUnary()){
						tmp = curr.calculate(0, lst.get(i+1).getValue());
						for(int j=0; j<2; j++){
							lst.remove(i); //remove operator and adjacent numbers
						}
						lst.add(i, tmp);
					}else{
						tmp = curr.calculate(lst.get(i-1).getValue(), lst.get(i+1).getValue());
						for(int j=0; j<3; j++){
							lst.remove(i-1); //remove operator and adjacent numbers
						}
						lst.add(i-1, tmp);
					}					
					i = 0;
				}				
			}
			priority++;
		}
		
		if(lst.get(0).isNumber()){
			result = lst.get(0).getValue();
			Stack<OperationElement> tmp = new Stack<OperationElement>();
			for(int i=0; i<copied.length; i++){
				tmp.push(copied[i]);
			}
			cache.push(tmp, result);
		}else{
			result = 0;
		}		
	}

	public FirstCache getCache() {
		return cache;
	}

	@Override
	public double getResult() {
		solve();
		return result;
	}

	@Override
	public void reset() {
		operations.clear();
	}

	@Override
	public void add() {
		operations.push(OperationsFactory.createAddOperation());
	}

	@Override
	public void subtract() {
		operations.push(OperationsFactory.createSubtractOperation());
	}

	@Override
	public void mult() {
		operations.push(OperationsFactory.createMultiplyOperation());
	}

	@Override
	public void divide() {
		operations.push(OperationsFactory.createDivideOperation());
	}

	@Override
	public void sqrt() {
		operations.push(OperationsFactory.createSqrtOperation());
	}

	@Override
	public void putNumber(double value) {
		operations.push(new CalcNumber(value));
	}
	
	
	
}
