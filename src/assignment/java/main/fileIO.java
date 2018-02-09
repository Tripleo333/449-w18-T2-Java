package assignment.java.main;
/**
 * 
 */
import java.io.File;
//import java.io.PrintWriter;
//import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class fileIO {

    private static boolean validFile = true;
    private static int lineCounter = 0;
    private static String line = "";

    @SuppressWarnings("resource")
    public static Constraints fileIO(String str) {
        validFile = true;
        lineCounter = 0;
        Constraints c = new Constraints();
        try {
            File file = new File(str);
            Scanner input = new Scanner(file);
            String name = "";

            // C:\Users\Admin\git\449-w18-T2-Java\src\assignment\java\main\test.txt
            // /home/uga/joel.lowe/workspace/449-w18-T2-Java-master/src/assignment/java/main/test.txt

            if (input.hasNextLine()) {
                line = input.nextLine();
            } else {
                System.err.println(
                        "Error while parsing input file");
                System.exit(0);
                return null;
            }
            lineCounter++;
            
            while(line.trim().isEmpty()) {
                if (input.hasNextLine()) {
                    line = input.nextLine();
                    lineCounter++;
                }
                else {
                    System.err.println("Error while parsing input file");
                    System.exit(0);
                }
            }
            
            if (line.trim().equals("Name:") && validFile) {
                if (input.hasNextLine()) {
                    name = input.nextLine();// should contain a string for name
                    lineCounter++;
                    while(name.trim().isEmpty()) {
                        if (input.hasNextLine()) {
                            name = input.nextLine();
                            lineCounter++;
                        }
                        else {
                            System.err.println("Error while parsing input file");
                            System.exit(0);
                        }
                    }
                } else {
                    System.err.println(
                            "Error while parsing input file");
                    System.exit(0);
                    return c;
                }
                if (input.hasNextLine()) {
                    line = input.nextLine();// reads new line
                    lineCounter++;
                    // Reads new lines until we reach a non-empty line; should be "forced partial assignment:"
                    while(line.trim().isEmpty()) {
                        if (input.hasNextLine()) {
                            line = input.nextLine();
                            lineCounter++;
                        }
                        else {
                            System.err.println("Error while parsing input file");
                            System.exit(0);
                        }
                    }
                } else {
                    validFile = false;
                    System.err.println(
                            "Error while parsing input file");
                    System.exit(0);
                }
                /*
                if (input.hasNextLine()) {
                    // line should be "forced partial
                    // assignment:\n"
                    while(line.trim().isEmpty()) {
                        if (input.hasNextLine()) {
                            line = input.nextLine();
                            lineCounter++;
                        }
                        else {
                            System.err.println("Error while parsing input file");
                        }
                    }
                } else {
                    validFile = false;
                    System.err.println(
                            "Error while parsing input file");
                    System.exit(0);
                }
                */
            } else {
                System.err.println(
                        "Error while parsing input file");
                validFile = false;
                System.exit(0);
            }
            if (line.trim().equals("forced partial assignment:") && validFile) {
                input = fileIO.forcedPartialAssignment(input, c);
                if (!validFile) {
                    System.err.println(
                            "Error while parsing input file");
                    System.exit(0);
                    return c;
                }
                /*
                if (input.hasNextLine()) {
                    line = input.nextLine();
                    lineCounter++;

                }
                else {
                    System.err.println("Error while parsing input file");
                    System.exit(0);
                }
                // Goes until we find next non-empty line; should be "forbidden machine:"
                while(line.trim().isEmpty()) {
                    if (input.hasNextLine()) {
                        line = input.nextLine();
                        lineCounter++;
                    }
                    else {
                        System.err.println("Error while parsing input file");
                        System.exit(0);
                    }
                }
                */
                /*
                line = input.nextLine(); // should contain "forbidden machine:"
                lineCounter++;
                */
            } else {
                System.err.println(
                        "Error while parsing input file");
                System.exit(0);
                return c;
            }
            if (line.trim().equals("forbidden machine:") && validFile) {
                input = fileIO.forbiddenMachine(input, c);
                if (!validFile) {
                    System.err.println(
                            "Error while parsing input file");
                    System.exit(0);
                    return c;
                }
                /*
                if (input.hasNextLine()) {
                    line = input.nextLine();// should contain "too-near
                                            // tasks:\n"
                    lineCounter++;
                } else {
                    System.err.println(
                            "Error while parsing input file");
                    System.exit(0);
                }
                // If the line is blank, skips past blank lines til we find the next line with chars in it (should be too-near tasks)
                while(line.trim().isEmpty()) {
                    if (input.hasNextLine()) {
                        line = input.nextLine();
                        lineCounter++;
                    }
                    else {
                        System.err.println("Error while parsing input file");
                        System.exit(0);
                    }
                }
                */
            } else {
                System.err.println(
                        "Error while parsing input file");
                System.exit(0);
                return c;
            }
            if (line.trim().equals("too-near tasks:") && validFile) {
                input = fileIO.tooNearTasks(input, c);
                if (!validFile) {
                    System.err.println(
                            "Error while parsing input file");
                    System.exit(0);
                    return c;
                }
                /*
                if (input.hasNextLine()) {
                    line = input.nextLine();// should contain "machine
                                            // penalties:"
                    lineCounter++;
                } else {
                    System.err.println(
                            "Error while parsing input file");
                    validFile = false;
                    System.exit(0);
                    return c;
                }
                while(line.trim().isEmpty()) {
                    if (input.hasNextLine()) {
                        line = input.nextLine();
                        lineCounter++;
                    }
                    else {
                        System.err.println("Error while parsing input file");
                        System.exit(0);
                    }
                }
                */

            } else {
                System.err.println(
                        "Error while parsing inputs file");
                validFile = false;
                System.exit(0);
                return c;
            }

            if (line.trim().equals("machine penalties:") && validFile) {
                input = fileIO.machinePenalties(input, c);
                if (!validFile) {
                    System.err.println(
                            "Error while parsing input file");
                    validFile = false;
                    System.exit(0);
                    return c;
                }
                /*
                if (input.hasNextLine()) {
                    line = input.nextLine();
                    lineCounter++;

                } else {
                    System.err.println(
                            "Error while parsing input file");
                    validFile = false;
                    System.exit(0);
                    return c;
                }
                while(line.trim().isEmpty()) {
                    if (input.hasNextLine()) {
                        line = input.nextLine();
                        lineCounter++;
                    }
                    else {
                        System.err.println("Error while parsing input file");
                        System.exit(0);
                    }
                }
                */
            } else {
                System.err.println(
                        "Error while parsing input file");
                validFile = false;
                System.exit(0);
                return c;
            }

            if (line.trim().equals("too-near penalties:") && validFile) {
                if (input.hasNextLine()) {
                    input = fileIO.tooNearPenalties(input, c);
                    if (!validFile) {
                        System.err.println(
                                "Error while parsing input file");
                        validFile = false;
                        System.exit(0);
                        return c;
                    }
                }
            } else {
                System.err.println(
                        "Error while parsing input file");
                System.exit(0);
                return c;
            }
            input.close();
        } catch (Exception ex) {
            System.err.println("could not open input file");
            System.exit(0);
            return c;
        }
        return c;
    }

    private static Scanner machinePenalties(Scanner input, Constraints c) {
        //int pen = 0;
        for (int i = 0; i < 8; i++) {
            if (input.hasNextLine()) {
                line = input.nextLine();
                lineCounter++;
            } else {
                System.err.println("machine penalty error");
                System.exit(0);
            }
            while(line.trim().isEmpty()) {
                if (input.hasNextLine()) {
                    line = input.nextLine();
                    lineCounter++;
                }
                else {
                    System.err.println("Error while parsing input file");
                    System.exit(0);
                }
            }
            String[] penaltiesStrArr = line.split(" ");
            if (penaltiesStrArr.length != 8) {
                validFile = false;
                System.err.println("machine penalty errors");
                System.exit(0);
                return input;
            }
            int[] penaltiesIntArr = new int[8];

            for (int y = 0; y < 8; y++) {
                try {
                    int temp = Integer.parseInt(penaltiesStrArr[y]);
                    penaltiesIntArr[y] = temp;
                    if (penaltiesIntArr[y] < 0) {
                        System.err.println("machine penalty error");
                        System.exit(0);
                        return input;
                    }
                } catch (NumberFormatException e) {
                    System.err.println("machine penalty error");
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
            System.err.println("Error while parsing input file");
            validFile = false;
            System.exit(0);
            return input;
        } else {

            if (input.hasNextLine()) {
                line = input.nextLine();
                lineCounter++;
                if (!line.trim().isEmpty() && (line.charAt(0)<= 57 && line.charAt(0) >= 48)) {
                	System.err.println("machine penalty error");
                	System.exit(0);
                } else if (!line.trim().isEmpty()){
                	System.err.println("Error while parsing input file");
                	System.exit(0);
                }
            } else {
                System.err.println(
                        "Error while parsing input file");
                System.exit(0);
            }
            while(line.trim().isEmpty()) {
                if (input.hasNextLine()) {
                    line = input.nextLine();
                    lineCounter++;
                }
                else {
                    System.err.println("Error while parsing input file");
                    System.exit(0);
                }
            }
        }
        return input;
    }

    private static Scanner tooNearPenalties(Scanner input, Constraints c) {
        //int penalty;
        if (!input.hasNextLine()) {
            System.err.print("Error while parsing input file");
            System.exit(0);
        } else {
            line = input.nextLine();
            lineCounter++;
        }
        mFind("[A-H][,][A-H][,][\\d+]", input, c, 5);
        return input;
    }

    private static void mFind(String regex, Scanner input,
            Constraints c, int type) {
        lineCounter++;
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(line);

        while (m.find()) {
            char[] lineArray = line.toCharArray();
            int sub = lineArray[1] - 1;
            char[] TMPair = {(char) sub, lineArray[3]};
            int penalty = 0;
            if (type == 1) {
                for (int i = 0; i < c.forcedPartialAssn.size(); i++) {
                    if ((char) c.forcedPartialAssn.get(i)[0] == (char) TMPair[0]
                            || (char) c.forcedPartialAssn
                                    .get(i)[1] == (char) TMPair[1]) {
                        System.err.println("partial assignment error");
                        System.exit(0);
                    }
                }
            }
            if (type == Constraints.TOO_NEAR_PENALTIES) {
                String tooNearPenIntOnly = line.replaceAll("[^\\d.]", "");
                penalty = Integer.parseInt(tooNearPenIntOnly);
                c.addTooNearPenalties(TMPair, penalty);

            } else {
                c.addConstraintPair(type, TMPair);
            }
            if (input.hasNextLine()) {
                line = input.nextLine();
                lineCounter++;
                while(line.trim().isEmpty()) {
                    if (input.hasNextLine()) {
                        line = input.nextLine();
                        lineCounter++;
                    }
                    else if (type == Constraints.TOO_NEAR_PENALTIES) {
                        return;
                    }
                    else {
                        System.err.println("Error while parsing input file");
                        System.exit(0);
                    }
                }
                
            } else if (type != Constraints.TOO_NEAR_PENALTIES) {
                System.err.println(
                        "Error while parsing input file ");
                System.exit(0);
            } else {

                return;
            }
            m = p.matcher(line);
        }
        if (!m.find() && !line.trim().isEmpty() && type == Constraints.TOO_NEAR_PENALTIES) {
            System.err.println("invalid task");
            System.exit(0);
        }
        if (!m.find() && !line.trim().isEmpty() && type == Constraints.FORBIDDEN_MACHINE && (!line.trim().equals("too-near tasks:"))) {
            System.err.println("invalid machine/task");
            System.exit(0);
        }
        if (!m.find() && !line.trim().isEmpty() && type == Constraints.TOO_NEAR_TASKS && (!line.trim().equals("machine penalties:"))) {
            System.err.println("invalid machine/tasks");
            System.exit(0);
        }
        /*
        if (!line.trim().isEmpty()) {
            validFile = false;
            System.err
                    .println("Error while parsing input file");
            System.exit(0);
            return;
        }
        */
    }

    private static Scanner tooNearTasks(Scanner input, Constraints c) {
        if (input.hasNextLine()) {
            line = input.nextLine();
        } else {
            System.err.println("Error while parsing input file");
            System.exit(0);
        }
        while(line.trim().isEmpty()) {
            if (input.hasNextLine()) {
                line = input.nextLine();
                lineCounter++;
            }
            else {
                System.err.println("Error while parsing input file");
                System.exit(0);
            }
        }
        mFind("[(][A-H][,][A-H][)]", input, c, Constraints.TOO_NEAR_TASKS);
        return input;
    }

    private static Scanner forbiddenMachine(Scanner input, Constraints c) {
        if (input.hasNextLine()) {
            line = input.nextLine();
        } else {
            System.err.println("Error while parsing input file");
            System.exit(0);
        }
        while(line.trim().isEmpty()) {
            if (input.hasNextLine()) {
                line = input.nextLine();
                lineCounter++;
            }
            else {
                System.err.println("Error while parsing input file");
                System.exit(0);
            }
        }
        mFind("[(][1-8][,][A-H][)]", input, c, Constraints.FORBIDDEN_MACHINE);
        return input;
    }

    private static Scanner forcedPartialAssignment(Scanner input,
            Constraints c) {
        if (input.hasNextLine()) {
            line = input.nextLine();
        } else {
            System.err.println("Error while parsing input file");
            System.exit(0);
        }
        // Skips past blank lines until we hit a non-blank line
        while(line.trim().isEmpty()) {
            if (input.hasNextLine()) {
                line = input.nextLine();
                lineCounter++;
            }
            else {
                System.err.println("Error while parsing input file");
                System.exit(0);
            }
        }
        mFind("[(][1-8][,][A-H][)]", input, c,
                Constraints.FORCED_PARTIAL_ASSIGNMENT);
        return input;
    }
}
