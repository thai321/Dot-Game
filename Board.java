import java.awt.Point;
import java.util.ArrayList;

public class Board {

    // Our representation of the board, where myBoard[0][0] represents 
    // the bottom left dot.
    private Dot[][] myBoard;
    // Total number of moves allowed for a single game session.
    private static int movesAllowed = 5;

    // DO NOT MODIFY
    public static final int MINSIZE = 4;
    public static final int MAXSIZE = 10;

    // keep track how many time remove method is call
    private int removesCall = 0;

    // previous dot
    private Point previous = null;

    //store all the selected Dots that are same color
    private ArrayList<Point> selectedDots = new ArrayList<Point>();

    //store all the selected Dots in the remove states
    private boolean removeStates[][];

    //scores
    private int scores = 0;

    //removesCount
    private int[] removesCount;



    /**
     * Sets up the board's data and starts up the GUI. N is side length of the
     * board. (Number of dots per side) N will default to 0 if a non-integer is
     * entered as an argument. If there are no arguments, N will default to 10;
     */
    public static void main(String[] args) {
        int n = 0;
        try {
            n = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            n = 0;
        } catch (IndexOutOfBoundsException e) {
            // This line is run if no command line arguments are given.
            // If you wish to modify this line to test, remember to change it back to 
            // n = 10;
            n = 10;
        }
        GUI.initGUI(n);
    }

    /**
     * When the New Game button is clicked, a new randomized board is constructed.
     * Sets up the board with input SIZE, such that the board's side length is SIZE.
     * Note: The Board is always a square, so SIZE is both the length and the width.
     * Generate a random board such that each entry in board is a random color. 
     * (represented by an int). Should print and error and System.exit if the size 
     * is not within the MINSIZE and MAXSIZE. This constructor will only be called 
     * once per game session. Initialize any variables if needed here.
     */
    public Board(int size) {
        if(size < MINSIZE) {
            throw new IllegalArgumentException("Size is too small");
        }
        else if(size > MAXSIZE){
            throw new IllegalArgumentException("Size is too big");

        }
        myBoard = new Dot[size][size];
        removeStates = new boolean[size][size];
        removesCount = new int[size];

        for(int i = 0; i < removesCount.length; i++) {
            removesCount[0] = 0;
        }

        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size;j++) {
                myBoard[i][j] = new Dot();
                removeStates[i][j] = false;
            }
        }
    }
    
    /**
     * This constructor takes in a 2D int array of colors and generates a preset board
     * with each dot matching the color of the corresponding entry in the int[][] 
     * arguement. This constructor can be used for predetermined tests.
     * You may assume that the input is valid (between MINSIZE and MAXSIZE etc.) 
     * since this is for your own testing.
     */
    public Board(int[][] preset) {
        removeStates = new boolean[preset.length][preset.length];
        removesCount = new int[preset.length];
        myBoard = new Dot [preset.length][preset.length];
        for(int i = 0; i < preset.length; i++) {
            for(int j = 0; j < preset.length;j++) {
                myBoard[i][j] = new Dot(preset[i][j]);
            }
        }
    }
    
    /**
     * Returns the array representation of the board. (Data is used by GUI).
     */
    public Dot[][] getBoard() {
    	return myBoard;
    }

    /**
     * Returns the number of moves allowed per game. This value should not
     * change during a game session.
     */
    public static int getMovesAllowed() {
        return movesAllowed;
        }

    /**
     * Change the number of moves allowed per game. This method can be used for 
     * testing.
     */
    public static void setMovesAllowed(int n) {
        movesAllowed = n;
    }

    /** Returns the number of moves left. */
    public int getMovesLeft() {
        if(removesCall >= movesAllowed) {
            return 0;
        } // create movesleft
    	return movesAllowed-removesCall;
    }

    /**
     * Return whether or not it is possible to make a Move. (ie, there exists
     * two adjacent dots of the same color.) If false, the GUI will report a
     * game over.
     */
    public boolean canMakeMove() {
        int size = myBoard[0].length;
        if(leftConerDown() || rightConerDown() || leftConerUp() || rightConerUp())
            return true;
        if(top() || bottom() ||leftSide() || rightSide())
            return true;
        for(int i = 1; i < size-1; i++) {
            for(int j = 1; j < size-1; j++) {
            if(myBoard[i][j].isSameColor(myBoard[i][j+1]))
                return true;
            else if(myBoard[i][j].isSameColor(myBoard[i][j-1]))
                return true;
            else if(myBoard[i][j].isSameColor(myBoard[i-1][j]))
                return true;
            else if(myBoard[i][j].isSameColor(myBoard[i+1][j]))
                return true;
            }
        }
        return false;
    }
