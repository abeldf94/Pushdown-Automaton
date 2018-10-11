package es.ull.etsii.cc.automaton;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import es.ull.etsii.cc.components.Alphabet;
import es.ull.etsii.cc.components.State;
import es.ull.etsii.cc.components.Tape;
import es.ull.etsii.cc.components.Transition;

public class PushdownAutomaton {

	private List<State> setOfStates; // Set of states
	private Alphabet inputAlphabet; // Input alphabet
	private Alphabet stackAlphabet; // Stack alphabet
	private State initialState; // Initial state
	private String initialStackSymbol; // Initial stack symbol
	private List<Transition> setOfTransitions; // Set of transitions

	private Tape tape;
	private Stack<String> stack;

	private final Integer FIRST_TOKEN = 0;
	private final String COMMENT = "#";

	public PushdownAutomaton() {
		setOfStates = new ArrayList<>();
		inputAlphabet = new Alphabet();
		stackAlphabet = new Alphabet();
		initialState = new State();
		setOfTransitions = new ArrayList<>();
	}

	public void loadFileContent(String file) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(file));

		String line = null;
		String[] tokens;
		
		line = reader.readLine();
		tokens = line.split("//s+");

		Boolean flag = true;
		while (flag) {
			if (tokens[FIRST_TOKEN] == COMMENT) {
				line = reader.readLine();
				tokens = line.split("//s+");
			} else {
				flag = false;
			}
		}

		// Read Set of states
		for (String i : tokens) {
			State state = new State(i);
			setOfStates.add(state);
		}

		while ((line = reader.readLine()) != null) {

		}

		reader.close();

		writeAutomaton();
	}

	public void computeInput() {
	}

	public void writeAutomaton() {
		System.out.print("Printing states Q: ");
		for (State i : getSetOfStates())
			System.out.print(i.getId() + " ");

		System.out.println();
	}

	/** Getters and Setters **/

	public List<State> getSetOfStates() {
		return setOfStates;
	}

	public void setSetOfStates(List<State> setOfStates) {
		this.setOfStates = setOfStates;
	}

	public Alphabet getInputAlphabet() {
		return inputAlphabet;
	}

	public void setInputAlphabet(Alphabet inputAlphabet) {
		this.inputAlphabet = inputAlphabet;
	}

	public Alphabet getStackAlphabet() {
		return stackAlphabet;
	}

	public void setStackAlphabet(Alphabet stackAlphabet) {
		this.stackAlphabet = stackAlphabet;
	}

	public State getInitialState() {
		return initialState;
	}

	public void setInitialState(State initialState) {
		this.initialState = initialState;
	}

	public String getInitialStackSymbol() {
		return initialStackSymbol;
	}

	public void setInitialStackSymbol(String initialStackSymbol) {
		this.initialStackSymbol = initialStackSymbol;
	}

	public List<Transition> getSetOfTransitions() {
		return setOfTransitions;
	}

	public void setSetOfTransitions(List<Transition> setOfTransitions) {
		this.setOfTransitions = setOfTransitions;
	}

	public Tape getTape() {
		return tape;
	}

	public void setTape(Tape tape) {
		this.tape = tape;
	}

	public Stack<String> getStack() {
		return stack;
	}

	public void setStack(Stack<String> stack) {
		this.stack = stack;
	}

}