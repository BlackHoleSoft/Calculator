package ru.ankanashov.calc.cache;

import java.util.Stack;
import ru.ankanashov.calc.operations.OperationElement;

public interface Cache {
	
	CacheElement find(Stack<OperationElement> operation);
	void push(Stack<OperationElement> operation, double result);
	void clear();
	
}
