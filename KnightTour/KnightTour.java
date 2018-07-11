package knighttour;

import java.util.ArrayList;

/**
 *
 * @author Dell
 */
public class KnightTour {

    /**
     * @effects generate 1 random number
     * @param range
     */
    public static int randInt(int range) {
        int result = (int) (Math.random() * range);
        return result;
    }

    /**
     * @effects fill the board with 0s ( reset the board )
     */
    public static void fillBoard(int[] board) {
        for (int i = 0; i < 8; i++) {
            board[i] = 0;
        }
    }

    /**
     * Prints out the board
     *
     * @param board
     */
    public static void printBoard(int[] board) {
        for (int i = 0; i < board.length; i++) {
            System.out.print(i + " ");
            if ((i + 1) % 8 == 0) {
                System.out.print("\n");
            }
        }
        System.out.print("\n");
    }

    /**
     * check if the challenge is finished or not
     *
     * @param board
     * @return boolean(done)
     */
    public static boolean isFinished(int[] board) {
        boolean done = true;
        for (int i = 0; i < board.length; i++) {
            if (board[i] == 0) {
                done = false;
            }
        }
        return done;
    }

    /**
     * Calculate possible moves that the knight can make even in corner
     * positions and return the result in form of coordinates in array list ex :
     * {6,10,15,17}
     *
     * @param currentPosition
     * @return array
     */
    public static ArrayList calculateMoves(int currentPosition) {
        //From the current position -> return correspoding possible moves
        // Store possible moves in the array list
        ArrayList<Integer> temp = new ArrayList<>();
        // normally there are 8 possible moves
        if (currentPosition % 8 != 0 && currentPosition % 8 != 1) {
            if(currentPosition < 56)
                temp.add(6);
            if(currentPosition > 7 )
                temp.add(-10); 
        }
        if (currentPosition % 8 != 6 && currentPosition % 8 != 7 ) {
            if(currentPosition < 56)
                temp.add(10);
            
            if(currentPosition > 7)
                temp.add(-6);     
        }
        if (currentPosition % 8 != 0 ) {
            if(currentPosition < 48)
                temp.add(15); 
            if(currentPosition > 15)
                temp.add(-17);        
        }
        if (currentPosition % 8 != 7  ) {
            if(currentPosition < 48)
               temp.add(17); 
            if(currentPosition > 15)
                temp.add(-15);
        }
        /**
         * temp.add(-6); temp.add(-10); temp.add(-15); temp.add(-17);
         */

        // Special cases in column 0,1,6,7
        return temp;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Init the board 
        int[] board = new int[64];
        //fill board with 0s ( reset )
        fillBoard(board);
        //Place knight  
        ArrayList<Integer> test = new ArrayList<>();
        //CURRENT POSITION
        int currentPos = randInt(63);
        board[currentPos] = 7;
        // Print the original state
        printBoard(board);
        // Challenge Loop
        test = calculateMoves(currentPos);

        //
        boolean yes = true;
        if (!test.isEmpty() && yes == false) {
            int random = randInt(test.size());
            board[currentPos] = 1;
            currentPos += test.get(random);
            board[currentPos] = 7;

            printBoard(board);
        } else {
            System.out.println("Out of moves");
        }
        //TEST ZONE
        System.out.println("current pos : " + currentPos);
        System.out.println("Possible moves");

        for (Integer x : test) {
            System.out.print(x + " ");
        }
        System.out.println("");
        // foreach loop
        /**
         * for (Integer x : test){ System.out.println(x); }
         */
        ///////////////////////////////////
        // Possible moves for the knight 
        ///////////////////////////////////
        //printboard
    }
}
