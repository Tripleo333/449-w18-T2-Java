//package assignment.java.main;

import java.util.ArrayList;
import java.util.Arrays;

public class Entry {
    public static void main(String[] args) {
        Constraints c = fileIO.fileIO(args[0]);
        State state = new State();
        PossibilityTree pt = new PossibilityTree(new char[] {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'}, c);
        pt.Branch(-1, state, pt.tasks);
        State min = pt.minPenalty;
        String returned = "";

        if (min == null) {
            returned = "No valid solution possible!";
        }
        
        else {
            returned = "Solution " + min.entries[0] + min.entries[1] + min.entries[2] + min.entries[3] + min.entries[4] + min.entries[5] + min.entries[6] + min.entries[7] + "; Quality: " + min.penalty;
        }
        System.out.println(returned);
    }
}
