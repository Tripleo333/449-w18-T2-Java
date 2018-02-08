//package assignment.java.main;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class Entry {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println(
                    "This program accepts exactly 2 command line arguments.");
            System.exit(0);
        }
        try {
            PrintWriter writer = new PrintWriter(args[1]);
            Constraints c = fileIO.fileIO(args[0], writer);
            State state = new State();
            PossibilityTree pt = new PossibilityTree(
                    new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'}, c);
            pt.Branch(-1, state, pt.tasks);
            State min = pt.minPenalty;
            String returned;

            if (min == null) {
                returned = "No valid solution possible!";
            }

            else {
                returned = "Solution " + min.entries[0] + min.entries[1]
                        + min.entries[2] + min.entries[3] + min.entries[4]
                        + min.entries[5] + min.entries[6] + min.entries[7]
                        + "; Quality: " + min.penalty;
            }
            writer.println(returned);
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.err.println(
                    "An error was encountered trying to create a file with the name: "
                            + args[1]);
            System.exit(0);
        }
    }
}
