//package assignment.java.main;
/**
 * 
 */
import java.io.File;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class fileIO {

    private static boolean validFile = true;
    private static int lineCounter = 0;

    @SuppressWarnings("resource")
    public static Constraints fileIO(String str, PrintWriter writer) {
        validFile = true;
        lineCounter = 0;
        Constraints c = new Constraints();
        try {
            File file = new File(str);
            Scanner input = new Scanner(file);
            String name = "";
            String line = "";

            // C:\Users\Admin\git\449-w18-T2-Java\src\assignment\java\main\test.txt
            // /home/uga/joel.lowe/workspace/449-w18-T2-Java-master/src/assignment/java/main/test.txt

            if (input.hasNextLine()) {
                line = input.nextLine();
            } else {
                writer.println("Invalid file.");
                writer.close();
                System.exit(0);
                return null;
            }
            lineCounter++;
            if (line.equals("Name:") && validFile) {
                if (input.hasNextLine()) {
                    name = input.nextLine();// should contain a string for name
                    lineCounter++;
                } else {
                    writer.println("Invalid file.");
                    writer.close();
                    System.exit(0);
                    return c;
                }
                if (input.hasNextLine()) {
                    line = input.nextLine();// reads new line
                    lineCounter++;
                } else {
                    validFile = false;
                    writer.println("invalid file at line: " + lineCounter);
                    System.exit(0);
                }
                if (!line.matches("")) {// checks that it infact reads new line
                    validFile = false;
                    System.err.println("invalid file at line: " + lineCounter);
                    System.exit(0);
                }
                writer.println("contents of name: " + name);
                if (input.hasNextLine()) {
                    line = input.nextLine(); // line should be "forced partial
                                             // assignment:\n"
                    lineCounter++;
                } else {
                    validFile = false;
                    writer.println("invalid file at line: " + lineCounter);
                    writer.close();
                    System.exit(0);
                }
            } else {
                writer.println("Invalid input on line " + lineCounter);
                validFile = false;
                writer.close();
                System.exit(0);
            }

            if (line.equals("forced partial assignment:") && validFile) {
                input = fileIO.forcedPartialAssignment(input, c, writer);
                if (!validFile) {
                    writer.println("Invalid file input on line " + lineCounter);
                    writer.close();
                    System.exit(0);
                    return c;
                }
                line = input.nextLine(); // should contain "forbidden machine:"
                lineCounter++;
            } else {
                writer.println("Invalid input on line: " + lineCounter);
                writer.close();
                System.exit(0);
                return c;
            }

            if (line.equals("forbidden machine:") && validFile) {
                input = fileIO.forbiddenMachine(input, c, writer);
                if (!validFile) {
                    writer.println("Invalid file input on line " + lineCounter);
                    writer.close();
                    System.exit(0);
                    return c;
                }
                if (input.hasNextLine()) {
                    line = input.nextLine();// should contain "too-near
                                            // tasks:\n"
                    lineCounter++;
                } else {
                    writer.println(
                            "Invalid file input on line: " + lineCounter);
                    writer.close();
                    System.exit(0);
                }
            } else {
                writer.println("Invalid input on line: " + lineCounter);
                writer.close();
                System.exit(0);
                return c;
            }

            if (line.equals("too-near tasks:") && validFile) {
                input = fileIO.tooNearTasks(input, c, writer);
                if (!validFile) {
                    writer.println("Invalid file input on line " + lineCounter);
                    writer.close();
                    System.exit(0);
                    return c;
                }
                if (input.hasNextLine()) {
                    line = input.nextLine();// should contain "machine
                                            // penalties:"
                    lineCounter++;
                } else {
                    writer.println("invalid file, line " + lineCounter);
                    validFile = false;
                    writer.close();
                    System.exit(0);
                    return c;
                }

            } else {
                writer.println("Invalid input with line: " + lineCounter);
                validFile = false;
                writer.close();
                System.exit(0);
                return c;
            }

            if (line.equals("machine penalties:") && validFile) {
                input = fileIO.machinePenalties(input, c, writer);
                if (!validFile) {
                    System.err.println(
                            "Invalid file input on line " + lineCounter);
                    System.exit(0);
                    return c;
                }
                if (input.hasNextLine()) {
                    line = input.nextLine();
                    lineCounter++;
                } else {
                    writer.println("invalid file, line " + lineCounter);
                    writer.close();
                    validFile = false;
                    System.exit(0);
                    return c;

                }
            } else {
                writer.println("Invalid input on line: " + lineCounter);
                writer.close();
                validFile = false;
                System.exit(0);
                return c;
            }

            if (line.equals("too-near penalties:") && validFile) {
                if (input.hasNextLine()) {
                    input = fileIO.tooNearPenalties(input, c, writer);
                    if (!validFile) {
                        writer.println(
                                "Invalid file input on line " + lineCounter);
                        writer.close();
                        validFile = false;
                        System.exit(0);
                        return c;
                    }
                }
            } else {
                writer.println("Invalid input with line: " + lineCounter);
                writer.close();
                System.exit(0);
                return c;
            }
            input.close();
        } catch (Exception ex) {
            // ex.printStackTrace();
            writer.println("could not open input file");
            writer.close();
            System.exit(0);
            return c;
        }
        return c;
    }

    private static Scanner machinePenalties(Scanner input, Constraints c,
            PrintWriter writer) {
        int pen = 0;
        for (int i = 0; i < 8; i++) {
            String line = "";
            if (input.hasNextLine()) {
                line = input.nextLine();
                System.out.println(line);
                lineCounter++;
            } else {
                writer.println("Invalid input on line: " + lineCounter);
                writer.close();
                System.exit(0);
            }
            String[] penaltiesStrArr = line.split(" ");
            if (penaltiesStrArr.length != 8) {
                validFile = false;
                writer.println("Invalid number of elements in line: "
                        + lineCounter + "in machine penalties");
                System.exit(0);
                return input;
            }
            int[] penaltiesIntArr = new int[8];

            for (int y = 0; y < 8; y++) {
                try {
                    int temp = Integer.parseInt(penaltiesStrArr[y]);
                    penaltiesIntArr[y] = temp;
                    if (penaltiesIntArr[y] < 0) {
                        writer.println(
                                "Invalid number in line: " + lineCounter);
                        writer.close();
                        System.exit(0);
                        return input;
                    }
                } catch (NumberFormatException e) {
                    writer.println("invalid number input line: " + lineCounter);
                    writer.close();
                    System.exit(0);
                    return input;
                }
            }
            for (int x = 0; x < 8; x++) {
                c.addMachPenalties(i, x, penaltiesIntArr[x]);
            }
        }
        // reading newline and the line after that which should contain
        // "too-near penalties"
        if (!input.hasNextLine()) {
            writer.println("Invalid input line " + (++lineCounter));
            writer.close();
            validFile = false;
            System.exit(0);
            return input;
        } else {
            String line = "";
            if (input.hasNextLine()) {
                line = input.nextLine();
                System.out.println(line);
                lineCounter++;
            } else {
                writer.println("Invalid input line " + lineCounter);
                writer.close();
                System.exit(0);
            }
            /*
            if (line.equals("")) {
                validFile = false;
                writer.println("Invalid input line " + lineCounter);
                writer.close();
                System.exit(0);
                return input;
            }
            */
        }
        if (!input.hasNextLine()) {
            writer.println("Invalid input line " + (lineCounter + 1));
            writer.close();
            validFile = false;
            System.exit(0);
            return input;
        }
        return input;
    }

    private static Scanner tooNearPenalties(Scanner input, Constraints c,
            PrintWriter writer) {
        int penalty;
        String line = "";
        if (!input.hasNextLine()) {
            writer.print("Invalid input line " + lineCounter);
            writer.close();
            System.exit(0);
        } else {
            line = input.nextLine();
            lineCounter++;
        }
        /*
        Pattern p = Pattern.compile("[(][A-H][,][A-H][,][\\d+][)]");
        Matcher m = p.matcher(line);
        */

        mFind(line, "[A-H][,][A-H][,][\\d+]", input, c, 5, writer);
        /*
        while (m.find()) {
            System.out.println(line);
            char[] lineArray = line.toCharArray();
            char[] TMPair = {lineArray[1], lineArray[3]};
            String numOnly = line.replaceAll("[^0-9]", "");
            penalty = Integer.parseInt(numOnly);
            if (penalty < 0) {
                validFile = false;
                writer.println("invalid penalty value at line: " + lineCounter);
                writer.close();
                System.exit(0);
                return input;
            }
            c.addTooNearPenalties(TMPair, penalty);
        }
        */
        /*
        if (!line.equals("")) {
            validFile = false;
            System.err.println("problem encountered at line: " + lineCounter);
            System.exit(0);
            return input;
        }
        */
        System.out.println("haha");
        return input;
    }

    private static void mFind(String line, String regex, Scanner input,
            Constraints c, int type, PrintWriter writer) {
        lineCounter++;
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(line);
        while (m.find()) {
            char[] lineArray = line.toCharArray();
            int sub = lineArray[1] - 1;
            char[] TMPair = {(char) sub, lineArray[3]};
            if (type == 5) {
                String numOnly = line.replaceAll("[^0-9]", "");
                int penalty = Integer.parseInt(numOnly);
                c.addTooNearPenalties(TMPair, penalty);
            }
            else {
                c.addConstraintPair(type, TMPair);
            }
            
            if (input.hasNextLine()) {
                line = input.nextLine();
                lineCounter++;
            } 
            else if (type == 5 && !input.hasNextLine()){
                return;
            }
            else {
                writer.println("Problem encounted at line " + lineCounter);
                writer.close();
                System.exit(0);
            }
            m = p.matcher(line);
        }

        Pattern pNL = Pattern.compile("");
        Matcher mNL = pNL.matcher(line);
        if (!mNL.find()) {
            validFile = false;
            writer.println("problem encountered at line: " + lineCounter);
            writer.close();
            System.exit(0);
            return;
        }
    }

    private static Scanner tooNearTasks(Scanner input, Constraints c,
            PrintWriter writer) {
        String line = "";
        if (input.hasNextLine()) {
            line = input.nextLine();
        } else {
            writer.println("Invalid line at " + lineCounter);
            writer.close();
            System.exit(0);
        }
        mFind(line, "[(][A-H][,][A-H][)]", input, c, c.TOO_NEAR_TASKS, writer);
        return input;
    }

    private static Scanner forbiddenMachine(Scanner input, Constraints c,
            PrintWriter writer) {
        String line = "";
        if (input.hasNextLine()) {
            line = input.nextLine();
        } else {
            writer.println("Invalid line at " + lineCounter);
            writer.close();
            System.exit(0);
        }
        mFind(line, "[(][1-8][,][A-H][)]", input, c, c.FORBIDDEN_MACHINE,
                writer);
        return input;
    }

    private static Scanner forcedPartialAssignment(Scanner input, Constraints c,
            PrintWriter writer) {
        String line = "";
        if (input.hasNextLine()) {
            line = input.nextLine();
        } else {
            writer.println("Invalid line at " + lineCounter);
            writer.close();
            System.exit(0);
        }
        mFind(line, "[(][1-8][,][A-H][)]", input, c,
                c.FORCED_PARTIAL_ASSIGNMENT, writer);
        System.out.println("number of lines read: " + lineCounter);
        return input;
    }
}
