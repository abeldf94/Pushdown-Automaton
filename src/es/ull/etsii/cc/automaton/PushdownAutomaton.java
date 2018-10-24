package es.ull.etsii.cc.automaton;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import es.ull.etsii.cc.components.Alphabet;
import es.ull.etsii.cc.components.Save;
import es.ull.etsii.cc.components.State;
import es.ull.etsii.cc.components.Tape;
import es.ull.etsii.cc.components.Transition;

/**
 * Pushdown Automaton simulator.
 */
public class PushdownAutomaton {

	/** The set of states. */
	private List<State> setOfStates;
	
	/** The input alphabet. */
	private Alphabet inputAlphabet;
	
	/** The stack alphabet. */
	private Alphabet stackAlphabet;
	
	/** The initial state. */
	private State initialState;
	
	/** The initial stack symbol. */
	private String initialStackSymbol;
	
	/** The set of transitions. */
	private List<Transition> setOfTransitions;

	/** The input tape. */
	private Tape tape;
	
	/** The stack. */
	private Stack<String> stack;

	/** The epsilon. */
	private final String EPSILON = ".";

	/**
	 * Instantiates a new pushdown automaton.
	 */
	public PushdownAutomaton() {
		setOfStates = new ArrayList<>();
		inputAlphabet = new Alphabet();
		stackAlphabet = new Alphabet();
		initialState = new State();
		setOfTransitions = new ArrayList<>();

		tape = new Tape();
		stack = new Stack<>();
	}

	/**
	 * Load the file content.
	 *
	 * @param file the file
	 * @throws Exception 
	 */
	public void loadFileContent(String file) throws Exception {
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
		
		// Check initial state is correct
		if (!checkInitialState()) {
			reader.close();
			throw new Exception("initial state not belongs to the set of states.");
		}

		// Read initial stack symbol
		line = reader.readLine();
		tokens = line.split("#")[0].split("\\s+");

		setInitialStackSymbol(tokens[0]);
		
		// Check initial stack symbol are correctly
		if (!checkInitialStackSymbol()) {
			reader.close();
			throw new Exception("initial stack symbol not belongs to the stack alphabet.");
		}

		// Read transitions and create it
		while ((line = reader.readLine()) != null) {
			tokens = line.split("#")[0].split("\\s+");

			Transition transition = new Transition(new State(tokens[0]), tokens[1], tokens[2], new State(tokens[3]),
					Arrays.copyOfRange(tokens, 4, tokens.length));
			setOfTransitions.add(transition);
		}

		reader.close();
		
		// Check transitions are correctly
		if (!checkTransitions()) {
			throw new Exception("transitions are incorrectly.");
		}

		// writeAutomaton();
	}

	/**
	 * Compute input to check if it belongs to the language or not.
	 *
	 * @param input the input
	 */
	public void computeInput(String input, Boolean debug) {
		// Set new input in tape and reset machine
		tape.resetTape(input);
		
		State current = getInitialState();

		stack.clear();
		stack.push(getInitialStackSymbol());

		Stack<Save> paths = new Stack<>();

		// Find the transitions for the input
		findTransitions(current, paths);
		
		if (debug) {
			System.out.println("Current state: " + current.getId());
			System.out.println("Input" + tape.getInput().substring(tape.getPointer(), tape.getInput().length()));

			System.out.print("Stack: ");
			for (String i : stack) {
				System.out.print(i + " ");
			}
			System.out.println();
			
			System.out.println("Available transitions: ");
			for (Save i : paths)
				System.out.println(i.getTransition().toString());
			
			System.out.println();
		}

		boolean accepted = false;
		
		while (!paths.isEmpty() && !accepted) {

			// Extract pair with current element
			Save save = paths.pop();
			Transition transition = save.getTransition();
			
			// Set pointer correctly in case is wrong
			if (tape.getPointer() != save.getPosition()) // Just update if it's different
				tape.setPointer(save.getPosition());
		
			// Compute all the moves
			if (!current.getId().equals(transition.getNextState().getId())) // Just update if it's different
				current = transition.getNextState(); // Update state
			
			
			// Compare stacks and reset if it's different
			if (!compareStacks(stack, save.getStack())) 
				stack = save.getStack();				

			stack.pop(); // Remove stack top
			if (!transition.getSymbol().equals(EPSILON))  // Tape pointer just move if it's not an epsilon
				tape.setPointer(tape.getPointer() + 1);

			// Reverse loop for insert correctly in the stack
			// If we want insert A B S, A must be in the top.
			for(int i = transition.getElements().size() - 1; i >= 0; i--) {
				if (!transition.getElements().get(i).equals(EPSILON)) // Don't insert if it's epsilon
					stack.add(transition.getElements().get(i));
			}

			findTransitions(current, paths);
			
			if (debug) {
				System.out.println("Current state: " + current.getId());
				System.out.println("Input: " + tape.getInput().substring(tape.getPointer(), tape.getInput().length()));

				System.out.print("Stack: ");
				for (String i : stack) {
					System.out.print(i + " ");
				}
				System.out.println();
				
				System.out.println("Available transitions: ");
				for (Save i : paths)
					System.out.println(i.getTransition().toString());
				
				System.out.println();
			}
			
			// If stack is empty and the input is all used, it's accepted.
			if(stack.isEmpty() && tape.getPointer() == tape.getInput().length())
				accepted = true;
		}

		if(accepted) 
			System.out.println(tape.getInput() + " is accepted.");
		else 
			System.out.println(tape.getInput() + " is not accepted.");
		
	}