//-------- support canMakeMove()-------------------
    public boolean leftConerDown() {
        if(myBoard[0][0].isSameColor(myBoard[1][0]))
            return true;
        else if(myBoard[0][0].isSameColor(myBoard[0][1]))
            return true;
        return false;
    }
    public boolean rightConerDown() {
        int size = myBoard[0].length;
        if(myBoard[size-1][0].isSameColor(myBoard[size-2][0]))
            return true;
        else if(myBoard[size-1][0].isSameColor(myBoard[size-1][1]))
            return true;
        return false;
    }
    public boolean leftConerUp() {
        int size = myBoard[0].length;
        if(myBoard[0][size-1].isSameColor(myBoard[0][size-2]))
            return true;
        else if(myBoard[0][size-1].isSameColor(myBoard[1][size-1]))
            return true;
        return false;
    }
    public boolean rightConerUp() {
        int size = myBoard[0].length;
        if(myBoard[size-1][size-1].isSameColor(myBoard[size-1][size-2]))
            return true;
        else if(myBoard[size-1][size-1].isSameColor( myBoard[size-2][size-1]))
            return true;
        return false;
    }
    public boolean leftSide() {
        for(int y = 1; y < myBoard[0].length-1; y++){
            if(myBoard[0][y].isSameColor(myBoard[0][y+1]))
                return true;
            else if(myBoard[0][y].isSameColor(myBoard[0][y-1]))
                return true;
            else if(myBoard[0][y].isSameColor(myBoard[1][y]))
                return true;
        }
        return false;
    }
   
    public boolean rightSide(){
        int size = myBoard[0].length;
        for(int y = 1; y < myBoard[0].length-1; y++){
            if(myBoard[size-1][y].isSameColor(myBoard[size-1][y+1]))
                return true;
            else if(myBoard[size-1][y].isSameColor(myBoard[size-1][y-1]))
                return true;
            else if(myBoard[size-1][y].isSameColor(myBoard[size-2][y]))
                return true;
        }
        return false;
    }
    public boolean top() {
        int size = myBoard[0].length;
        for(int x = 1; x < myBoard[0].length-1; x++){
            if(myBoard[x][size-1].isSameColor(myBoard[x][size-2]))
                return true;
            else if(myBoard[x][size-1].isSameColor(myBoard[x-1][size-1]))
                return true;
            else if(myBoard[x][size-1].isSameColor(myBoard[x+1][size-1]))
                return true;
        }
        return false;
    }
    public boolean bottom() {
        for(int x = 1; x < myBoard[0].length-1; x++){
            if(myBoard[x][0].isSameColor(myBoard[x][1]))
                return true;
            else if(myBoard[x][0].isSameColor(myBoard[x-1][0]))
                return true;
            else if(myBoard[x][0].isSameColor(myBoard[x+1][0]))
                return true;
        }
        return false;
    }
