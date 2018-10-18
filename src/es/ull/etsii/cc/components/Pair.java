package es.ull.etsii.cc.components;

import java.util.Stack;

public class Pair {
	
	private Transition transition;
	private Integer position;
	private Stack<String> stack;
	
	public Pair (Transition transition, Integer position, Stack<String> stack) {
		this.transition = transition;
		this.position = position;
		this.stack = stack;
	}

	public Transition getTransition() {
		return transition;
	}

	public void setTransition(Transition transition) {
		this.transition = transition;
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	public Stack<String> getStack() {
		return stack;
	}

	public void setStack(Stack<String> stack) {
		this.stack = stack;
	}
}
