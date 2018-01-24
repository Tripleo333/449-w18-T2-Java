package assignment.java.main;
/**
 * 
 */
import java.io.*;
public class fileIO {

	//this is a preattempt at opening a file
	//final version should return a buffered reader or scanner (havn't decided yet)
	//I'll make another function below that parses it and adds it to their respective places
	private static void getFileInfo() throws IOException {
		FileReader in = null;
		
		try {
			
		}finally{
			if(in != null) {
				in.close();
			}
		}
	}
}
