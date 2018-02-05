import java.util.ArrayList;
import java.util.Arrays;

public class Entry {
    public static void main(String[] args) {
        Constraints c = fileIO.fileIO(args[0]);
        State state = new State();
        PossibilityTree pt = new PossibilityTree(new char[] {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'});
        pt.Branch(-1, state, pt.tasks);
        State min = pt.minPenalty;
        String returned;
       
        for (int x = 0; x < c.machinePenalties.length; x++) {
            for (int y = 0; y < c.machinePenalties[x].length; y++) {
                System.out.print(c.machinePenalties[x][y] + " ");
            }
            System.out.println("");
        }
        if (min == null) {
            returned = "No valid solution possible!";
        }
        
        else {
            // PRINT ENTRIES
            System.out.println(min.entries);
            System.out.println(pt.currentMinPenalty);
        }
    }
}
