package assignment.java.main;


/*
 * This is the class that represents an iteration of the algorithm.
 */
public class State {
    
    // Entries is a character A, B, C, D, E, F, G, H for the related task, or X for unknown
    public char[] entries = {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'};
    // Penalty is current penalty value
    public int penalty;
    
    public State() {
        
    }
    
    public State(char[] entries) {
        this(entries, 0);
    }
    
    public State(char[] entries, int penalty) {
        this.entries = entries;
        this.penalty = penalty;
    }
    
    public State(int i, char task, State prevState) {
    	for (int j = 0; j < 8; j++) {
    		if (j == i) {
    			this.entries[j] = task;
    		}
    		else {
    			this.entries[j] = prevState.entries[j]; 
    		}    			
    	}
    	penalty = prevState.penalty;
    }
}
