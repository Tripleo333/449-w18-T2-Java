//package assignment.java.main;
	
public class Triplet {
    char mach;
    char task;
    Integer penalty;
    
    Triplet(char mach, char task, Integer penalty) {
        this.mach = mach;
        this.task = task;
        this.penalty = penalty;
    }
    
    //Omar added a to string function to use for debugging later 
    
    public String toString() {
        String str = "Mach " + this.mach + " task " + this.task + " penalty " + penalty + "\n";
        return str;
    }
}