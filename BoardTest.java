import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.Test;
import java.util.ArrayList;
import java.awt.Point;



public class BoardTest {


	@Rule
    public ExpectedException exception = ExpectedException.none();

	@Test
	public void testConstructor() {
		Board board = new Board(5);
		try {
			 board = new Board(1);
		    fail();
		} catch (IllegalArgumentException e) {
			System.out.println("The size board is too small");
		    assertTrue(true);
		}
		
		try {
			board = new Board(11);
		    fail();
		} catch (IllegalArgumentException e) {
			System.out.println("The size board is too big");
		    assertTrue(true);
		}

		int preset[][] = new int[5][5];
		for(int i = 0; i < preset.length; i++) {
			for(int j = 0; j < preset.length; j++) {
				preset[i][j] = 1;
			}
		}
		board = new Board(preset);
		Dot[][] myBoard = board.getBoard();
		for(int i = 0; i < preset.length; i++) {
			for(int j = 0; j < preset.length; j++) {
				assertTrue(myBoard[i][j].getColor() == 1);
			}
		}

	}
	
	@Test
	public void testCanMakeMove() {
		Board board = new Board(4);
		Dot[][] myBoard = board.getBoard();
		int k = 1;
		for(int y = 0; y < myBoard.length; y++) {
			for(int x = 0; x < myBoard.length; x++) {
				if(k == 6) {
					k = 1;
				}
				myBoard[x][y] = new Dot(k++);
			}
		}
		board.printBoard("No solution for can make move");
		assertTrue(!board.canMakeMove());
		

		myBoard[0][myBoard.length - 2] = new Dot(3);
		board.printBoard("solution for upper left, down");
		assertTrue(board.canMakeMove());
		myBoard[0][myBoard.length - 2] = new Dot(4);

		myBoard[1][myBoard.length - 1] = new Dot(3);
		board.printBoard("solution for upper left, right");
		assertTrue(board.canMakeMove());
		myBoard[1][myBoard.length - 1] = new Dot(4);
		//-----------------------------------------------

		myBoard[myBoard.length - 1][myBoard.length - 2] = new Dot(1);
		board.printBoard("solution for upper right, down");
		assertTrue(board.canMakeMove());
		myBoard[myBoard.length - 1][myBoard.length - 2] = new Dot(2);

		myBoard[myBoard.length - 2][myBoard.length - 1] = new Dot(1);
		myBoard[myBoard.length - 2][myBoard.length - 2] = new Dot(4);
		board.printBoard("solution for upper right, left");
		assertTrue(board.canMakeMove());
		myBoard[myBoard.length - 2][myBoard.length - 1] = new Dot(5);
		myBoard[myBoard.length - 2][myBoard.length - 2] = new Dot(1);

		//-----------------------------------------------
		myBoard[1][1] = new Dot(3);
		myBoard[0][1] = new Dot(1);
		board.printBoard("solution for bottom left, up");
		assertTrue(board.canMakeMove());
		myBoard[0][1] = new Dot(5);

		myBoard[1][0] = new Dot(1);
		board.printBoard("solution for bottom left, right");
		assertTrue(board.canMakeMove());
		myBoard[1][0] = new Dot(2);
		myBoard[1][1] = new Dot(1);

		//-----------------------------------------------
		myBoard[myBoard.length - 1][1] = new Dot(4);
		board.printBoard("solution for bottom right, up");
		assertTrue(board.canMakeMove());
		myBoard[myBoard.length - 1][1] = new Dot(3);

		myBoard[myBoard.length - 2][0] = new Dot(4);
		board.printBoard("solution for bottom right, left");
		assertTrue(board.canMakeMove());
		myBoard[myBoard.length - 2][0] = new Dot(3);


		//-----------------Side------------------------
		myBoard[0][myBoard.length - 1] = new Dot(4);
		board.printBoard("solution for left side, up");
		assertTrue(board.canMakeMove());
		myBoard[0][myBoard.length - 1] = new Dot(3);

		myBoard[0][myBoard.length - 3] = new Dot(4);
		board.printBoard("solution for left side, down");
		assertTrue(board.canMakeMove());
		myBoard[0][myBoard.length - 3] = new Dot(5);

		myBoard[1][myBoard.length - 2] = new Dot(4);
		myBoard[1][myBoard.length - 1] = new Dot(2);
		board.printBoard("solution for left side, right");
		assertTrue(board.canMakeMove());
		myBoard[1][myBoard.length - 2] = new Dot(5);
		myBoard[1][myBoard.length - 1] = new Dot(4);


		//-----------------------------------------
		myBoard[1][1] = new Dot(2);
		myBoard[2][1] = new Dot(5);
		board.printBoard("solution for bottom side, up");
		assertTrue(board.canMakeMove());
		myBoard[1][1] = new Dot(1);


		myBoard[0][0] = new Dot(2);
		board.printBoard("solution for bottom side, left");
		assertTrue(board.canMakeMove());
		myBoard[0][0] = new Dot(1);

		myBoard[2][0] = new Dot(2);
		board.printBoard("solution for bottom side, right");
		assertTrue(board.canMakeMove());
		myBoard[2][0] = new Dot(3);

		myBoard[2][1] = new Dot(2);
		


		//-----------------------------------------
		myBoard[0][myBoard.length - 2] = new Dot(1);

		myBoard[1][myBoard.length - 2] = new Dot(4);
		board.printBoard("solution for top side, down");
		assertTrue(board.canMakeMove());
		myBoard[1][myBoard.length - 2] = new Dot(5);

		myBoard[0][myBoard.length - 1] = new Dot(4);
		board.printBoard("solution for top side, right");
		assertTrue(board.canMakeMove());
		myBoard[0][myBoard.length - 1] = new Dot(3);

		myBoard[2][myBoard.length - 1] = new Dot(4);
		board.printBoard("solution for top side, left");
		assertTrue(board.canMakeMove());
		myBoard[2][myBoard.length - 1] = new Dot(5);
		myBoard[0][myBoard.length - 2] = new Dot(4);



		//--------------------------------------------
		myBoard[2][0] = new Dot(5);
		myBoard[myBoard.length - 1][2] = new Dot(3);
		board.printBoard("solution for right side, up");
		assertTrue(board.canMakeMove());		
		myBoard[myBoard.length - 1][2] = new Dot(2);


		myBoard[myBoard.length - 1][0] = new Dot(3);
		board.printBoard("solution for right side, down");
		assertTrue(board.canMakeMove());		
		myBoard[myBoard.length - 1][0] = new Dot(4);

		myBoard[myBoard.length - 2][1] = new Dot(3);
		board.printBoard("solution for right side, left");
		assertTrue(board.canMakeMove());		
		myBoard[myBoard.length - 2][1] = new Dot(2);
		myBoard[2][0] = new Dot(3);


		//----------------In the middle---------------
		myBoard[0][0] = new Dot(4);
		myBoard[2][2] = new Dot(3);
		myBoard[1][2] = new Dot(1);
		board.printBoard("solution for middle , up");
		assertTrue(board.canMakeMove());	
		myBoard[1][2] = new Dot(5);

		myBoard[1][0] = new Dot(1);
		board.printBoard("solution for middle , down");
		assertTrue(board.canMakeMove());	
		myBoard[1][0] = new Dot(2);

		myBoard[0][1] = new Dot(1);
		board.printBoard("solution for middle ,left");
		assertTrue(board.canMakeMove());	
		myBoard[0][1] = new Dot(2);

		myBoard[2][1] = new Dot(1);
		board.printBoard("solution for middle , right");
		assertTrue(board.canMakeMove());	
		myBoard[2][1] = new Dot(2);

		myBoard[2][2] = new Dot(1);
		myBoard[0][0] = new Dot(1);

	}

