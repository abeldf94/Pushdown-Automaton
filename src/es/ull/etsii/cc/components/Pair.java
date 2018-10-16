package es.ull.etsii.cc.components;

public class Pair {
	
	private Transition transition;
	private Integer position;
	
	public Pair (Transition transition, Integer position) {
		this.transition = transition;
		this.position = position;
	}

	public Transition getTransition() {
		return transition;
	}

	public void setTransition(Transition transition) {
		this.transition = transition;
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}
}
