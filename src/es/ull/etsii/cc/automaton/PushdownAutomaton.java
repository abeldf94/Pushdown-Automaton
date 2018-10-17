package es.ull.etsii.cc.automaton;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import es.ull.etsii.cc.components.Alphabet;
import es.ull.etsii.cc.components.Pair;
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

	private final String EPSILON = ".";

	public PushdownAutomaton() {
		setOfStates = new ArrayList<>();
		inputAlphabet = new Alphabet();
		stackAlphabet = new Alphabet();
		initialState = new State();
		setOfTransitions = new ArrayList<>();

		tape = new Tape();
		stack = new Stack<>();
	}

	public void loadFileContent(String file) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(file));

		String line = reader.readLine();
		String[] tokens = line.split("\\s+");

		// Skip first lines if they are comments
		while (tokens[0].equals("#")) {
			line = reader.readLine();
			tokens = line.split("\\s+");
		}

		tokens = line.split("#")[0].split("\\s+"); // Ignore comments in line

		// Here come the set of states so we create it
		for (String i : tokens) {
			State state = new State(i);
			setOfStates.add(state);
		}

		// Read input alphabet and ignore comments
		line = reader.readLine();
		tokens = line.split("#")[0].split("\\s+");

		// Create tape alphabet
		for (String i : tokens) {
			inputAlphabet.addElement(i);
		}

		// Read stack alphabet and ignore comments
		line = reader.readLine();
		tokens = line.split("#")[0].split("\\s+");

		// Create stack alphabet
		for (String i : tokens) {
			stackAlphabet.addElement(i);
		}

		// Read initial state
		line = reader.readLine();
		tokens = line.split("#")[0].split("\\s+");

		setInitialState(new State(tokens[0]));

		// Read initial stack symbol
		line = reader.readLine();
		tokens = line.split("#")[0].split("\\s+");

		setInitialStackSymbol(tokens[0]);

		// Read transitions and create it
		while ((line = reader.readLine()) != null) {
			tokens = line.split("#")[0].split("\\s+");

			Transition transition = new Transition(new State(tokens[0]), tokens[1], tokens[2], new State(tokens[3]),
					Arrays.copyOfRange(tokens, 4, tokens.length));
			setOfTransitions.add(transition);
		}

		reader.close();

		// writeAutomaton();
	}

	public void computeInput(String input) {
		// Set new input in tape and reset machine
		tape.resetTape(input);

		State current = getInitialState();

		stack.clear();
		stack.push(getInitialStackSymbol());

		Stack<Pair> paths = new Stack<>();

		findTransitions(current, paths);

		while (!stack.isEmpty() && !paths.isEmpty()) {
			// TODO: fix if input could take epsilon without consume entry
			System.out.println("Input: " + tape.getInput().substring(tape.getPointer(), tape.getInput().length()));
			System.out.println("Current state: " + current.getId());
			System.out.println("Current letter: " + tape.getCurrentCharacterWithoutMove());
			System.out.print("Stack: ");
			for(String i : stack) {
				System.out.print(i + " ");
			}
			System.out.println();

			System.out.println("Transitions: ");
			for (Pair i : paths)
				System.out.println(i.getTransition().toString() + " en la pos " + i.getPosition());

			System.out.println();
			System.out.println();


			// Extract pair with current element
			Pair pair = paths.pop();
			Transition transition = pair.getTransition();

			// Set pointer correctly in case is wrong
			if (tape.getPointer() != pair.getPosition()) // Just update if it's different
				tape.setPointer(pair.getPosition());

			// Compute all the moves
			if (current.getId() != transition.getNextState().getId()) // Just update if it's different
				current = transition.getNextState(); // Update state

			stack.pop(); // Remove stack top
			tape.setPointer(tape.getPointer() + 1);

			// Reverse loop for insert correctly in the stack
			// If we want insert A B S, A must be in the top.
			for(int i = transition.getElements().size() - 1; i >= 0; i--) {
				if (!transition.getElements().get(i).equals(EPSILON)) // Don't insert if it's epsilon
					stack.add(transition.getElements().get(i));
			}

			findTransitions(current, paths);
		}

		if(stack.isEmpty() && tape.getPointer() == tape.getInput().length()) {
			System.out.println(tape.getInput() + " is accepted.");
		} else {
			System.out.println(tape.getInput() + " is not accepted.");
		}
	}

	/**
	 * Find transitions for the current symbol, stack top and state.
	 *
	 * @param current the current
	 * @param paths the paths
	 */
	private void findTransitions(State current, Stack<Pair> paths) {
		for (Transition i : getSetOfTransitions()) {
			// Check if state is the same, if not we wont keep comparing
			if (current.getId().equals(i.getCurrentState().getId())) {
				// If it's same symbol or epsilon
				if (tape.getCurrentCharacterWithoutMove().equals(i.getSymbol()) || i.getSymbol().equals(EPSILON)) {
					// If stack top it's equal
					if (!stack.isEmpty() && stack.peek().equals(i.getStackTop())) {
						paths.add(new Pair(i, tape.getPointer()));
					}
				}
			}
		}
	}

	/**
	 * Write automaton to check a correctly load from file.
	 */
	public void writeAutomaton() {
		System.out.print("Printing states Q: ");
		for (State i : getSetOfStates())
			System.out.print(i.getId() + " ");

		System.out.println();

		System.out.print("Printing input alphabet E: ");
		for (String i : getInputAlphabet().getElements())
			System.out.print(i + " ");

		System.out.println();

		System.out.print("Printing stack alphabet P: ");
		for (String i : getStackAlphabet().getElements())
			System.out.print(i + " ");

		System.out.println();

		System.out.println("Printing initial state s: " + getInitialState().getId());

		System.out.println("Printing initial stack symbol Z: " + getInitialStackSymbol());

		System.out.println("Printing transitions d: ");
		for (Transition i : getSetOfTransitions())
			System.out.println(i.toString());

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