	@Test
	public void testSelection() {
		Board board = new Board(5);
		Dot[][] myBoard = board.getBoard();

		myBoard[0][1] = new Dot(2);
		myBoard[1][1] = new Dot(2);
		myBoard[2][1] = new Dot(2);

		assertTrue(board.numberSelected() == 0);
		board.selectDot(0,1);

		assertTrue(board.numberSelected() == 1);
		board.selectDot(1,1);
		assertTrue(board.numberSelected() == 2);
		board.selectDot(2,1);
		assertTrue(board.numberSelected() == 3);

		assertFalse(board.canDeselect(0,1));

		board.deselectDot(2,1);
		assertTrue(board.numberSelected() == 2);
		board.deselectDot(1,1);
		assertTrue(board.numberSelected() == 1);
		board.deselectDot(0,1);
		assertTrue(board.numberSelected() == 0);

	}

	@Test
	public void testClosedShape() {
		int preset[][] = new int[5][5];
		for(int i = 0; i < preset.length; i++) {
			for(int j = 0; j < preset.length; j++) {
				preset[i][j] = 1;
			}
		}
		Board board = new Board(preset);
		Dot[][] myBoard = board.getBoard();

		myBoard[0][1] = new Dot(2);
		myBoard[1][1] = new Dot(2);
		myBoard[2][1] = new Dot(2);
		myBoard[2][0] = new Dot(2);
		myBoard[1][0] = new Dot(2);
		myBoard[0][0] = new Dot(2);

		
		board.selectDot(1,1);
		board.selectDot(2,1);
		board.selectDot(2,0);
		board.selectDot(1,0);
		board.selectDot(0,0);

		assertFalse(board.isClosedShape());

		board.deselectDot(0,0);
		board.deselectDot(1,0);
		board.deselectDot(2,0);
		board.deselectDot(2,1);
		board.deselectDot(1,1);

		board.selectDot(0,1);
		board.selectDot(1,1);
		board.selectDot(2,1);
		board.selectDot(2,0);
		board.selectDot(1,0);
		board.selectDot(0,0);

		assertTrue(board.isClosedShape());
	}