	/**
	 * Find transitions for the current symbol, stack top and state.
	 *
	 * @param current the current
	 * @param paths the paths
	 */
	private void findTransitions(State current, Stack<Save> paths) {
		for (Transition i : getSetOfTransitions()) {
			// Check if state is the same, if not we wont keep comparing
			if (current.getId().equals(i.getCurrentState().getId())) {
				// If it's same symbol or epsilon
				if (tape.getCurrentCharacter().equals(i.getSymbol()) || i.getSymbol().equals(EPSILON)) {
					// If stack top it's equal
					if (!stack.isEmpty() && stack.peek().equals(i.getStackTop())) {
						Stack<String> copy = new Stack<>(); // Copy of stack, so it don't keep a reference
						copy.addAll(stack);
						paths.add(new Save(i, tape.getPointer(), copy));
					}
				}
			}
		}
	}
	
	/**
	 * Compare tow stacks to check if they are equal.
	 *
	 * @param first the first
	 * @param second the second
	 * @return the boolean
	 */
	public Boolean compareStacks (Stack<String> first, Stack<String> second) {
		if(first == second) return true; // Check if they point to same object
		if (first.size() != second.size()) return false; // If size it's different, elements are differents
		if (first.isEmpty() && second.isEmpty()) return true; // If both are empty, they are equal
		
		// Iterators for go throw stacks without pop elements
		Iterator<String> firstItr = first.iterator();
		Iterator<String> secondItr = second.iterator();
		
		// Compare all the elements
		while(firstItr.hasNext() && secondItr.hasNext()) {
			if (!firstItr.next().equals(secondItr.next()))
				return false;
		}
		
		return true;
	}

	/**
	 * Check initial state.
	 *
	 * @return the boolean
	 */
	public Boolean checkInitialState() {
		for(State i : setOfStates) {
			if (i.getId().equals(initialState.getId()))
				return true;
		}
		return false;
	}
	
	/**
	 * Check initial stack symbol.
	 *
	 * @return the boolean
	 */
	public Boolean checkInitialStackSymbol() {
		for(String i : stackAlphabet.getElements()) {
			if (i.equals(initialStackSymbol))
				return true;
		}
		return false;
	}
	
	/**
	 * Check transition state.
	 *
	 * @param state the state
	 * @return the boolean
	 */
	public Boolean checkState(State state) {
		for(State i : setOfStates) {
			if (i.getId().equals(state.getId()))
				return true;
		}		
		return false;
	}
	
	/**
	 * Check element belongs to stack alphabet.
	 *
	 * @param element the element
	 * @return the boolean
	 */
	public Boolean checkStackAlphabet(String element) {
		for(String i : stackAlphabet.getElements()) {
			if (i.equals(element))
				return true;
		}		
		return false;
	}
	
	/**
	 * Check symbol belongs input alphabet.
	 *
	 * @param element the element
	 * @return the boolean
	 */
	public Boolean checkSymbol(String element) {
		for(String i : inputAlphabet.getElements()) {
			if (i.equals(element))
				return true;
		}		
		return false;
	}
	
	
	/**
	 * Check all the transitions.
	 *
	 * @return the boolean
	 */
	public Boolean checkTransitions() {
		for(Transition i : setOfTransitions) {
			if (!checkState(i.getCurrentState()))
				return false;
			else if (!checkState(i.getNextState()))
				return false;
			else if (!checkStackAlphabet(i.getStackTop()))
				return false;
			else if (!i.getSymbol().equals(".")) {
				if (!checkSymbol(i.getSymbol())) 
					return false;
			} else if (!i.getElements().get(0).equals(".")) {
				if (!stackAlphabet.getElements().containsAll(i.getElements())) 
					return false;
			}
		}
		return true;
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
