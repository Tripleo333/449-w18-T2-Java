package assignment.java.test;

//import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

//import java.util.Arrays;
import org.junit.jupiter.api.*;
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
		PossibilityTree pt = new PossibilityTree(c, null);
	
		c = ("ABCDEFGG").toCharArray();		
		assertFalse(c.equals(pt.tasks));
		
		c = ("ABCDEFGH").toCharArray();
		assertArrayEquals(c, pt.tasks);
		
		c = ("GHBEFADC").toCharArray();
		PossibilityTree py = new PossibilityTree(c, null);
		assertArrayEquals(c, py.tasks);		
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
				constraint.addMachPenalties(i, j, j+i);
			}
		}
		System.out.println();
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				int expPen = j+i+i;
//				assertEquals(expPen, constraint.machinePenalties[i][j]);
			}
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
	
		
	}
/*	
	@Test
	void complexInvalidTest() {
		Constraints c = fileIO.fileIO("D:\\eclipse-workspace\\git\\449-w18-T2-Java\\src\\assignment\\java\\main\\ComplexInvalidTest.txt");
		State state = new State();
		PossibilityTree pt = new PossibilityTree(("ABCDEFGH").toCharArray(), c);
		pt.Branch(-1, state, pt.tasks);
		
	}
	
	@Test
	void complexValidTest() {
		Constraints c = fileIO.fileIO("D:\\eclipse-workspace\\git\\449-w18-T2-Java\\src\\assignment\\java\\main\\ComplexValidTest.txt");
		State state = new State();
		PossibilityTree pt = new PossibilityTree(("ABCDEFGH").toCharArray(), c);
		pt.Branch(-1, state, pt.tasks);
	}
	
	@Test
	void InvalidFileName() {
		Constraints c = fileIO.fileIO("D:\\eclipse-workspace\\git\\449-w18-T2-Java\\src\\assignment\\java\\main\\InvalidFileName.txt");
		State state = new State();
		PossibilityTree pt = new PossibilityTree(("ABCDEFGH").toCharArray(), c);
		pt.Branch(-1, state, pt.tasks);
	}
	
	@Test
	void InvalidForcedPartAsg() {
		Constraints c = fileIO.fileIO("D:\\eclipse-workspace\\git\\449-w18-T2-Java\\src\\assignment\\java\\main\\InvalidForcedPartAsg.txt");
		State state = new State();
		PossibilityTree pt = new PossibilityTree(("ABCDEFGH").toCharArray(), c);
		pt.Branch(-1, state, pt.tasks);
	}
	
	@Test
	void InvalidInForbidMach() {
		Constraints c = fileIO.fileIO("D:\\eclipse-workspace\\git\\449-w18-T2-Java\\src\\assignment\\java\\main\\InvalidInForbidMach.txt");
		State state = new State();
		PossibilityTree pt = new PossibilityTree(("ABCDEFGH").toCharArray(), c);
		pt.Branch(-1, state, pt.tasks);
	}
	
	@Test
	void InvalidMachPenal() {
		Constraints c = fileIO.fileIO("D:\\eclipse-workspace\\git\\449-w18-T2-Java\\src\\assignment\\java\\main\\InvalidMachPenal.txt");
		State state = new State();
		PossibilityTree pt = new PossibilityTree(("ABCDEFGH").toCharArray(), c);
		pt.Branch(-1, state, pt.tasks);
	}
	
	@Test
	void InvalidMachPenalNotEnough() {
		Constraints c = fileIO.fileIO("D:\\eclipse-workspace\\git\\449-w18-T2-Java\\src\\assignment\\java\\main\\InvalidMachPenalNotEnough.txt");
		State state = new State();
		PossibilityTree pt = new PossibilityTree(("ABCDEFGH").toCharArray(), c);
		pt.Branch(-1, state, pt.tasks);
	}
	
	@Test
	void InvalidSpaces() {
		Constraints c = fileIO.fileIO("D:\\eclipse-workspace\\git\\449-w18-T2-Java\\src\\assignment\\java\\main\\InvalidSpaces.txt");
		State state = new State();
		PossibilityTree pt = new PossibilityTree(("ABCDEFGH").toCharArray(), c);
		pt.Branch(-1, state, pt.tasks);
	}
	
	@Test
	void InvalidTooNearPenal() {
		Constraints c = fileIO.fileIO("D:\\eclipse-workspace\\git\\449-w18-T2-Java\\src\\assignment\\java\\main\\InvalidTooNearPenal.txt");
		State state = new State();
		PossibilityTree pt = new PossibilityTree(("ABCDEFGH").toCharArray(), c);
		pt.Branch(-1, state, pt.tasks);
	}
	
	@Test
	void InvalidTooNearTask() {
		Constraints c = fileIO.fileIO("D:\\eclipse-workspace\\git\\449-w18-T2-Java\\src\\assignment\\java\\main\\InvalidTooNearTask.txt");
		State state = new State();
		PossibilityTree pt = new PossibilityTree(("ABCDEFGH").toCharArray(), c);
		pt.Branch(-1, state, pt.tasks);
	}
*/
	
	@Test
	void emptyFileIO() {
		final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		
		Constraints c = fileIO.fileIO("D:\\eclipse-workspace\\git\\449-w18-T2-Java\\src\\empty.txt");
		assertEquals(c, null);
	}
	
	@Test
	void simple1FileIO() {
		Constraints c = fileIO.fileIO("D:\\eclipse-workspace\\git\\449-w18-T2-Java\\src\\input1.txt");
		State state = new State();
		PossibilityTree pt = new PossibilityTree(("ABCDEFGH").toCharArray(), c);
		pt.Branch(-1, state, pt.tasks);
		assertArrayEquals(("ABCDEFGH").toCharArray(), pt.minPenalty.entries);
	}

	@Test
	void simple2FileIO() {
		Constraints c = fileIO.fileIO("D:\\eclipse-workspace\\git\\449-w18-T2-Java\\src\\input2.txt");
		State state = new State();
		PossibilityTree pt = new PossibilityTree(("ABCDEFGH").toCharArray(), c);
		pt.Branch(-1, state, pt.tasks);
		assertArrayEquals(("HGFEDCBA").toCharArray(), pt.minPenalty.entries);
	}

	@Test
	void simple3FileIO() {
		Constraints c = fileIO.fileIO("D:\\eclipse-workspace\\git\\449-w18-T2-Java\\src\\input3.txt");
		State state = new State();
		PossibilityTree pt = new PossibilityTree(("ABCDEFGH").toCharArray(), c);
		pt.Branch(-1, state, pt.tasks);
		assertArrayEquals(("FAGDHECB").toCharArray(), pt.minPenalty.entries);
	}
	
	@Test
	void FPAhighPen1() {
		Constraints c = fileIO.fileIO("D:\\eclipse-workspace\\git\\449-w18-T2-Java\\src\\input4.txt");
		State state = new State();
		PossibilityTree pt = new PossibilityTree(("ABCDEFGH").toCharArray(), c);
		pt.Branch(-1, state, pt.tasks);
		assertArrayEquals(("ABCEFGHD").toCharArray(), pt.minPenalty.entries);
	}
	
	@Test
	void FPAhighPen2() {
		Constraints c = fileIO.fileIO("D:\\eclipse-workspace\\git\\449-w18-T2-Java\\src\\input5.txt");
		State state = new State();
		PossibilityTree pt = new PossibilityTree(("ABCDEFGH").toCharArray(), c);
		pt.Branch(-1, state, pt.tasks);
		assertArrayEquals(("BCDEAFGH").toCharArray(), pt.minPenalty.entries);
	}
	
	@Test
	void FMleastPen1() {
		Constraints c = fileIO.fileIO("D:\\eclipse-workspace\\git\\449-w18-T2-Java\\src\\input6.txt");
		State state = new State();
		PossibilityTree pt = new PossibilityTree(("ABCDEFGH").toCharArray(), c);
		pt.Branch(-1, state, pt.tasks);
		if(pt.minPenalty.entries.equals(("BCADEFGH").toCharArray())){
			assertArrayEquals(("BCADEFGH").toCharArray(), pt.minPenalty.entries);
		} else {
			assertArrayEquals(("BCDEFGHA").toCharArray(), pt.minPenalty.entries);
		}
	}
	
	@Test 
	void FMleastPen2(){
		Constraints c = fileIO.fileIO("D:\\eclipse-workspace\\git\\449-w18-T2-Java\\src\\input7.txt");
		State state = new State();
		PossibilityTree pt = new PossibilityTree(("ABCDEFGH").toCharArray(), c);
		pt.Branch(-1, state, pt.tasks);
		if(pt.minPenalty.entries.equals(("ABCDEGHF").toCharArray())){
			assertArrayEquals(("ABCDEGHF").toCharArray(), pt.minPenalty.entries);
		} else {
			assertArrayEquals(("BCDEFGHA").toCharArray(), pt.minPenalty.entries);
		}
	}
	
}
