
public class StaticFuncs {
    public static hardConstraints
    
    public static boolean checkValid(State state, cs) {
        
    }
    
    public static boolean checkFPA(int mach, State state, char[][] cs) {
        for(int constraint = 0; constraint < cs.length; constraint++) {
            if(mach == cs[constraint][0]) {
                if(state.entries[mach] != cs[constraint][1]) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public static boolean checkFM(int mach, State state, char[][] cs) {
        for(int constraint = 0; constraint < cs.length; constraint++) {
            if(mach == cs[constraint][0]) {
                if(state.entries[mach] == cs[constraint][1]) {
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
