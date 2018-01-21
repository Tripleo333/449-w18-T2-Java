
public class PossibilityTree {
	
	private Constraints constraints = new Constraints();

	public State Branch(State state) {
		// recursive dfs
		// uses meetsHardConstraints() to determine validity of the branch of the tree
		return state;
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
		for (i = 0; i < constraints.tooNearTasks.length; i++) {
				if (state.entries[i] == constraints.tooNearTasks[i][0] && state.entries[i+1] == constraints.tooNearTasks[i][1])
					return false;
		}
		
		// Determine if the pair {i+1, state.entries[i]} meets the Forced partial Assignment constraint
		for (char[] mustPair : constraints.forcedPartialAssn) {
			if (pair == mustPair) {
				return true; // only one case where this method can return true!
			}
		}
		
		return false;
	}
	
}
