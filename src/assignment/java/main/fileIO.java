package assignment.java.main;
/**
 * 
 */
import java.io.*;
public class fileIO {

	//this is a preattempt at opening a file
	//final version should return a buffered reader or scanner (havn't decided yet)
	//I'll make another function below that parses it and adds it to their respective places
	private static void getFileInfo(String fileName) throws IOException {
		FileInputStream Fin = null;
		try {
			Fin = new FileInputStream(fileName);
			int c;
			while ((c = Fin.read()) != -1) {
				
				
			}
		}finally{
			if(Fin != null) {
				Fin.close();
			}
		}
	}
}
