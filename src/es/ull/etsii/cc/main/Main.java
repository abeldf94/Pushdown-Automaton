package es.ull.etsii.cc.main;

import es.ull.etsii.cc.automaton.PushdownAutomaton;

public class Main {

	public static void main(String[] args) {
		PushdownAutomaton myAutomaton = new PushdownAutomaton();
		myAutomaton.loadFileContent(args[0]);
	}

}
