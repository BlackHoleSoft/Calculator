package ru.ankanashov.calc.cache;

import java.util.ArrayList;
import java.util.Stack;

import ru.ankanashov.calc.operations.OperationElement;

public class SecondCache implements Cache {
	
	private ArrayList<CacheElement> list;
	
	public SecondCache(){
		list = new ArrayList<CacheElement>();
	}

	@Override
	public CacheElement find(Stack<OperationElement> operation) {
		for(CacheElement el : list){
			if(el.compare(operation)){
				return el;
			}
		}
		return null;
	}
	
	public int size(){
		return list.size();
	}
	
	public boolean contains(CacheElement el){
		return list.contains(el);
	}

	@Override
	public void push(Stack<OperationElement> operation, double result) {
		list.add(new CacheElement(operation, result));
	}
	
	public void push(CacheElement el){
		list.add(el);
	}

	@Override
	public void clear() {
		list.clear();
	}

}
