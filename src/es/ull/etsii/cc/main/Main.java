package es.ull.etsii.cc.main;

import java.io.IOException;

import es.ull.etsii.cc.automaton.PushdownAutomaton;

public class Main {

	public static void main(String[] args) {
		try {
			PushdownAutomaton myAutomaton = new PushdownAutomaton();
			myAutomaton.loadFileContent(args[0]);
		} catch (IOException error) {
			System.err.println("Error: " + error.getMessage());
			error.printStackTrace();
		}
	}

}
