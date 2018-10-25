package es.ull.etsii.cc.main;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import es.ull.etsii.cc.automaton.PushdownAutomaton;

/**
 * Main class of the program. It won't be used for create new objects.
 */
public class Main {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		try {
			if (args.length > 2 || args.length == 0) {
				throw new Exception("incorrect program arguments. Usage: file.txt [debug]");
			}
			
			// Automaton
			PushdownAutomaton myAutomaton = new PushdownAutomaton();
			myAutomaton.loadFileContent(args[0]);
			System.out.println("Machine loaded. ");

			// Read console input
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			Boolean loop = true;
			do {
				System.out.print("\nTo exit the program, insert a '*'. Insert a string to compute: ");
				String input = reader.readLine();

				if (input.equals("*"))  // Exit loop to end program if matches exit symbol
					loop = false;
				else { // Compute input
					if (args.length == 2) {
						if (args[1].equals("debug"))
							myAutomaton.computeInput(input, true);
						else
							myAutomaton.computeInput(input, false);
					} else 
						myAutomaton.computeInput(input, false);
				}
				
			} while (loop);
			
			System.out.println("Shutting down. Bye!");
		} catch (Exception error) {
			System.err.println("Error: " + error.getMessage());
			error.printStackTrace();
		}
	}

}
