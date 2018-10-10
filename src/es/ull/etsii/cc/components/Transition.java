package es.ull.etsii.cc.components;

import java.util.ArrayList;
import java.util.List;

public class Transition {
	
	private State currentState;
	private String symbol;
	private String stackTop;
	private List<String> elements;
	
	/**
	 * Instantiates a new transition.
	 *
	 * @param current the current state
	 * @param symbol the symbol we need for this transition
	 * @param stackTop symbol that must have the top of the stack for this transition
	 * @param args the the different elements that will be inserted in the stack
	 */
	public Transition(State current, String symbol, String stackTop, String ...args) {
		setCurrentState(current);
		setSymbol(symbol);
		setStackTop(stackTop);
		//setElements(new ArrayList<>());
		
		List<String> helper = new ArrayList<>();
		for (String i : args)
			helper.add(i);
		
		setElements(helper);
	}

	/** Getters and Setters **/
	
	public State getCurrentState() {
		return currentState;
	}

	public void setCurrentState(State currentState) {
		this.currentState = currentState;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getStackTop() {
		return stackTop;
	}

	public void setStackTop(String stackTop) {
		this.stackTop = stackTop;
	}

	public List<String> getElements() {
		return elements;
	}

	public void setElements(List<String> elements) {
		this.elements = elements;
	}
}
