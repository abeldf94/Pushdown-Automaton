package es.ull.etsii.cc.components;

public class State {

	private String id;

	public State() {
		id = null;
	}

	public State(String id) {
		this.id = id;
	}
	
	/** Getters and Setters **/

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
