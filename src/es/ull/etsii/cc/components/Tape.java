package es.ull.etsii.cc.components;

public class Tape {
	private String input;
	private Integer pointer;
	
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
		String result = Character.toString(getInput().charAt(getPointer()));
		return result;
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
