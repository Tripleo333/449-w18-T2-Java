//package assignment.java.main;


public class PossibilityTree {
	
	private Constraints constraints;
	public char[] tasks;
	public double currentMinPenalty = Double.POSITIVE_INFINITY;
	public State minPenalty = null;

	/*
	 * Checks and Initiates the possibleTasks field.
	 * PARAM:
	 * possibleTasks - A char array of upper-case letters indicating tasks that are possible. Tasks don't have to be in any particular order.
	*/
	public PossibilityTree(char[] possibleTasks, Constraints cs) {
		char[] alphabet = new char[26];
		for(char c = 'A'; c <= 'Z'; ++c) {
		    alphabet[c-65] = c;
		}
		int counter = 0;
		for (char letter : alphabet) {
			for (char ourLetter : possibleTasks) {
				if (ourLetter == letter)
					counter++;
			}
		}
		if (counter == possibleTasks.length)
			this.tasks = possibleTasks;
		constraints = cs;
	}
	
	/*
	 * Recursive function that checks whether the current list of tasks meets the hard constraints, 
	 * then checks whether the current list of tasks has the lowest penalty value so far, 
	 * then branches out a branch for every possible next task and calls itself.
	 * PARAM:
	 * i - The current (non-empty) index of the task list. The i+1th index is empty.
	 * state - The current task list. Full until index i+1.
	 */

	
	public void Branch(int i, State state, char[] toDo) {	    

        if (!constraints.checkHardConstraints(i, state)) {
            return;
        }
        
        state.penalty += constraints.checkSoftConstraints(i, state);
        
        if (i >= 7) {
            if (state.penalty < currentMinPenalty) {
                currentMinPenalty = state.penalty;
                minPenalty = state;
            }
            return;
        }
        if (state.penalty > currentMinPenalty) {
            return;
        }
        
        for (int x = 0; x < toDo.length; x++) {
            char[] newToDo = toDo.clone();
            if (toDo[x] != 'X') {
                newToDo[x] = 'X';
                Branch(i+1, new State(i+1, toDo[x], state), newToDo);
            }
        }
        return;
        
        
        
        
	}
		
}