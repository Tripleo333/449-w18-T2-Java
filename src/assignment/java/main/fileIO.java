package assignment.java.main;
/**
 * 
 */
import java.io.File;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class fileIO {

    private static boolean validFile = true;
    private static int lineCounter = 0;

    @SuppressWarnings("resource")
    public static Constraints fileIO(String str) {

        Constraints c = new Constraints();
        try {
//            System.out.println("Enter the file name with the extension: ");

            File file = new File(str);
            Scanner input = new Scanner(file);
            String name = "";
            String line = "";

            // C:\Users\Admin\git\449-w18-T2-Java\src\assignment\java\main\test.txt
            // /home/uga/joel.lowe/workspace/449-w18-T2-Java-master/src/assignment/java/main/test.txt

            if (input.hasNextLine()) {
                line = input.nextLine();
            } else {
                System.out.println("Invalid file.");
                return null;
            }
            lineCounter++;
            Pattern PnameNL = Pattern.compile("Name:");// no need to check for
                                                       // newline because
                                                       // input.newline eats \n
            Matcher MnameNL = PnameNL.matcher(line);
            if (MnameNL.find() && validFile) {
                if (input.hasNextLine()) {
                    name = input.nextLine();// should contain a string for name
                    lineCounter++;
                } else {
                    System.out.println("Invalid file.");
                    System.exit(0);
                }
                line = input.nextLine();// reads new line
                lineCounter++;

                if (!line.matches("")) {// checks that it infact reads new line
                    validFile = false;
                    System.out.println("invalid line: " + lineCounter);
                    System.exit(0);
                }
                System.out.println("contents of name: " + name);
                line = input.nextLine(); // line should be "forced partial
                                         // assignment:\n"
                lineCounter++;
            } else {
                System.out.println("Invalid input on line " + lineCounter);

            }

            if (line.matches("forced partial assignment:") && validFile) {
                // System.out.println("in forced partial assignment if
                // statment:\n");
                input = fileIO.forcedPartialAssignment(input, c);
                if (!validFile) {
                    System.out.println(
                            "Invalid file input on line " + lineCounter);
                    System.exit(0);
                }
                line = input.nextLine(); // should contain "forbidden
                                         // machine:\n"
                lineCounter++;
            } else {
                System.out.println("Invalid input on line: " + lineCounter);
                System.exit(0);
            }
            if (line.matches("forbidden machine:") && validFile) {
                System.out.println("entered forbidden machine if:\n");
                input = fileIO.forbiddenMachine(input, c);
                if (!validFile) {
                    System.out.println(
                            "Invalid file input on line " + lineCounter);
                    System.exit(0);
                }
                if (input.hasNextLine()) {
                    line = input.nextLine();// should contain "too-near
                                            // tasks:\n"
                    lineCounter++;
                } else {

                }
            } else {
                System.out.println("Invalid input on line: " + lineCounter);
                System.exit(0);
            }

            if (line.matches("too-near tasks:") && validFile) {
                input = fileIO.tooNearTasks(input, c);
                if (!validFile) {
                    System.out.println(
                            "Invalid file input on line " + lineCounter);
                    System.exit(0);
                }
                if (input.hasNextLine()) {
                    line = input.nextLine();// should contain "machine
                                            // penalties:"
                    lineCounter++;
                } else {
                    System.out.println("invalid file, line " + lineCounter);
                    validFile = false;
                    System.exit(0);
                }

            } else {
                System.out.println("Invalid input on line: " + lineCounter);
                System.exit(0);
            }

            if (line.matches("machine penalties:") && validFile) {
                System.out.println("\n\nmachine penalties: if statement");
                input = fileIO.machinePenalties(input, c);
                if (!validFile) {
                    System.out.println(
                            "Invalid file input on line " + lineCounter);
                    System.exit(0);
                }
                if (input.hasNextLine()) {
                    line = input.nextLine();
                    lineCounter++;
                } else {
                    System.out.println("invalid file, line " + lineCounter);
                    validFile = false;
                    System.exit(0);

                }
            } else {
                System.out.println("Invalid input on line: " + lineCounter);
                System.exit(0);
            }

            Pattern PTNP = Pattern.compile("too-near penalities|too-near penalties");
            Matcher MTNP = PTNP.matcher(line);
            if (MTNP.find() && validFile) {
                System.out.println("\n\ntoo-near penalties if statement");
                if(input.hasNextLine()) {
	                input = fileIO.tooNearPenalties(input, c);
	                if (!validFile) {
	                    System.out.println(
	                            "Invalid file input on line " + lineCounter);
	                    System.exit(0);
	                }
                }
            } else {
                System.out.println("Invalid input on line: " + lineCounter);
                System.exit(0);
            }
            input.close();
            System.out.println("input scanner is closed and everything was read in fine.");
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("could not open file");
            System.exit(0);
        }

        return c;
    }

    private static Scanner machinePenalties(Scanner input, Constraints c) {
        int pen = 0;
        for (int i = 0; i < 8; i++) {
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
            for (int x = 0; x < 8; x++) {
                c.addMachPenalties(i, x, penaltiesIntArr[x]);
            }
        }
        System.out.println("number of lines read: " + lineCounter);

        // reading newline and the line after that which should contain
        // "too-near penalties"
        if (!input.hasNextLine()) {
            System.out.println("Invalid input line " + (++lineCounter));
            validFile = false;
            System.exit(0);
        } else {
            String line = input.nextLine();
            lineCounter++;
            Pattern PMachPenNL = Pattern.compile("");
            Matcher MMachPenNL = PMachPenNL.matcher(line);
            if (!MMachPenNL.find()) {
                validFile = false;
                System.out.println("Invalid input line " + lineCounter);
                validFile = false;
                System.exit(0);
            }
        }
        if (!input.hasNextLine()) {
            System.out.println("Invalid input line " + (lineCounter + 1));
            validFile = false;
            System.exit(0);
        }
        return input;
    }

    private static Scanner tooNearPenalties(Scanner input, Constraints c) {
        int penalty;
        String line = input.nextLine();
        lineCounter++;
        Pattern p = Pattern.compile("([A-H],[A-H],[\\d+])");
        Matcher m = p.matcher(line);
        while (m.find()) {
            char[] lineArray = line.toCharArray();
            char[] TMPair = {lineArray[1], lineArray[3]};
//            System.out.println(
//                    "contents of line array: " + Arrays.toString(lineArray));
//            System.out
//                    .println("contents of TMPair: " + Arrays.toString(TMPair));
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
        Pattern pNL = Pattern.compile("");
        Matcher mNL = pNL.matcher(line);
        if (!mNL.find()) {
            validFile = false;
            System.out.println("problem encountered at line: " + lineCounter);
            System.exit(0);
        }

        // debugging
        /*
         * for (int i = 0; i < c.tooNearPenalties.size(); i++) {
         * System.out.println(
         * "attempting to print out contents of c.tooNearPenalties" +
         * c.tooNearPenalties); }
         */
        return input;
    }

    private static void mFind(String line, String regex, Scanner input, Constraints c) {
        lineCounter++;
        System.out.println(line);
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(line);
        while (m.find()) {
            char[] lineArray = line.toCharArray();
            char[] TMPair = {lineArray[1], lineArray[3]};
            c.addConstraintPair(3, TMPair);
            line = input.nextLine();
            lineCounter++;
            System.out.println("pair accepted. Line read: " + lineCounter);
            m = p.matcher(line);
        }
        
        Pattern pNL = Pattern.compile("");
        Matcher mNL = pNL.matcher(line);
        if (!mNL.find()) {
            validFile = false;
            System.out.println("problem encountered at line: " + lineCounter);
            System.exit(0);
        }
    }
    
    private static Scanner tooNearTasks(Scanner input, Constraints c) {
        System.out.println("in too near tasks function");
        String line = input.nextLine();
        mFind(line, "[(][A-H][,][A-H][)]", input, c);
        /*
         * for (int i = 0; i < c.tooNearTasks.size(); i++) {
         * System.out.println(c.tooNearTasks.get(i)); }
         */
        return input;
    }

    private static Scanner forbiddenMachine(Scanner input, Constraints c) {
        String line = input.nextLine();
        mFind(line, "[(][0-7][,][A-H][)]", input, c);
        // TODO: debog
        //	check if different from fpa pairs
        for (int i = 0; i < c.forbiddenMach.size(); i++) {
            System.out.println(c.forbiddenMach.get(i));
        }
        System.out.println("number of lines read: " + lineCounter);
        return input;

    }

    private static Scanner forcedPartialAssignment(Scanner input,
            Constraints c) {
        String line = input.nextLine();
        mFind(line, "[(][0-7][,][A-H][)]", input, c);
        // TODO: DEBUG
        for (int i = 0; i < c.forcedPartialAssn.size(); i++) {
            System.out.println(c.forcedPartialAssn.get(i));
        }
        System.out.println("number of lines read: " + lineCounter);
        return input;
    }
}