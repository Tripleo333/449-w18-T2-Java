package assignment.java.main;

import java.util.ArrayList;
import java.util.Arrays;

public class Entry {
    public static void main(String[] args) {
        Constraints c = fileIO.fileIO(args[0]);
        State state = new State();
        PossibilityTree pt = new PossibilityTree(new char[] {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'}, c);
        pt.Branch(-1, state, pt.tasks);
        State min = pt.minPenalty;
        String returned;

        if (min == null) {
            System.out.println("No valid solution possible!");
        }
        
        else {
            // PRINT ENTRIES
            System.out.println(min.entries);
            System.out.println(pt.currentMinPenalty);
        }
    }
}
