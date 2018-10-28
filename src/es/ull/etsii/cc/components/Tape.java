package es.ull.etsii.cc.components;


/**
 * Input tape for the automaton.
 */
public class Tape {

	/** The input string. */
	private String input;

	/** The pointer for get every character from input. */
	private Integer pointer;

	/** The epsilon character. */
	private final String EPSILON = ".";

	/**
	 * Instantiates a new tape.
	 */
	public Tape() {
		input = null;
		pointer = -1;
	}

	/**
	 * Reset the tape to default.
	 *
	 * @param input the input
	 */
	public void resetTape(String input) {
		setInput(input);
		setPointer(0);
	}

	/**
	 * Gets the current character it's pointing.
	 *
	 * @return the current character
	 */
	public String getCurrentCharacter() {
		if (getPointer() == getInput().length())
			return EPSILON;
		else
			return Character.toString(getInput().charAt(getPointer()));
	}

	/** Getters and Setters **/

	public String getInput() {
		return input;
	}

	public void setInput(String content) {
		this.input = content;
	}

	public Integer getPointer() {
		return pointer;
	}

	public void setPointer(Integer pointer) {
		this.pointer = pointer;
	}

}
