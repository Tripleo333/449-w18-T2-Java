package assignment.java.main;

import java.util.LinkedList;
import java.util.Optional;

public class Constraints {
	
	private class Triplet {
		char mach;
		char task;
		Integer penalty;
		
		Triplet(char mach, char task, Integer penalty) {
			this.mach = mach;
			this.task = task;
			this.penalty = penalty;
		}
	}
	
	public static final int FORCED_PARTIAL_ASSIGNMENT = 1;
	public static final int FORBIDDEN_MACHINE = 2;
	public static final int TOO_NEAR_TASKS = 3;
	public static final int MACHINE_PENALTIES = 4;
	public static final int TOO_NEAR_PENALTIES = 5;
	
	// Hard Constraints
	/*
	 * These pairs must be included in the final state
	 * Example: ['1', 'F']
	 */
	public LinkedList<char[]> forcedPartialAssn;
	/*
	 * These pairs cannot be included in the final state
	 */
	public LinkedList<char[]> forbiddenMach;
	/*
	 * For every row i in tooNearTasks, machine i cannot take the first task and machine i+1 cannot take the second task
	 */
	public LinkedList<char[]> tooNearTasks;
	
	// Soft Constraints	
	/*
	 * 8X8 matrix of penalties. If p is the number at position i on line j and an assignment has assigned machine j to task at i, 
	 * then p is added to the penalty value of that assignment.
	 */
	public LinkedList<Integer> machinePenalties;
	/*
	 * 
	 */
	public LinkedList<Triplet> tooNearPenalties;
	
	
	public Constraints() {
		this.forcedPartialAssn = new LinkedList<char[]>();
	}
	
	public void addConstraintPair(int type, Optional<char[]> pairOpt, Optional<Integer> penaltyOpt) {
		Integer penalty = penaltyOpt.isPresent() ? penaltyOpt.get() : 0;
		char[] pair = pairOpt.isPresent() ? pairOpt.get() : null;
		switch(type) {
		case FORCED_PARTIAL_ASSIGNMENT:
			this.forcedPartialAssn.add(pair);
			break;
		case FORBIDDEN_MACHINE:
			this.forbiddenMach.add(pair);
			break;
		case TOO_NEAR_TASKS:
			this.tooNearTasks.add(pair);
			break;
		case MACHINE_PENALTIES:
			this.machinePenalties.add(penalty);
			break;
		case TOO_NEAR_PENALTIES:
			Triplet triplet = new Triplet(pair[0], pair[1], penalty);
			this.tooNearPenalties.add(triplet);
			break;
		default: 
			System.err.println("Invalid type argument!");
		}
	}

}
