package es.ull.etsii.cc.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import es.ull.etsii.cc.automaton.PushdownAutomaton;

public class Main {

	public static void main(String[] args) {
		try {
			PushdownAutomaton myAutomaton = new PushdownAutomaton();
			myAutomaton.loadFileContent(args[0]);
			System.out.println("Machine loaded. ");

			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			Boolean loop = true;
			do {
				System.out.println("\nTo exit the program, insert *");
				System.out.print("\nInsert a string to compute: ");
				String input = reader.readLine();

				if (input.equals("*"))  // Exit loop to end program if matches exit symbol
					loop = false;
				else  // Compute input
					myAutomaton.computeInput(input);
				
			} while (loop);
			
			System.out.println("Shutting down. Bye!");
		} catch (IOException error) {
			System.err.println("Error: " + error.getMessage());
			error.printStackTrace();
		}
	}

}
