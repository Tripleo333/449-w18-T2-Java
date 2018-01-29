package assignment.java.main;

import java.util.LinkedList;

public class StaticFuncs {   
    
    
    public static int checkTNP(int mach, State state, Triplet[] cs) {
        if (mach == 0) {
            return 0;
        }
        
        if (mach == 7) {
            for (int i = 0; i < cs.length; i++) {
                // If task 2 in constraint == task in our mach:
                if (cs[i].task == state.entries[mach]) {
                    
                }
            }
        }
    }
    
    /*
     * ASSUMING INNER ARRAYS ARE TASKS OUTER ARRAYS ARE MACH'S!
     * Returns penalty value associated with the given machine at the given task
     * 
     * @param int mach:     newest filled index in the state given (if state is a, b, x, ... then mach should be 1 to represent b)
     * @param State state:  given state to check constraints on
     * @param constraints:  Constraints object
     */
    public static int checkMP(int mach, State state, int[][] cs) {
        return cs[mach][state.entries[mach]];
    }
    
    /*
     * Checks all hard constraints and returns true if all are passed, returns false if a hard constraint is broken
     * 
     * @param int mach:     newest filled index in the state given (if state is a, b, x, ... then mach should be 1 to represent b)
     * @param State state:  given state to check constraints on
     * @param constraints:  Constraints object
     */
    public static boolean checkHardConstraints(int mach, State state, Constraints constraints) {
        // Runs checkFPA with mach, state, and the constraints FPA linked list which is converted to a 2d char array
        if (checkFPA(mach, state, constraints.forcedPartialAssn.toArray(new char[0][0])) == false) return false;
        // Runs checkFM with mach, state, and the constraints FM linked list which is converted to a 2d char array
        if (checkFM(mach, state, constraints.forbiddenMach.toArray(new char[0][0])) == false) return false;
        // Runs checkTNT with mach, state, and the constraints TNT linked list which is converted to a 2d char array
        if (checkTNT(mach, state, constraints.tooNearTasks.toArray(new char[0][0])) == false) return false;
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
    public static boolean checkTNT(int mach, State state, char[][] cs) {
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
            else if (state.entries[mach] == cs[constraint][1]) {
                // if mach == task2
                if (state.entries[mach] == cs[constraint][1]) {
                    // if entry behind mach == task 1
                    if (state.entries[mach-1] == cs[constraint][0]) {
                        return false;
                    }
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
    public static boolean checkFPA(int mach, State state, char[][] cs) {
        // Checks each constraint in cs
        for (int constraint = 0; constraint < cs.length; constraint++) {
            // If machine we're checking is equal to machine in the constraint
            if (mach == cs[constraint][0]) {
                // If the task asssigned to the machine does not equal the task that is supposed to be assigned to that machine
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
    public static boolean checkFM(int mach, State state, char[][] cs) {
        // For each constraint in cs
        for (int constraint = 0; constraint < cs.length; constraint++) {
            // If the given machine is equal to the machine in the constraint
            if (mach == cs[constraint][0]) {
                // If the forbidden task is assigned to that machine
                if (state.entries[mach] == cs[constraint][1]) {
                    return false;
                }
            }
        }
        return true;
    }
    
    /*public static boolean checkFPA(State state, char[][] cs) {
        // For each machine in the state:
        for(int mach = 0; mach < state.entries.length; mach++) {
            // For each constraint in our set of constraints:
            for(int constraint = 0; constraint < cs.length; constraint++) {
                // If the machine we're currently looking at is equal to the machine number in the constraint:
                if (mach == cs[constraint][0]) {
                    // If the task at the machine is equal to the constraints task:
                    if (state.entries[mach] == cs[constraint][1]) {
                        // This means that the given state 
                        return false;
                    }
                }
            }
        }
        return true;
    }
    */
}
