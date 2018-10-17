package es.ull.etsii.cc.components;

public class Tape {
	private String input;
	private Integer pointer;
	private final String EPSILON = ".";
	
	public Tape() {
		input = null;
		pointer = -1;
	}
	
	public void resetTape(String input) {
		setInput(input);
		setPointer(0);
	}
	
	public String getCurrentCharacter() {
		String result = Character.toString(getInput().charAt(getPointer()));
		pointer++;
		return result;
	}
	
	public String getCurrentCharacterWithoutMove() {
		if (getPointer() == getInput().length())
			return EPSILON;
		else
			return Character.toString(getInput().charAt(getPointer()));
	}

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
