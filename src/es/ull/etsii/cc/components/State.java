package es.ull.etsii.cc.components;

/**
 * state representation for the transitions in the automaton.
 */
public class State {

	/** The id. */
	private String id;

	/**
	 * Instantiates a new state.
	 */
	public State() {
		id = null;
	}

	/**
	 * Instantiates a new state.
	 *
	 * @param id the id
	 */
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