	@Test
	public void testRemoveDropFill() {
		Board board = new Board(5);
		Dot[][] myBoard = board.getBoard();	

		for(int i = 0; i < myBoard.length; i++) {
			for(int j = 0; j < myBoard.length; j++) {
				myBoard[i][j] = new Dot(1);
			}
		}
		assertTrue(board.getScore() == 0);
		assertTrue(board.canMakeMove());
		myBoard[0][0] = new Dot(2);
		myBoard[1][0] = new Dot(2);
		myBoard[1][1] = new Dot(2);
		myBoard[2][1] = new Dot(2);
		myBoard[2][2] = new Dot(2);
		myBoard[3][2] = new Dot(2);
		myBoard[3][3] = new Dot(2);
		myBoard[4][3] = new Dot(2);
		myBoard[4][4] = new Dot(2);
		assertTrue(board.canMakeMove());
		assertFalse(board.canDeselect(0,0));
		assertFalse(board.canDeselect(1,3));

		board.printBoard("Test for RemoveDropFill, before");
		board.selectDot(0,0);
		board.selectDot(1,0);
		board.selectDot(1,1);
		board.selectDot(2,1);
		board.selectDot(2,2);
		board.selectDot(3,2);
		board.selectDot(3,3);
		board.selectDot(4,3);
		board.selectDot(4,4);

		try{
			board.removeSelectedDots();
		}catch (Exception e) {
			System.out.println("Can't removed, something wrong");
			assertTrue(false);
		}

		board.dropRemainingDots();
		board.fillRemovedDots();
		board.printBoard("Test for RemoveDropFill, after");
		assertTrue(board.getScore() == 9);
		
		assertEquals(myBoard[0][0].getColor(), 1);
		assertEquals(myBoard[0][1].getColor(), 1);
		assertEquals(myBoard[0][2].getColor(), 1);
		assertEquals(myBoard[0][3].getColor(), 1);
		
		assertEquals(myBoard[1][0].getColor(), 1);
		assertEquals(myBoard[1][1].getColor(), 1);
		assertEquals(myBoard[1][2].getColor(), 1);

		assertEquals(myBoard[2][1].getColor(), 1);
		assertEquals(myBoard[2][2].getColor(), 1);

		assertEquals(myBoard[3][2].getColor(), 1);

		//-------------------------
		board.selectDot(0,0);
		board.selectDot(0,1);
		board.selectDot(0,2);
		board.selectDot(0,3);

		try{
			board.removeSelectedDots();
		}catch (Exception e) {
			System.out.println("Can't removed, something wrong");
			assertTrue(false);
		}
		board.dropRemainingDots();
		board.fillRemovedDots();

		board.printBoard("Test for RemoveDropFill, remove edge left down");
		assertTrue(board.getScore() == 13);
		//------------------------------------------------------------
		board.selectDot(3,0);
		board.selectDot(4,0);
		board.selectDot(4,1);
		board.selectDot(4,2);

		try{
			board.removeSelectedDots();
		}catch (Exception e) {
			System.out.println("Can't removed, something wrong");
			assertTrue(false);
		}
		board.dropRemainingDots();
		board.fillRemovedDots();

		board.printBoard("Test for RemoveDropFill, remove edge right down");
		assertTrue(board.getScore() == 17);

	}

