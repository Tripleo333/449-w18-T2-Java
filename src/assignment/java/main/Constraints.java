package assignment.java.main;

public class Constraints {
	
	// The beginning of hard constraints... 
	
	/*
	 * These pairs must be included in the final state
	 * Example: ['1', 'F']
	 */
	public char[][] forcedPartialAssn = {};
	/*
	 * These pairs cannot be included in the final state
	 */
	public char[][] forbiddenMach = {};
	/*
	 * For every row i in tooNearTasks, machine i cannot take the first task and machine i+1 cannot take the second task
	 */
	public char[][] tooNearTasks = {};
	
}