//-----------------------------------------------------
    /**
     * Returns if the board is in a state of game over. The game is over if there
     * are no possible moves left or if the player has used up the maximum
     * allowed moves.
     */
    public boolean isGameOver() {
        return !canMakeMove() || getMovesLeft() <= 0;
    }

    /**
     * Returns whether or not you are allowed to select a dot at X, Y at the
     * moment. Remember, if the game is over, you cannot select any dots.
     */
    public boolean canSelect(int x, int y) {
        for(Point p: selectedDots) {
            if(x == p.x && y == p.y) {
                return false;
            }
        }
        if(isGameOver()){ 
            return false;
        }
        else if(previous != null && x == previous.x && y == previous.y) {
            return false;
        }
        else if(selectedDots.size() != 0) {
            Point p = selectedDots.get(selectedDots.size()-1);
            if(isNeighbors(new Point(x,y),p) && myBoard[p.x][p.y].isSameColor(myBoard[x][y])) {
                if(p.x == x && p.y == y) {
                    return false;
                }
                return true;
            }
            else {
                return false;
            }
        }
        return true;


    }
    public boolean isNeighbors(Point p1, Point p2) {
        int size = myBoard[0].length;
        boolean legal1 = false;
        boolean legal2 = false;
        boolean legal3 = false;
        boolean legal4 = false;

        if(p1.x == 0 && p1.y == 0) { // coner left down
            legal1 = (p2.x == 0) && (p2.y == 1);
            legal2 = (p2.x == 1) && (p2.y == 0);
        }
        else if(p1.x == 0 && p1.y == size-1) { //coner left up
            legal1 = (p2.x == 0) && (p2.y == size - 2);
            legal2 = (p2.x == 1)&& (p2.y == size - 1);
        }
        else if(p1.x == size - 1 && p1.y == 0) { // coner right down
            legal1 = (p2.x == size - 1) && (p2.y == 1);
            legal2 = (p2.x == size - 2) && (p2.y == 0);
        }
        else if(p1.y == size - 1) { // top
            legal1 = (p2.x == p1.x) && (p2.y == p1.y -1);
            legal2 = (p2.x == p1.x - 1) && (p2.y == p1.y);
            legal3 = (p2.x == p1.x + 1) && (p2.y == p1.y);
        }
        else if(p1.y == 0) { //bottom
            legal1 = (p2.x == p1.x) && (p2.y == p1.y + 1);
            legal2 = (p2.x == p1.x - 1) && (p2.y == p1.y);
            legal3 = (p2.x == p1.x + 1) && (p2.y == p1.y);
        }
        else if(p1.x == 0) { //left side
            legal1 = (p2.x == p1.x) && (p2.y == p1.y + 1);
            legal2 = (p2.x == p1.x) && (p2.y == p1.y - 1);
            legal3 = (p2.x == p1.x + 1) && (p2.y == p1.y);
        }
        else if(p1.x == size - 1) { //right side
            legal1 = (p2.x == p1.x) && (p2.y == p1.y + 1);
            legal2 = (p2.x == p1.x) && (p2.y == p1.y - 1);
            legal3 = (p2.x == p1.x - 1) && (p2.y == p1.y);
        }
        else { // middle case
            legal1 = (p2.x == p1.x) && (p2.y == p1.y + 1);
            legal2 = (p2.x == p1.x) && (p2.y == p1.y - 1);
            legal3 = (p2.x == p1.x - 1) && (p2.y == p1.y);
            legal4 = (p2.x == p1.x + 1) && (p2.y == p1.y);
        }
        return legal1 || legal2 || legal3 || legal4;
    }
    
    /**
     * Is called when a dot located at myBoard[X][Y] is selected on the GUI.
     */
    public void selectDot(int x, int y) {
        if(canSelect(x,y)) {
            selectedDots.add(new Point(x,y));
            previous = selectedDots.get(selectedDots.size()-1);
        }
    }

    /**
     * Checks if you are allowed to deselect the chosen point.
     * Assumes at least one point has been selected already.
     * You can only deselect the most recent point you have selected.
     * (You can select 3 dots and deselect them in reverse order.)
     */
    public boolean canDeselect(int x, int y) {
        if (previous != null) {
            if(previous.x == x && previous.y == y) {
                return true;
            }
        }
        return false;
    }
    	
    /**Is called when a dot located at myBoard[X][Y] is deselected on the GUI. */
    public void deselectDot(int x, int y) {
        if(selectedDots.size() == 0) {
            previous = null;
        }
        else if(canDeselect(x,y)) {
            selectedDots.remove(selectedDots.size()-1);
            if(selectedDots.size() == 0) {
                previous = null;
            }
            else {
                previous = selectedDots.get(selectedDots.size()-1);
            }
        }

    }

    /**Returns the number of of currently selected dots */
    public int numberSelected() {
    	return selectedDots.size();
    }

    /**
     * Is called when the "Remove" button is clicked. Puts all selected dots in
     * a "removed" state. If no dots should be removed, throw a CantRemoveException. 
     * You must also create your own Exception Class named CantRemoveException.
     * If selected dots form a closed shape, remove all dots on the board that have
     * the same color as the selected dots.
     */
    public void removeSelectedDots() throws CantRemoveException {
        if(selectedDots.size() < 2) {
            throw new CantRemoveException("Error, Cannot remove one selected dot");
        }
        else if(isClosedShape()) {
            removeSameColor();
        }
        else {
            scores += selectedDots.size();
            for(Point p: selectedDots) {
                removeStates[p.x][p.y] = true;
            }

            for(int i = 0; i < removeStates.length; i++) {
                for(int j = 0; j < removeStates.length; j++) {
                    if(removeStates[i][j] == true) {
                        removesCount[i]++; 
                        myBoard[i][j] = new Dot(-1);

                    }   
                }
            }
        }
        previous = null;
        removesCall++;
    }
    //-----------------------------------
    @SuppressWarnings("serial")
	public static class CantRemoveException extends Exception {
        public CantRemoveException(String m) {
            super(m);
        }

        public CantRemoveException(Throwable cause){
            super(cause);
        }

    }

    /**
     * Puts the dot at X, Y in a removed state. Later all dots above a
     * removed dot will drop.
     */
    public void removeSingleDot(int x, int y) {
    }

    /**
     * Return whether or not the selected dots form a closed shape. Refer to
     * diagram for what a closed shape looks like.
     */
    public boolean isClosedShape() {
        int count = 0;
        if(selectedDots.size() < 4) {
            return false;
        }
        else {
            Point p = selectedDots.get(selectedDots.size() - 1);
            for(int i = 0; i < selectedDots.size() - 1; i++) {
                if(isNeighbors(p, selectedDots.get(i))) {
                    count++;
                }
            }
        }
        return count >= 2;
    }

    /**
     * Removes all dots of the same color of the dots on the currently selected
     * dots. Assume it is confirmed that a closed shape has been formed from the
     * selected dots.
     */
    public void removeSameColor() {
        int count = 0;
        Point p = selectedDots.get(0);
        int color = myBoard[p.x][p.y].getColor();
        for(int i = 0; i < myBoard.length; i++) {
            for(int j = 0; j < myBoard.length; j++) {
                if(myBoard[i][j].getColor() == color) {
                    removeStates[i][j] = true;
                    myBoard[i][j] = new Dot(-1);
                    count++;
                }
            }
        }
        scores += count;
    }

    /**
     * Once the dots are removed. Rearrange the board to simulate the dropping of
     * all of the dots above the removed dots. Refer to diagram in the spec for clarity.
     * After dropping the dots, there should exist some "bad" dots at the top. 
     * (These are the blank dots on the 4-stage diagram.)
     * fillRemovedDots will be called immediately after this by the GUI so that random 
     * dots replace these bad dots with new ones that have a randomly generated color.
     */
    public void dropRemainingDots() {
        Dot[] temp = new Dot[myBoard.length];
        int k = 0;
        for(int x = 0; x < myBoard.length; x++) {
            for(int y = 0; y < myBoard.length; y++) {
                if(myBoard[x][y].getColor() != -1) {
                    temp[k++] = new Dot(myBoard[x][y].getColor());
                }
            }
            myBoard[x] = temp;
            temp = new Dot[myBoard.length];
            k = 0;
        }
    }

    /**
     * After removing all dots that were meant to be removed, replace any
     * removed dot with a new dot of a random color.
     */
    public void fillRemovedDots() {
        int size = myBoard.length;
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size;j++) {
                if(myBoard[i][j] == null) {
                    myBoard[i][j] = new Dot();
                }
            }
        }
        selectedDots.clear();
        for(int i = 0; i < removesCount.length; i++) {
            removesCount[0] = 0;
        }
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size;j++) {
                removeStates[i][j] = false;
            }
        }
    }

    /**
     * Return the current score, which is called by the GUI when it needs to
     * update the display of the score. Remember to update the score in your 
     * other methods.
     */
    public int getScore() {
        return scores;
    }

    /**
     * Search the board for a sequence of 4 consecutive points which form a
     * square such that out of all possible 2x2 squares, selecting this one 
     * yields the most points.
     */
    public ArrayList<Point> findBestSquare() {
        int max = 0;
        int currentCount = 0;
        ArrayList<Point> square = new ArrayList<Point>();
        ArrayList<Point> temp = new ArrayList<Point>();

        for(int y = 0; y < myBoard.length-1; y++) {
            for(int x = 0; x < myBoard.length-1; x++) {
                if(isSquare(x,y)) {
                    if(square.size() == 0) {
                        square.add(new Point(x,y));
                        square.add(new Point(x+1,y));
                        square.add(new Point(x+1,y+1));
                        square.add(new Point(x,y+1));
                        max = countSquare(myBoard[x][y].getColor());
                    }
                    else {
                        temp.add(new Point(x,y));
                        temp.add(new Point(x+1,y));
                        temp.add(new Point(x+1,y+1));
                        temp.add(new Point(x,y+1));
                        currentCount = countSquare(myBoard[x][y].getColor());
                        if(max < currentCount) {
                            max = currentCount;
                            square.clear();
                            square.add(new Point(x,y));
                            square.add(new Point(x+1,y));
                            square.add(new Point(x+1,y+1));
                            square.add(new Point(x,y+1));
                        }
                        temp.clear();
                    }
                }
            }
        }

        return square;
    }

    public boolean isSquare(int x, int y) {
        int color = myBoard[x][y].getColor();
        boolean legal1 = color == myBoard[x+1][y].getColor();
        boolean legal2 = color == myBoard[x+1][y+1].getColor();
        boolean legal3 = color == myBoard[x][y+1].getColor();         
        return legal1 && legal2 && legal3;
    }

    public int countSquare(int color) {
        int count = 0;
        for(int y = 0; y < myBoard.length; y++) {
            for(int x = 0; x < myBoard.length; x++) {
                if(myBoard[x][y].getColor() == color) {
                    count++;
                }
            }
        }
        return count;
    }

    /**Prints the the board any way you like for testing purposes. */
    public void printBoard() {
        for(int y = myBoard.length-1; y >= 0; y--) {
            for(int x = 0; x < myBoard.length; x++) {
                System.out.print( myBoard[x][y].getColor() + " ");
            }
            System.out.println();
        }
    }

    public void printBoard(String msg) {
    	System.out.println(msg);
    	printBoard();
    }

}
