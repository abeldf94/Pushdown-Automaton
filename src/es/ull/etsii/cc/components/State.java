package es.ull.etsii.cc.components;

public class State {

	private String id;

	public State() {
		setId(null);
	}

	public State(String id) {
		setId(id);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
