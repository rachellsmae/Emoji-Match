import org.junit.Test;
import static org.junit.Assert.*;
public class BoardTests {
	
	/* Tests for shiftLeft and rotate*/
	// shift left combined numbers
	@Test
	public void shiftLeftAdd() {
		Board.playing = true;
		int[][] a = new int[][]{{0, 0, 0, 0}, {0, 0, 2, 0}, {0, 0, 0, 0}, {0, 0, 2, 0}};
		int[][] b = new int[][]{{0, 0, 4, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
		int[][] c = Board.shiftLeft(a);
		
		assertArrayEquals(b, c);
	}
	
	// shift left no combined numbers
	@Test
	public void shiftLeftNoAdd() {
		int[][] a = new int[][]{{0, 0, 0, 0}, {0, 2, 0, 0}, {0, 0, 0, 0}, {0, 0, 2, 0}};
		int[][] b = new int[][]{{0, 2, 2, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
		int[][] c = Board.shiftLeft(a);
		
		assertArrayEquals(b, c);
	}
	
	// rotate once
	@Test
	public void testRotate() {
		int[][] a = new int[][]{{2, 4, 8, 16}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
		int[][] b = new int[][]{{16, 0, 0, 0}, {8, 0, 0, 0}, {4, 0, 0, 0}, {2, 0, 0, 0}};
		int[][] c = Board.rotate(a);
		
		assertArrayEquals(b, c);
	}
	
	// test for shift up combined numbers
	@Test
	public void testShiftUpAdd() {
		int[][] a = new int[][] { { 0, 2, 0, 2 }, { 0, 0, 0, 0 }, { 0, 4, 4, 0 }, { 0, 0, 0, 0 } };
		int[][] b = new int[][] { { 4, 0, 0, 0 }, { 0, 0, 0, 0 }, { 8, 0, 0, 0 }, { 0, 0, 0, 0 } };
		int[][] c = Board.rotate(Board.shiftLeft(Board.rotate(Board.rotate(Board.rotate(a)))));

		assertArrayEquals(b, c);
	}
	
	// test for shift up no combined numbers
	@Test
	public void testShiftUpNoAdd() {
		int[][] a = new int[][] { { 0, 2, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 4, 0 }, { 0, 0, 0, 0 } };
		int[][] b = new int[][] { { 2, 0, 0, 0 }, { 0, 0, 0, 0 }, { 4, 0, 0, 0 }, { 0, 0, 0, 0 } };
		int[][] c = Board.rotate(Board.shiftLeft(Board.rotate(Board.rotate(Board.rotate(a)))));

		assertArrayEquals(b, c);
	}
	
	// test for shift down combined numbers
	@Test
	public void testShiftDownAdd() {
		int[][] a = new int[][] { { 0, 2, 0, 2 }, { 0, 0, 0, 0 }, { 0, 4, 4, 0 }, { 0, 0, 0, 0 } };
		int[][] b = new int[][] { { 0, 0, 0, 4 }, { 0, 0, 0, 0 }, { 0, 0, 0, 8 }, { 0, 0, 0, 0 } };
		int[][] c = Board.rotate(Board.rotate(Board.rotate(Board.shiftLeft(Board.rotate(a)))));

		assertArrayEquals(b, c);
	}
	
	// test for shift down no combined numbers
		@Test
		public void testShiftDownNoAdd() {
			int[][] a = new int[][] {{0, 2, 0, 0 }, {0, 0, 0, 0 }, {0, 0, 4, 0 }, {0, 0, 0, 0 }};
			int[][] b = new int[][] {{0, 0, 0, 2 }, {0, 0, 0, 0 }, {0, 0, 0, 4 }, {0, 0, 0, 0 }};
			int[][] c = Board.rotate(Board.rotate(Board.rotate(Board.shiftLeft(Board.rotate(a)))));

			assertArrayEquals(b, c);
		}
	
	// test for shift right combined numbers
	@Test
	public void testShiftRightAdd() {
		int[][] a = new int[][]{{0, 0, 0, 2}, {0, 0, 0, 2}, {0, 0, 0, 0}, {0, 0, 0, 0}};
		int[][] b = new int[][]{{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 4}};
		int[][] c = Board.rotate(Board.rotate(Board.shiftLeft(Board.rotate(Board.rotate(a)))));
		
		assertArrayEquals(b, c);
	}
	
	/* Tests for score */
	@Test
	public void testScore() {
		int[][] a = new int[][] {{4, 8, 4, 8}, {8, 4, 8, 4}, {4, 8, 4, 8}, {8, 4, 8, 4}};
		int[][] b = new int[][] { { 0, 2, 0, 2 }, { 0, 0, 0, 0 }, { 0, 4, 4, 0 }, { 0, 0, 0, 0 } };
		
		assertEquals(96, Board.score(a));
		assertEquals(12, Board.score(b));
	}
	
	/* Tests for has lost */
	// has lost is true
	@Test
	public void testHasLostTrue() {
		int[][] a = new int[][] {{4, 8, 4, 16}, {8, 4, 64, 8}, {16, 32, 16,  32}, {32, 16, 32, 64}};
		int[][] b = new int[][] {{4, 8, 4, 8}, {8, 4, 8, 4}, {4, 8, 4, 8}, {8, 4, 8, 4}};
		
		assertTrue(Board.hasLost(a));
		assertTrue(Board.hasLost(b));
	}
	
	// has lost is false
	@Test
	public void testHasLostFalse() {
		int[][] a = new int[][] {{4, 4, 8, 16}, {8, 4, 64, 8}, {16, 32, 16,  32}, {32, 16, 32, 64}};
		int[][] b = new int[][] {{4, 8, 0, 8}, {8, 4, 8, 4}, {4, 8, 4, 8}, {8, 4, 8, 4}};
		
		assertFalse(Board.hasLost(a));
		assertFalse(Board.hasLost(b));
	}
	
	// has won is true
	@Test
	public void testHasWonTrue() {
		int[][] a = new int[][]{{0, 0, 0, 512}, {0, 0, 0, 2}, {0, 0, 0, 0}, {0, 0, 0, 0}};
		int[][] b = new int[][]{{0, 0, 0, 2}, {0, 0, 0, 2}, {0, 0, 0, 0}, {0, 0, 0, 512}};
		
		assertTrue(Board.hasWon(a));
		assertTrue(Board.hasWon(b));
	}
	
	// has won is false
	@Test
	public void testHasWonFalse() {
		int[][] a = new int[][] {{4, 4, 8, 16}, {8, 4, 64, 8}, {16, 32, 16,  32}, {32, 16, 32, 64}};
		int[][] b = new int[][] {{4, 8, 0, 8}, {8, 4, 8, 4}, {4, 8, 4, 8}, {8, 4, 8, 4}};
		
		assertFalse(Board.hasWon(a));
		assertFalse(Board.hasWon(b));
	}

}