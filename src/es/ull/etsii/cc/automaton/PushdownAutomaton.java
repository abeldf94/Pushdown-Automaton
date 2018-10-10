package es.ull.etsii.cc.automaton;

import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

import es.ull.etsii.cc.components.Alphabet;
import es.ull.etsii.cc.components.State;
import es.ull.etsii.cc.components.Tape;
import es.ull.etsii.cc.components.Transition;

public class PushdownAutomaton {

	private Set<State> setOfStates; // Set of states
	private Alphabet inputAlphabet; // Input alphabet
	private Alphabet stackAlphabet; // Stack alphabet
	private State initialState; // Initial state
	private Set<Transition> setOfTransitions; // Set of transitions
	
	private Tape tape;
	private Stack<String> stack;

	public PushdownAutomaton() {
		setOfStates = new TreeSet<>();
		inputAlphabet = new Alphabet();
		stackAlphabet = new Alphabet();
		initialState = new State();
		setOfTransitions = new TreeSet<>();
	}

	public void loadFileContent(String file) {

	}
	
	public void computeInput() {}

}
