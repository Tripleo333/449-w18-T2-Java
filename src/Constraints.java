
public class Constraints {
	
	// The beginning of hard constraints... 
	
	/*
	 * These pairs must be included in the final state
	 */
	public char[][] forcedPartialAssn = {{'1', 'F'}};
	/*
	 * These pairs cannot be included in the final state
	 */
	public char[][] forbiddenMach = {{'1', 'F'}};
	/*
	 * For every row i in tooNearTasks, machine i cannot take the first task and machine i+1 cannot take the second task
	 */
	public char[][] tooNearTasks = {{'A', 'B'}};
	
}
