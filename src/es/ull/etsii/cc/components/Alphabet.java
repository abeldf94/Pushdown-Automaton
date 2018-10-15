package es.ull.etsii.cc.components;

import java.util.LinkedHashSet;

public class Alphabet {
	
	private LinkedHashSet<String> elements;
	
	public Alphabet() {
		elements = new LinkedHashSet<>();
	}
	
	public void addElement(String element) {
		elements.add(element);
	}

	/** Getters and Setters **/
	public LinkedHashSet<String> getElements() {
		return elements;
	}

	public void setElements(LinkedHashSet<String> elements) {
		this.elements = elements;
	}
}
