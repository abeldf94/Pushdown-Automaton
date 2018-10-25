# Pushdown Automaton Simulator

### Description:

* A pushdown automaton is a way to implement a context-free grammar in a similar way we design DFA for a regular grammar. A DFA can remember a finite amount of information, but a PDA can remember an infinite amount of information.

* A PDA has three main components:
	- An input tape.
	- A control unit.
	- A stack with infinite size.

* A PDA can be formally described as a 7-tuple (Q, ∑, Γ, s, Z, δ)
	- Q is the finite number of states
	- ∑ is input alphabet
	- Γ is stack symbols
	- s is the initial state
	- Z is the initial stack top symbol
	- δ is the transition function

* In this simulation, we won't implement a final state PDA. It will accept the input if the stack is empty and input consumed.

#### How to use it:
* Program can receive two arguments. It need a file that contains the machine and also can receive debug as optional parameter.
	- file.txt [debug]
* The file structure it's defined by the pdf guide that it's available in this repository.

#### Examples tested:

* APv.txt
	- L = {a^nb^n | n > 0}
* APv-2.txt
	- L = {w | wE{0,1}* and w = reversed w}
