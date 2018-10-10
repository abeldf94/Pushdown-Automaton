package es.ull.etsii.cc.components;

public class Tape {
	private String content;
	private Integer pointer;
	
	public Tape() {
		setContent(null);
		setPointer(0);
	}
	
	public String getCurrentCharacter() {
		String result = Character.toString(getContent().charAt(getPointer()));
		pointer++;
		return result;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getPointer() {
		return pointer;
	}

	public void setPointer(Integer pointer) {
		this.pointer = pointer;
	}

}
