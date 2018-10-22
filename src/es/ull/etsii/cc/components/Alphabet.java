package es.ull.etsii.cc.components;

import java.util.LinkedHashSet;

/**
 * Generic class for the automaton alphabet's(Tape and stack).
 */
public class Alphabet {
	
	/** The elements. */
	private LinkedHashSet<String> elements;
	
	/**
	 * Instantiates a new alphabet.
	 */
	public Alphabet() {
		elements = new LinkedHashSet<>();
	}
	
	/**
	 * Adds a element to the alphabet.
	 *
	 * @param element the element
	 */
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
