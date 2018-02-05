package assignment.java.test;

//import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.*;
//import java.util.Arrays;
import org.junit.jupiter.api.Test;
import assignment.java.main.*;

class JUnitTest {

	@Test
	void addConstraintPairSwitch() {
		Constraints constraint = new Constraints();
		
		char [] pair = ("0A").toCharArray();
		constraint.addConstraintPair(1, pair);
		constraint.addConstraintPair(2, pair);
		constraint.addConstraintPair(3, pair);		
		constraint.addConstraintPair(4, pair);
		constraint.addConstraintPair('a', pair);		
	}
	
	@Test
	void possibilityTreeInit() {
		char[] c = ("ABCDEFGH").toCharArray();
		PossibilityTree pt = new PossibilityTree(c);
	
		c = ("ABCDEFGG").toCharArray();		
		assertFalse(c.equals(pt.getTasks()));
		
		c = ("ABCDEFGH").toCharArray();
		assertArrayEquals(c, pt.getTasks());
		
		c = ("GHBEFADC").toCharArray();
		PossibilityTree py = new PossibilityTree(c);
		assertArrayEquals(c, py.getTasks());		
	}
	
	@Test
	void addForcedPartialAssignment() {
		Constraints constraint = new Constraints();
		
		char [] pair = ("0A").toCharArray();
		constraint.addConstraintPair(1, pair);
		assertEquals(pair, constraint.forcedPartialAssn.get(0));
		pair = ("1B").toCharArray();
		constraint.addConstraintPair(1, pair);
		assertEquals(pair, constraint.forcedPartialAssn.get(1));
		pair = ("G6").toCharArray();
		constraint.addConstraintPair(1, pair);
		assertEquals(pair, constraint.forcedPartialAssn.get(2));

	}

	@Test
	void addForbiddenMachine() {
		Constraints constraint = new Constraints();
		
		char [] pair = ("0A").toCharArray();
		constraint.addConstraintPair(2, pair);
		assertEquals(pair, constraint.forbiddenMach.get(0));
		pair = ("1B").toCharArray();
		constraint.addConstraintPair(2, pair);
		assertEquals(pair, constraint.forbiddenMach.get(1));
		pair = ("G6").toCharArray();
		constraint.addConstraintPair(2, pair);
		assertEquals(pair, constraint.forbiddenMach.get(2));

	}

	@Test
	void addTooNearTask() {
		Constraints constraint = new Constraints();
		
		char [] pair = ("AB").toCharArray();
		constraint.addConstraintPair(3, pair);
		assertEquals(pair, constraint.tooNearTasks.get(0));
		pair = ("AC").toCharArray();
		constraint.addConstraintPair(3, pair);
		assertEquals(pair, constraint.tooNearTasks.get(1));
		pair = ("BD").toCharArray();
		constraint.addConstraintPair(3, pair);
		assertEquals(pair, constraint.tooNearTasks.get(2));

	}
	
	@Test
	void addMachPen() {
		Constraints constraint = new Constraints();
		
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				int pen = j+i+i;
				System.out.printf("%3d", pen);
				constraint.addMachPenalties(i, j, j+i);
			}
			System.out.println();
		}
		System.out.println();
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				int expPen = j+i+i;
				System.out.printf("%3d", expPen);
//				assertEquals(expPen, constraint.machinePenalties[i][j]);
			}
			System.out.println();
		}
	}	
	
	@Test
	void checkFPA() {
		char [] str = ("AXXXXXXX").toCharArray();
		State state = new State(str);
		Constraints constraint = new Constraints();

		constraint.addConstraintPair(1, ("0A".toCharArray()));
		constraint.addConstraintPair(1, ("1B".toCharArray()));
		constraint.addConstraintPair(1, ("6G".toCharArray()));		
		assertTrue(constraint.checkFPA(0, state, constraint.forcedPartialAssn.toArray(new char[0][0])));
		state = new State(1, 'B', state);
		assertTrue(constraint.checkFPA(1, state, constraint.forcedPartialAssn.toArray(new char[0][0])));
		state = new State(6, 'G', state);
		assertTrue(constraint.checkFPA(6, state, constraint.forcedPartialAssn.toArray(new char[0][0])));
		
		constraint.addConstraintPair(1, ("3B".toCharArray()));
		constraint.addConstraintPair(1, ("5C".toCharArray()));
		constraint.addConstraintPair(1, ("7A".toCharArray()));
		state = new State(3, 'C', state);
		assertFalse(constraint.checkFPA(3, state, constraint.forcedPartialAssn.toArray(new char[0][0])));
		state = new State(5, 'F', state);
		assertFalse(constraint.checkFPA(5, state, constraint.forcedPartialAssn.toArray(new char[0][0])));
		state = new State(7, 'D', state);
		assertFalse(constraint.checkFPA(7, state, constraint.forcedPartialAssn.toArray(new char[0][0])));

	}

	@Test
	void forbiddenMachine() {
		char [] str = ("ABCDEFGH").toCharArray();
		State state = new State(str);
		Constraints constraint = new Constraints();
		
		char [] pair = ("0A").toCharArray();
		constraint.addConstraintPair(1, pair);
//		assertFalse(constraint.checkFM(0, state, constraint.forbiddenMach.toArray(new char[0][0])));
	
		
		
/*		
		char [] str = ("ABCDEFGH").toCharArray();
		PossibilityTree pt = new PossibilityTree(str);
		State state = new State(str);

		char [] pair = ("0A").toCharArray();
		pt.addFM(pair);
		assertEquals(-1, (int) pt.Branch(0, state));
	}
	
	void constraintAdd() {
		char [] str = ("ABCDEFGH").toCharArray();
		PossibilityTree pt = new PossibilityTree(str);
		State state = new State(str);
		
		char[] pair = ("1A").toCharArray();
		pt.addFPA(pair);
		pair = ("2B").toCharArray();
		pt.addFPA(pair);
		pair = ("3C").toCharArray();
		pt.addFPA(pair);
		pair = ("4D").toCharArray();
		pt.addFPA(pair);
		pair = ("5E").toCharArray();
		pt.addFPA(pair);
		pair = ("6F").toCharArray();
		pt.addFPA(pair);
		pair = ("7G").toCharArray();
		pt.addFPA(pair);
		pair = ("0H").toCharArray();
		pt.addFPA(pair);

		int[][] mp = new int[8][8];

		for(int i=0;i<8;i++) {
			for(int j=0;j<8;j++) {
				if(i+j>7)		
					mp[i][j] = i+j-8;
				else
					mp[i][j] = i+j;
			}
		}
		int[] k = mp[0];
		mp[0] = mp[7];
		mp[7] = k;
		for(int i=0;i<8;i++) {
			for(int j=0;j<8;j++) {
				System.out.print(mp[i][j]);
			}
			System.out.println();
		}
		pt.addMP(mp);
		
		System.out.print("state.entries: ");
		System.out.println(state.entries);
		System.out.println("state.penalty: " + state.penalty);
		System.out.println(pt.Branch(1, state));

		
*/	}

}
