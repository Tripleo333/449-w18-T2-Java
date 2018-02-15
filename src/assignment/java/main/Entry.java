//package assignment.java.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Entry {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println(
                    "This program accepts exactly 2 command line arguments.");
            System.exit(0);
        }
        
        // Change error stream to a file
        /*
        try {
			File errorWriter = new File(args[1]);
			System.setErr(new PrintStream(new FileOutputStream (errorWriter, true)));
		} catch (FileNotFoundException e1) {
			System.err.println(
                    "An error was encountered trying to create the error output file ");
            System.exit(0);
		}
		*/
        // for error reporting   
        PrintWriter writer;
        try {
            writer = new PrintWriter(args[1], "UTF16");
            /*
        	File outputFile = new File(args[1]);
        	OutputStreamWriter outputStream;
    		if (!outputFile.exists()) {
        		outputStream = new OutputStreamWriter(new FileOutputStream (outputFile), "UTF16");
        	} else {
        		outputStream = new OutputStreamWriter(new FileOutputStream (outputFile, true), "UTF16");
        	}    	
        	writer = new PrintWriter(outputStream);
        	*/

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
                returned = "Solution " + min.entries[0] + " "
                        + min.entries[1] + " " + min.entries[2] + " "
                        + min.entries[3] + " " + min.entries[4] + " " 
                        + min.entries[5] + " " + min.entries[6] + " " 
                        + min.entries[7] + "; Quality: "
                        + min.penalty;
            }
           
            writer.println(returned);
            writer.close();
        } catch (FileNotFoundException e) {
            System.err.println("An error was encountered trying to create a file with the name: " + args[1]);
            System.exit(0);
        } catch (UnsupportedEncodingException e) {

            System.err.println("An unsupported encoding was provided to OutputStreamWriter");
            System.exit(0);
        }
    }
}
