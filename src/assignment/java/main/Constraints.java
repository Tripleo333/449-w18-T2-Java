package assignment.java.main;

import java.util.LinkedList;

public class Constraints {
	// By Anastasiya:
	
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
		this.machinePenalties = new int[8][8];
	}
	
	/*
	 * PARAM:
	 * type - Type of constraint, a constant.
	 * pairOpt - Char array of machine, task or task, task to add to the given constraint's linked list. Not used with constraint type MACHINE_PENALTY.
	 * penaltyOpt - Penalty associated with a machine-task pair. Used only for soft constraint types (MACHINE_PENALTIES, TOO_NEAR_PENALTIES).
	 * mechineIndx - Index of outer array corresponding to the machine in question. Used only for MACHINE_PENALTIES.
	 * taskIndx - Index of inner array corresponding to task assigned to machine in question. Used only for MACHINE_PENALTIES.
	 */
	public void addConstraintPair(int type, char[] pair) {
		switch(type) {
		case FORCED_PARTIAL_ASSIGNMENT:
			this.forcedPartialAssn.add(pair);
			break;
		case FORBIDDEN_MACHINE:
			this.forbiddenMach.add(pair);
			break;
		case TOO_NEAR_TASKS:
		    int sub = pair[0] + 1;
		    pair = new char[] {(char)sub, pair[1]};
			this.tooNearTasks.add(pair);
			break;
		default: 
			System.err.println("Invalid type argument!");
		}
	}
	
	public void addMachPenalties(int machineIndx, int taskIndx, int penalty) {
		this.machinePenalties[machineIndx][taskIndx] = penalty;
	}
	
	public void addTooNearPenalties(char[] pair, int penalty) {
	    int sub = pair[0] + 1;
		Triplet triplet = new Triplet((char)sub, pair[1], penalty);
		this.tooNearPenalties.add(triplet);
	}
	
	// By Joseph:
    /*
     * Please note that for this file I assume mach is an int which is the entry in the array, not the mach number (mach1 = 0, mach2 = 1, so on so forth)
     * 
     */
    
    
    /*
     * Checks soft constraints given newest filled machine and the state; returns penalty value associated with that assignment
     * 
     * @param int mach:     newest filled index in the state given (if state is a, b, x, ... then mach should be 1 to represent b)
     * @param State state:  given state to check constraints on
     */
    public int checkSoftConstraints(int mach, State state) {
        if (mach == -1) return 0;
        int returned = checkTNP(mach, state, tooNearPenalties.toArray(new Triplet[0]));
        //System.out.println(tooNearPenalties.getFirst());
        returned += checkMP(mach, state);
        return returned;
    }
    
    
    /*
     * The reason why this one is a little weird is because we want to assume current entry is task 2 and check "behind"
     * it for task one.  However, if the mach is 0 then we check nothing because there is only one task assigned, and if the mach is 7 then we don't check "behind",
     * instead we wrap around and check 0.
     * 
     * @param int mach:     newest filled index in the state given (if state is a, b, x, ... then mach should be 1 to represent b)
     * @param State state:  given state to check constraints on
     * @param Triplet[] cs: array of TNP triplets
     */
    public int checkTNP(int mach, State state, Triplet[] cs) {
        int returned = 0;
        
        // Can't do anything; only 1 machine to check; need 2
        if (mach == 0) {
            return returned;
        }
        
        // If we're checking the last machine, wrap around and consider mach 7 as task 1 and mach 0 as task 2
        if (mach == 7) {
            for (int i = 0; i < cs.length; i++) {
                // If task 1 in constraint == task in mach:
                if (cs[i].task1 == state.entries[mach]) {
                    // If task 2 in constraint == task in state.entries[0]:
                    if (cs[i].task2 == state.entries[0]) {
//newest penalty wins                        if (cs[i].penalty > returned) {
                    	returned += cs[i].penalty;
                        //}
                    }
                }
            }
        }
        
            // For all constraints
            for (int i = 0; i < cs.length; i++) {
                // If task2 in cs == task assigned to mach
                if (cs[i].task2 == state.entries[mach]) {
                    // If task 1 in cs == task assigned to mach-1
                    if (cs[i].task1 == state.entries[mach-1]) {
                        // Return penalty
//newest penalty wins                         if (cs[i].penalty > returned) {
                    	returned += cs[i].penalty;
                        //}
                    }
                }
            }
        
        return returned;
    }
    
    
    /*
     * ASSUMING INNER ARRAYS ARE TASKS OUTER ARRAYS ARE MACH'S!
     * Returns penalty value associated with the given machine at the given task
     * 
     * @param int mach:     newest filled index in the state given (if state is a, b, x, ... then mach should be 1 to represent b)
     * @param State state:  given state to check constraints on
     * @param constraints:  Constraints object
     */
    public int checkMP(int mach, State state) {
        if (state.entries[mach] == 'X') return 0;
        return machinePenalties[mach][state.entries[mach] - 65];
    }
    
    
    /*
     * Checks all hard constraints and returns true if all are passed, returns false if a hard constraint is broken
     * 
     * @param int mach:     newest filled index in the state given (if state is a, b, x, ... then mach should be 1 to represent b)
     * @param State state:  given state to check constraints on
     * @param constraints:  Constraints object
     */
    public boolean checkHardConstraints(int mach, State state) {
        if (mach == -1) return true;
        // Runs checkFPA with mach, state, and the constraints FPA linked list which is converted to a 2d char array
        if (checkFPA(mach, state, forcedPartialAssn.toArray(new char[0][0])) == false) return false;
//        System.out.println("returned true");
        // Runs checkFM with mach, state, and the constraints FM linked list which is converted to a 2d char array
        if (checkFM(mach, state, forbiddenMach.toArray(new char[0][0])) == false) return false;
        // Runs checkTNT with mach, state, and the constraints TNT linked list which is converted to a 2d char array
        if (checkTNT(mach, state, tooNearTasks.toArray(new char[0][0])) == false) return false;
        return true;
    }
    
    
    /*
     * The reason why this one is a little weird is because we want to assume current entry is task 2 and check "behind"
     * it for task one.  However, if the mach is 0 then we check nothing, and if the mach is 7 then we don't check "behind",
     * instead we wrap around and check 0.
     * 
     * @param int mach:     newest filled index in the state given (if state is a, b, x, ... then mach should be 1 to represent b)
     * @param State state:  given state to check constraints on
     * @param char[][] cs:  2D array of only Too-Near Task constraints
     */
    public boolean checkTNT(int mach, State state, char[][] cs) {
        // Check nothing because there is only one task assigned to a machine in this state
        if (mach == 0) return true;
        // For each constraint
        for (int constraint = 0; constraint < cs.length; constraint++) {
            if (mach == 7) {
                // If the task assigned to mach is task1 in the constraint
                if (state.entries[7] == cs[constraint][0]) {
                    // if the task assigned to mach + 1 (in this case entry 0) is equal to task 2 in the constraint
                    if (state.entries[0] == cs[constraint][1]) {
                        return false;
                    }
                }
            }
            // Mach does not equal 7 or 0, so we assume mach == task 2 and check backward
            if (state.entries[mach] == cs[constraint][1]) {
            	// if entry behind mach == task 1
            	if (state.entries[mach-1] == cs[constraint][0]) {
            		return false;
            	}
            }
        }
        // (If mach == 7 then if mach == task 1 then entry[0] != task 2) and (if mach == task 2 then mach-1 != task 1)
        // Basically, task 1 and 2 are not assigned to mach i and mach i + 1 respectively (or mach i and mach 0 if mach == 7)
        return true;
    }
    
    
    /*
     * @param int mach:     newest filled index in the state given (if state is a, b, x, ... then mach should be 1 to represent b)
     * @param State state:  given state to check constraints on
     * @param char[][] cs:  2D array of only Forced Partial Assignment constraints
     */ 
    public boolean checkFPA(int mach, State state, char[][] cs) {
        // Checks each constraint in cs
        for (int constraint = 0; constraint < cs.length; constraint++) {
            // If machine we're checking is equal to machine in the constraint
            if ((mach + 48) == cs[constraint][0]) {
                // If the task assigned to the machine does not equal the task that is supposed to be assigned to that machine
                if (state.entries[mach] != cs[constraint][1]) {
                    return false;
                }
            }
        }
        return true;
    }

    
    /*
     * @param int mach:     newest filled index in the state given (if state is a, b, x, ... then mach should be 1 to represent b)
     * @param State state:  given state to check constraints on
     * @param char[][] cs:  2D array of only Forbidden Machine constraints
     */
    public boolean checkFM(int mach, State state, char[][] cs) {
        // For each constraint in cs
        for (int constraint = 0; constraint < cs.length; constraint++) {
            // If the given machine is equal to the machine in the constraint
            if ((mach + 48) == cs[constraint][0]) {
                // If the forbidden task is assigned to that machine
                if (state.entries[mach] == cs[constraint][1]) {
                    return false;
                }
            }
        }
        return true;
    }
}
