package assignment.java.main;
/**
 * 
 */
import java.io.File;
import java.util.Scanner;
public class fileIO {

	//this is a preattempt at opening a file
	//final version should return a buffered reader or scanner (havn't decided yet)
	//I'll make another function below that parses it and adds it to their respective places 
    
    /*
     * available tools for string manipulation: contains, indexof
     * 
     * regexes:
     * looking for a word that is 2-20 characters in length
     * [A-Za-z]{2,20} or \\w{2,20} 
     * 
     * Pattern checkRegex = Pattern.compile(theRegex);
     * Matcher regexmatcher = checkRegex.matcher(str2Check);
     * 
     * while(regexMatcher.find()){
     *  if (regexMatcher.group().length() != 0){
     *      system.out.println(regexMatcher.group().trim());
     *      
     *  
     *  }
     *  
     * }
     * 
     */
    
    public static void main(String[] args) {
        fileIO();
        
    }
    
    public static void fileIO() {
        try {
            System.out.println("Enter the file name with the extension: ");
            Scanner input = new Scanner(System.in);
            File file = new File(input.nextLine());
            input = new Scanner(file);
            String name = "";
            int counter =0;
            String garbage = "";
            String line = input.nextLine();

            // C:\Users\Admin\git\449-w18-T2-Java\src\assignment\java\main\test.txt
            
            
            if(line.matches("Name:(.*)")) {
                name = input.nextLine();
                System.out.println("contents of name: " + name);
                
            }else if(line.equals("\n")) {
                garbage = input.nextLine();
            }else if(line.matches("forced partial assignment:(.*)")) {
                
                
            }
            
            input.close();
                        
        }catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    private void forcedPartialAssignment(Scanner input) {
//        String pattern = "";
        String line = input.nextLine();
        while(line.matches("([A-H],[1-8])(.*)")) {
            char[] lineArray = line.toCharArray();
            char [] TMPair = {lineArray[1],lineArray[3]};
            addConstraintPair(1,TMPair);
            line = input.nextLine();
        }
    }
	
	
	
}  