	@Test 
	public void testBestSquare()  {
		Board board = new Board(4);
		Dot[][] myBoard = board.getBoard();
		int k = 1;
		for(int y = 0; y < myBoard.length; y++) {
			for(int x = 0; x < myBoard.length; x++) {
				if(k == 6) {
					k = 1;
				}
				myBoard[x][y] = new Dot(k++);
			}
		}

		ArrayList<Point> squares = new ArrayList<Point>(); 
		myBoard[0][1] = new Dot(1);
		myBoard[1][0] = new Dot(1);
		myBoard[myBoard.length - 1][0] = new Dot(3);
		myBoard[myBoard.length - 2][1] = new Dot(3);
		squares.add(new Point(0,0));
		squares.add(new Point(1,0));
		squares.add(new Point(1,1));
		squares.add(new Point(0,1));

		
		board.printBoard("Test for bestSquares, before remove");
		ArrayList<Point> bestSquares = board.findBestSquare();
		for(Point p: bestSquares) {
			board.selectDot(p.x,p.y);
		}
		assertTrue(board.countSquare(1) > board.countSquare(3));
		for (int i = 0; i < bestSquares.size(); i++) {
			Point p1 = bestSquares.get(i);
			Point p2 = squares.get(i);
			assertTrue(p1.x == p2.x && p1.y == p2.y);
		}

		try{
			board.removeSelectedDots();
		}catch (Exception e) {
			System.out.println("Can't removed, something wrong");
			assertTrue(false);
		}

		board.dropRemainingDots();
		board.fillRemovedDots();
		assertEquals(myBoard[0][0].getColor(),4);
		assertEquals(myBoard[1][0].getColor(),5);
		assertEquals(myBoard[0][1].getColor(),3);
		assertEquals(myBoard[1][1].getColor(),4);

		assertEquals(myBoard[2][0].getColor(),3);
		assertEquals(myBoard[3][0].getColor(),3);
		assertEquals(myBoard[2][1].getColor(),3);
		assertEquals(myBoard[3][1].getColor(),3);

		assertEquals(myBoard[2][2].getColor(),5);

		board.printBoard("Test for bestSquares, after remove");
	}

	@Test
	public void testgetScore() {
		Board board = new Board(4);
		Dot[][] myBoard = board.getBoard();
		for(int i = 0; i < myBoard.length; i++) {
			for(int j = 0; j < myBoard.length; j++) {
				myBoard[i][j] = new Dot(3);
			}
		}

		myBoard[0][0] = new Dot(4);
		myBoard[1][0] = new Dot(4);
		myBoard[1][1] = new Dot(4);
		myBoard[2][1] = new Dot(4);
		myBoard[2][2] = new Dot(4);
		myBoard[3][2] = new Dot(4);
		myBoard[3][3] = new Dot(4);

		board.selectDot(0,0);
		board.selectDot(1,0);
		board.selectDot(1,1);
		board.selectDot(2,1);
		board.selectDot(2,2);
		board.selectDot(3,2);
		board.selectDot(3,3);
		assertTrue(board.getScore() == 0);

		board.printBoard("Test for getScore, before remove");
		try{
			board.removeSelectedDots();
		}catch (Exception e) {
			System.out.println("Can't removed, something wrong");
			assertTrue(false);
		}
		board.dropRemainingDots();
		board.fillRemovedDots();
		
		assertTrue(board.getScore() == 7);
		board.printBoard("Test for getScore, after remove");
	}
}
