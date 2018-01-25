package assignment.java.main;

public class PossibilityTree {
	
	private Constraints constraints = new Constraints();
	private char[] tasks;
	private double currentMinPenalty = Double.POSITIVE_INFINITY;

	public PossibilityTree(char[] possibleTasks) {
		char[] alphabet = new char[26];
		for(char c = 'A'; c <= 'Z'; ++c) {
		    ++c;
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
	}
	
	public double Branch(int i, State state) {
		if (!meetsHardConstraints(i, state))
			return -1;
		else {
			double penalty = this.countPenalty(i, state);
			if (penalty >= this.currentMinPenalty)
				return -1;
			for (char task : this.tasks) {
				Branch(i+1,new State(i+1, task, state));
			} 
			return penalty;
		}
			
	}
	
	/* Meant to be implemented in a loop which provides i
	 * PARAM:
	 * i: the current index of state 
	 * state: the current state, an instance of State
	 */
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
	
}
