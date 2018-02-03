package assignment.java.main;
/**
 * 
 */
import java.io.File;
import java.util.Scanner;
import java.util.regex.*;
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
            while(input.hasNextLine()) {
                String line = input.nextLine();
                System.out.println(line);
               
            }
            input.close();
                    
        }catch(Exception ex) {
            ex.printStackTrace();
        }
    }
    

	
	
	
}  
