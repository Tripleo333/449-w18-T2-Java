package assignment.java.main;

import java.util.ArrayList;

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
	/*
	public void Branch(int i, State state, char[] notTaken) {
	    System.out.println("\nMach: " + i);
	    System.out.println("Entries: ");
	    for (int z = 0; z < state.entries.length; z++) {
	        System.out.print(state.entries[z]);
	    }
	    System.out.println("");
	    
	    /*
	    if (i >= 7) {
	        if (this.currentMinPenalty > state.penalty) {
	            this.currentMinPenalty = state.penalty;
	            this.minPenalty = state;
	        }
	        return state;
	    }
	    */
	    /*
	    if (!constraints.checkHardConstraints(i, state)) {
	        System.out.println("Doesn't meet hard constraints");
			return;
	    }
	    
		else {
			double penalty = constraints.checkSoftConstraints(i, state);
			if (penalty >= this.currentMinPenalty) { // Base case...
				return;
			}
			
			state.penalty += penalty;
			
			if (i >= 7) {
			    System.out.println("test");
			    if (this.currentMinPenalty > state.penalty) {
			        this.currentMinPenalty = state.penalty;
			        this.minPenalty = state;
			    }
			    return;
			}
			
			for (int x = 0; x < notTaken.length; x++) {
			    int counter = 0;
			    char[] next = new char[6 - i];
			    for (int y = 0; y < notTaken.length; y++) {
			        if (y != x) {
			            next[counter] = notTaken[y];
			            counter++;
			        }
			    }
			    System.out.println(next);
				Branch(i+1, new State(i+1, notTaken[x], state), next); // Recursive call to self
			} 
			//return minPenalty;
		}
	    */
	
	public void Branch(int i, State state, char[] toDo) {	    

/*
        System.out.print("i: " + i + "\nEntries: ");
        for (int z = 0; z < state.entries.length; z++) {
            System.out.print(state.entries[z]);
        }
        System.out.println("");
*/    
        if (!constraints.checkHardConstraints(i, state)) {
//            System.out.println("Doesn't meet hard constraints");
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
	
	
	
	
	
	/* Meant to be implemented in a loop which provides i
	 * PARAM:
	 * i: the current index of state 
	 * state: the current state, an instance of State
	 
	public boolean meetsHardConstraints(int i, State state) {
		char[] pair = new char[2];
		pair[0] = (char)(i+1 + '0');
		pair[1] = state.entries[i];
		
		// Determine if the pair {i+1, state.entries[i]} meets the Forbidden Machines constraint
		for (char[] pairElem : constraints.forbiddenMach) {
			if (pair == pairElem) {
				return false;
			}
		}
		
		// Determine if the pair {i+1, state.entries[i]} and {i+2, state.entries[i+1]} meets the Too Near Tasks constraint
		for (i = 0; i < constraints.tooNearTasks.size(); i++) {
			char[] myPair = constraints.tooNearTasks.get(i);
			if (state.entries[i] == myPair[0] && state.entries[i+1] == myPair[1])
				return false;
			
		}
		
		// Determine if the pair {i+1, state.entries[i]} meets the Forced Partial Assignment constraint
		// TODO: fix this semantic error
		for (char[] mustPair : constraints.forcedPartialAssn) {
			if (pair == mustPair) {
				return true; // only one case where this method can return true!
			}
		}
		
		return false;
	}
	
	public int countPenalty(int i, State state) { return 0;}
	*/
}
