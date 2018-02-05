
public class Entry {
    public static void main(String[] args) {
        Constraints c = fileIO.fileIO(args[0]);
        State state = new State();
        PossibilityTree pt = new PossibilityTree(new char[] {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'});
        State min = pt.Branch(0, state, pt.tasks);
        String returned;
        if (min == null) {
            returned = "No valid solution possible!";
        }
        else {
            // PRINT ENTRIES
            System.out.println(min.entries);
        }
    }
}
