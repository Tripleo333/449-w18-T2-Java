package assignment.java.main;

import java.util.LinkedList;
import java.util.Optional;

public class Constraints {
	
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
	public int[][] machinePenalties;
	/*
	 * Triplets of <Task1, Task2, Penalty value>. If Task1 and Task2 are assigned to neighboring machines, the penalty value applies.
	 */
	public LinkedList<Triplet> tooNearPenalties;
	
	/*
	* No-argument constructor. Initializes LinkedList type fields using their no-arg constructors.
	*/
	public Constraints() {
		this.forcedPartialAssn = new LinkedList<char[]>();
		this.forbiddenMach = new LinkedList<char[]>();
		this.tooNearTasks = new LinkedList<char[]>();
		this.tooNearPenalties = new LinkedList<Triplet>();
	}
	
	/*
	 * PARAM:
	 * type - Type of constraint, a constant.
	 * pairOpt - Char array of machine, task or task, task to add to the given constraint's linked list. Not used with constraint type MACHINE_PENALTY.
	 * penaltyOpt - Penalty associated with a machine-task pair. Used only for soft constraint types (MACHINE_PENALTIES, TOO_NEAR_PENALTIES).
	 * mechineIndx - Index of outer array corresponding to the machine in question. Used only for MACHINE_PENALTIES.
	 * taskIndx - Index of inner array corresponding to task assigned to machine in question. Used only for MACHINE_PENALTIES.
	 */
	public void addConstraintPair(int type, Optional<char[]> pairOpt, Optional<Integer> penaltyOpt, Optional<Integer> machineIndx, Optional<Integer> taskIndx) {
		Integer penalty = penaltyOpt.isPresent() ? penaltyOpt.get() : 0;
		char[] pair = pairOpt.isPresent() ? pairOpt.get() : null;
		int machine = machineIndx.isPresent() ? machineIndx.get().intValue() : 0;
		int task = taskIndx.isPresent() ? taskIndx.get().intValue() : 0;
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
			this.machinePenalties[machine][task] = penalty;
			break;
		case TOO_NEAR_PENALTIES:
			Triplet triplet = new Triplet(pair[0], pair[1], penalty);
			this.tooNearPenalties.add(triplet);
			break;
		default: 
			System.err.println("Invalid type argument!");
		}

}
