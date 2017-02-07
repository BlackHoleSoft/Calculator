package ru.ankanashov.calc.cache;

import java.util.LinkedList;
import java.util.Stack;

import ru.ankanashov.calc.operations.OperationElement;

public class FirstCache implements Cache {
	
	private final int MAX_SIZE = 10;
	private LinkedList<CacheElement> list;
	private SecondCache secondCache;
	
	public FirstCache(){
		list = new LinkedList<CacheElement>();
		secondCache = new SecondCache();
	}

	@Override
	public CacheElement find(Stack<OperationElement> operation) {
		for(CacheElement el : list){
			if(el.compare(operation)){ //this operation exists
				list.remove(el);
				list.addLast(el); //move to the top
				return el;
			}
		}
		return secondCache.find(operation); //search in second cache, returns null if not exist
	}

	public SecondCache getSecondCache() {
		return secondCache;
	}

	@Override
	public void clear() {
		list.clear();
	}

	@Override
	public void push(Stack<OperationElement> operation, double result) {
		list.addLast(new CacheElement(operation, result));
		if(list.size() > MAX_SIZE){
			CacheElement tmp = list.removeFirst(); //remove old entries
			if(!secondCache.contains(tmp)){
				secondCache.push(tmp);
			}
		}
	}

}
