package assignment.java.main;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;

public class Entry {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println(
                    "This program accepts exactly 2 command line arguments.");
            System.exit(0);
        }
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(args[1], "UTF16");

            Constraints c = fileIO.fileIO(args[0]);
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
                returned = "Solution " + min.entries[0] + "\u2081"
                        + min.entries[1] + "\u2082" + min.entries[2] + "\u2083"
                        + min.entries[3] + "\u2084" + min.entries[4] + "\u2085"
                        + min.entries[5] + "\u2086" + min.entries[6] + "\u2087"
                        + min.entries[7] + "\u2088" + "; Quality: "
                        + min.penalty;
            }
            writer.println(returned);
            writer.close();
        } catch (FileNotFoundException e) {
//            e.printStackTrace();
            System.err.println(
                    "An error was encountered trying to create a file with the name: "
                            + args[1]);
            System.exit(0);
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
//            e.printStackTrace();
            System.err.println(
                    "An error was encountered trying to create a file with the name: "
                            + args[1]);
            System.exit(0);
        }
    }
}
