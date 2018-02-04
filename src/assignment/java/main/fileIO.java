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
    public static void main(String[] args) {
        fileIO();

    }

    private static boolean validFile = true;
    private static int lineCounter = 0;

    public static void fileIO() {

        try {
            System.out.println("Enter the file name with the extension: ");
            Scanner input = new Scanner(System.in);

            File file = new File(input.nextLine());
            input = new Scanner(file);
            String name = "";
            int counter = 0;
            String garbage = "";
            String line = input.nextLine();
            lineCounter++;

            // C:\Users\Admin\git\449-w18-T2-Java\src\assignment\java\main\test.txt
            // /home/uga/joel.lowe/workspace/449-w18-T2-Java-master/src/assignment/java/main/test.txt
            fileIO f = new fileIO();
            while (input.hasNextLine()) {

                if (line.matches("Name:(.*)")) {
                    name = input.nextLine();
                    lineCounter++;
                    line = input.nextLine();
                    lineCounter++;
                    System.out.println("contents of name: " + name);

                } else if (line.equals("\n")) {
                    garbage = input.nextLine();
                    lineCounter++;
                } else if (line.matches("forced partial assignment:(.*)")) {
                    System.out.println("entered second if");

                    input = f.forcedPartialAssignment(input);
                    if (!validFile) {
                        System.out.println(
                                "Invalid file input on line " + lineCounter);
                        System.exit(0);
                    }
                } else if (line.matches("forbidden machine:(.*)")) {

                    System.out.println("entered forbidden machine elseif");
                    input = f.forbiddenMachine(input);
                    if (!validFile) {
                        System.out.println(
                                "Invalid file input on line " + lineCounter);
                        System.exit(0);
                    }
                } else if (line.matches("too-near tasks:(.*)")) {
                    System.out.println("too-near tasks: elseif");
                    input = f.tooNearTasks(input);
                } else if (line.matches("machine penalties:(.*)")) {
                    System.out.println("too-near tasks: elseif");
                    input = f.machinePenalties(input);
                }
                line = input.nextLine();
                lineCounter++;
            }
            input.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    private Scanner machinePenalties(Scanner input) {
        int pen = 0;
        Constraints c = new Constraints();
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
            int [] penaltiesIntArr = new int[8];
            
            for (int y = 0; y < 8; y++) {
                try {
                    int temp = Integer.parseInt(penaltiesStrArr[y]);
                    penaltiesIntArr[y] = temp;

                    if (penaltiesIntArr[y] < 0) {
                        System.out.println("Invalid number in line: " + lineCounter);
                        System.exit(0);
                    }
                } catch (NumberFormatException e) {
                    System.out.println(
                            "invalid number input line: " + lineCounter);
                    System.exit(0);
                }
            }
/*
 * KNOWN ISSUE:
 * I was unable to use the function addMachPenalties in the constraints file, so I was never able to add the matrix of penalties 
 * all the code is here though so just un-comment the line in the following forloop and everything should be fine.
 * 
 */
            for (int x = 0; x < 8; x++) {
                //c.addMachPenalties(i, x , penaltiesIntArr[x]);
            }
        }
        //debug
        System.out.println(Arrays.deepToString(c.machinePenalties));
        return input;
    }

    private Scanner tooNearTasks(Scanner input) {
        System.out.println("Beginning of toonear tasks function");
        String line = input.nextLine();
        lineCounter++;
        System.out.println(line);
        Constraints c = new Constraints();
        // Matcher m = new Matcher("([1-8],[A-H])(.*)");
        Pattern p = Pattern.compile("(.*)((.*)[A-B](.*),(.*)[A-H](.*))(.*)");
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
        }

        for (int i = 0; i < c.tooNearTasks.size(); i++) {
            System.out.println(c.tooNearTasks.get(i));
        }
        System.out.println("number of lines read: " + lineCounter);
        return input;

    }

    private Scanner forbiddenMachine(Scanner input) {
        // String pattern = "";
        String line = input.nextLine();
        lineCounter++;
        System.out.println(line);
        Constraints c = new Constraints();
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

    private Scanner forcedPartialAssignment(Scanner input) {
        // String pattern = "";
        String line = input.nextLine();
        lineCounter++;
        System.out.println(line);
        Constraints c = new Constraints();
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