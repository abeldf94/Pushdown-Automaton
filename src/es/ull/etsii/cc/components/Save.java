package es.ull.etsii.cc.components;

import java.util.Stack;

/**
 * Class for reset the automaton to a previous point when a path is not the solution.
 */
public class Save {
	
	/** The transition. */
	private Transition transition;
	
	/** The position for the input string. */
	private Integer position;
	
	/** The stack elements in that moment. */
	private Stack<String> stack;
	
	/**
	 * Instantiates a new save .
	 *
	 * @param transition the transition
	 * @param position the position
	 * @param stack the stack
	 */
	public Save (Transition transition, Integer position, Stack<String> stack) {
		this.transition = transition;
		this.position = position;
		this.stack = stack;
	}

	/** Getters and Setters **/
	
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
