package assignment.java.main;
/**
 * 
 */
import java.io.File;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class fileIO {

    // this is a preattempt at opening a file
    // final version should return a buffered reader or scanner (havn't decided
    // yet)
    // I'll make another function below that parses it and adds it to their
    // respective places

    /*
     * available tools for string manipulation: contains, indexof
     * 
     * regexes: looking for a word that is 2-20 characters in length
     * [A-Za-z]{2,20} or \\w{2,20}
     * 
     * Pattern checkRegex = Pattern.compile(theRegex); Matcher regexmatcher =
     * checkRegex.matcher(str2Check);
     * 
     * while(regexMatcher.find()){ if (regexMatcher.group().length() != 0){
     * system.out.println(regexMatcher.group().trim());
     * 
     * 
     * }
     * 
     * }
     * 
     */

    private static boolean validFile = true;
    private static int lineCounter = 0;

    public static Constraints fileIO(String str) {
        
        Constraints c = new Constraints();
        try {
            System.out.println("Enter the file name with the extension: ");
            
            File file = new File(str);
            Scanner input = new Scanner(file);
/*          
            Scanner input = new Scanner(System.in);

            File file = new File(input.nextLine());
            input = new Scanner(file);
*/          
            String name = "";
            int counter = 0;
            String garbage = "";
            String line;
            lineCounter++;

            // C:\Users\Admin\git\449-w18-T2-Java\src\assignment\java\main\test.txt
            // /home/uga/joel.lowe/workspace/449-w18-T2-Java-master/src/assignment/java/main/test.txt
            while (input.hasNextLine()) {
                line = input.nextLine();

                if (line.matches("Name:(.*)")) {
                    name = input.nextLine();
                    lineCounter++;
                    line = input.nextLine();
                    lineCounter++;
                    System.out.println("contents of name: " + name);

                } else if (line.matches("\n")) {
                    garbage = input.nextLine();
                    lineCounter++;
                    System.out.print("line read by NL elseif: " + lineCounter);
                } else if (line.matches("forced partial assignment:(.*)")) {
                    System.out.println("entered second if");

                    input = fileIO.forcedPartialAssignment(input, c);
                    if (!validFile) {
                        System.out.println(
                                "Invalid file input on line " + lineCounter);
                        System.exit(0);
                    }
                } else if (line.matches("forbidden machine:(.*)")) {

                    System.out.println("entered forbidden machine elseif");
                    input = fileIO.forbiddenMachine(input, c);
                    if (!validFile) {
                        System.out.println(
                                "Invalid file input on line " + lineCounter);
                        System.exit(0);
                    }
                } else if (line.matches("too-near tasks:(.*)")) {
                    System.out.println("too-near tasks: elseif");
                    input = fileIO.tooNearTasks(input, c);
                    if (!validFile) {
                        System.out.println(
                                "Invalid file input on line " + lineCounter);
                        System.exit(0);
                    }
                } else if (line.matches("machine penalties:(.*)")) {
                    System.out.println("machine penalties: elseif");
                    input = fileIO.machinePenalties(input, c);
                    if (!validFile) {
                        System.out.println(
                                "Invalid file input on line " + lineCounter);
                        System.exit(0);
                    }
                } else if (line.matches("too-near penalities")) {
                    System.out.println("too-near penalties elseif");
                    input = fileIO.tooNearPenalties(input, c);
                    if (!validFile) {
                        System.out.println(
                                "Invalid file input on line " + lineCounter);
                        System.exit(0);
                    }
                }
                if (input.hasNextLine()) {
                    line = input.nextLine();
                    lineCounter++;
                }
            }
            input.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        return c;
    }

    private static Scanner machinePenalties(Scanner input, Constraints c) {
        int pen = 0;
        for (int i = 1; i <= 8; i++) {
            String line = input.nextLine();
            lineCounter++;
            String[] penaltiesStrArr = line.split(" ");

            // the following is checking that its all ints and only 8 ints
            if (penaltiesStrArr.length != 8) {
                validFile = false;
                System.out.println(
                        "Invalid number of elements in line: " + lineCounter);
                System.exit(0);
            }
            int[] penaltiesIntArr = new int[8];

            for (int y = 0; y < 8; y++) {
                try {
                    int temp = Integer.parseInt(penaltiesStrArr[y]);
                    penaltiesIntArr[y] = temp;

                    if (penaltiesIntArr[y] < 0) {
                        System.out.println(
                                "Invalid number in line: " + lineCounter);
                        System.exit(0);
                    }
                } catch (NumberFormatException e) {
                    System.out.println(
                            "invalid number input line: " + lineCounter);
                    System.exit(0);
                }
            }
            /*
             * KNOWN ISSUE: I was unable to use the function addMachPenalties in
             * the constraints file, so I was never able to add the matrix of
             * penalties all the code is here though so just un-comment the line
             * in the following forloop and everything should be fine.
             * (probably) Fixed
             */
            for (int x = 0; x < 8; x++) {
                c.addMachPenalties(i, x , penaltiesIntArr[x]);
            }
        }
        System.out.println("number of lines read: " + lineCounter);

        // debug
        // System.out.println(Arrays.deepToString(c.machinePenalties));
        return input;
    }

    private static Scanner tooNearPenalties(Scanner input, Constraints c) {
        int penalty;
        String line = input.nextLine();
        lineCounter++;
        Pattern p = Pattern.compile(
                "(.*)((.*)[A-H](.*),(.*)[A-H](.*),(.*)[\\d+](.*))(.*)");
        Matcher m = p.matcher(line);
        while (m.find()) {
            line = line.replaceAll("\\s", "");
            char[] lineArray = line.toCharArray();
            char[] TMPair = {lineArray[1], lineArray[3]};
            System.out.println(
                    "contents of line array: " + Arrays.toString(lineArray));
            System.out
                    .println("contents of TMPair: " + Arrays.toString(TMPair));
            String numOnly = line.replaceAll("[^0-9]", "");
            penalty = Integer.parseInt(numOnly);
            if (penalty < 0) {
                validFile = false;
                System.out.println(
                        "invalid penalty value at line: " + lineCounter);
                System.exit(0);
            }
            c.addTooNearPenalties(TMPair, penalty);
        }
        Pattern pNL = Pattern.compile("(.*)");
        Matcher mNL = pNL.matcher(line);
        if (!mNL.find()) {
            validFile = false;
            System.out.println("problem encountered at line: " + lineCounter);
            System.exit(0);
        }

        // debugging
        /*
        for (int i = 0; i < c.tooNearPenalties.size(); i++) {
            System.out.println(
                    "attempting to print out contents of c.tooNearPenalties"
                            + c.tooNearPenalties);
        }
        */
        return input;
    }

    private static Scanner tooNearTasks(Scanner input, Constraints c) {
        // System.out.println("Beginning of toonear tasks function");
        String line = input.nextLine();
        lineCounter++;
        System.out.println(line);
        // Matcher m = new Matcher("([1-8],[A-H])(.*)");
        Pattern p = Pattern.compile("(.*)((.*)[A-H](.*),(.*)[A-H](.*))(.*)");
        Matcher m = p.matcher(line);
        while (m.find()) {
            line = line.replaceAll("\\s", "");
            char[] lineArray = line.toCharArray();
            char[] TMPair = {lineArray[1], lineArray[3]};
            c.addConstraintPair(3, TMPair);
            line = input.nextLine();
            lineCounter++;
            System.out.println("pair accepted. Line read: " + lineCounter);
            m = p.matcher(line);
        }

        Pattern pNL = Pattern.compile("(.*)");
        Matcher mNL = pNL.matcher(line);
        if (!mNL.find()) {
            validFile = false;
            System.out.println("problem encountered at line: " + lineCounter);
            System.exit(0);
        }

        for (int i = 0; i < c.tooNearTasks.size(); i++) {
            System.out.println(c.tooNearTasks.get(i));
        }
        System.out.println("number of lines read: " + lineCounter);
        return input;

    }

    private static Scanner forbiddenMachine(Scanner input, Constraints c) {
        // String pattern = "";
        String line = input.nextLine();
        lineCounter++;
        System.out.println(line);
        // Matcher m = new Matcher("([1-8],[A-H])(.*)");
        Pattern p = Pattern.compile("(.*)((.*)[1-8](.*),(.*)[A-H](.*))(.*)");
        Matcher m = p.matcher(line);
        while (m.find()) {
            line = line.replaceAll("\\s", "");
            char[] lineArray = line.toCharArray();
            char[] TMPair = {lineArray[1], lineArray[3]};
            c.addConstraintPair(2, TMPair);
            line = input.nextLine();
            lineCounter++;
            System.out.println("pair accepted. Line read: " + lineCounter);
            m = p.matcher(line);
        }

        Pattern pNL = Pattern.compile("(.*)");
        Matcher mNL = pNL.matcher(line);
        if (!mNL.find()) {
            validFile = false;
        }

        for (int i = 0; i < c.forbiddenMach.size(); i++) {
            System.out.println(c.forbiddenMach.get(i));
        }
        System.out.println("number of lines read: " + lineCounter);
        return input;

    }

    private static Scanner forcedPartialAssignment(Scanner input, Constraints c) {
        // String pattern = "";
        String line = input.nextLine();
        lineCounter++;
        System.out.println(line);
        // Matcher m = new Matcher("([1-8],[A-H])(.*)");
        Pattern p = Pattern.compile("(.*)((.*)[1-8](.*),(.*)[A-H](.*))(.*)");
        Matcher m = p.matcher(line);
        while (m.find()) {
            line = line.replaceAll("\\s", "");
            char[] lineArray = line.toCharArray();
            char[] TMPair = {lineArray[1], lineArray[3]};
            c.addConstraintPair(1, TMPair);
            line = input.nextLine();
            lineCounter++;
            System.out.println("pair accepted. Line read: " + lineCounter);
            m = p.matcher(line);
        }

        Pattern pNL = Pattern.compile("(.*)");
        Matcher mNL = pNL.matcher(line);
        if (!mNL.find()) {
            validFile = false;
        }

        for (int i = 0; i < c.forcedPartialAssn.size(); i++) {
            System.out.println(c.forcedPartialAssn.get(i));
        }
        System.out.println("number of lines read: " + lineCounter);
        return input;
    }
}