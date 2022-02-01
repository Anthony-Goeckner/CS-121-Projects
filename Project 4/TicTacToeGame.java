import java.awt.Point;
/**
 * implements methods from TicTacToe to build a functioning model
 * of the TicTacToe game.
 * @author anthonygoeckner
 * @version Fall 21
 */
public class TicTacToeGame implements TicTacToe {
    private BoardChoice[][] bcArray = new BoardChoice[3][3];
    private Point[] pointArray = new Point[9];
    private GameState gameState;
    private BoardChoice lastPlayer;
    private int turnNum;

    public TicTacToeGame() {
        newGame();
    }

    @Override
    /**
     * resets all instance variables to start new game
     */
    public void newGame() {
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                bcArray[i][j] = BoardChoice.OPEN;
            }
        }
        for (int i = 0; i < 9; ++i) {
            pointArray[i] = null;
        }
        gameState = GameState.IN_PROGRESS;
        turnNum = 0;
    }

    @Override
    /**
     * fills grid at position provided in params if position is available
     * @param player expecting BoardChoice.X or BoardChoice.O
     * @param ror which row to fill with player
     * @param col column to fill with player
     * @return true if valid position, else false
     */
    public boolean choose(TicTacToe.BoardChoice player, int row, int col) {
        if (gameState != GameState.IN_PROGRESS) { // cannot choose if game is over
            return false;
        } else if (bcArray[row][col] != BoardChoice.OPEN) { // cannot choose if grid position not OPEN
            return false;
        } 
        else {
            if (player == lastPlayer) { // same player cannot go twice
                return false;
            }
            lastPlayer = player;
            bcArray[row][col] = player;
            pointArray[turnNum] = new Point(row, col);
            turnNum++;
            gameOver(); // check if game is over and change gameState
            return true;
        }
    }

    @Override
    /**
     * Checks if game has been won or is a tie
     * @return true if game is over, else false
     */
    public boolean gameOver() {
        BoardChoice first;
        BoardChoice second;
        BoardChoice third;
        // first checks rows for three of the same BoardChoice, then
        // checks the columns for the same
        for (int i = 0; i < 3; ++i) {
            first = bcArray[i][0];
            second = bcArray[i][1];
            third = bcArray[i][2];
            // if any of the three spaces are open, 
            // cannot be a winner
            if (first != BoardChoice.OPEN) {
                if (first == second && first == third) {
                    if (first == BoardChoice.X) {
                        gameState = GameState.X_WON;
                        return true;
                    } else if (first == BoardChoice.O) {
                        gameState = GameState.O_WON;
                        return true;
                    }
                }
            }

            first = bcArray[0][i];
            second = bcArray[1][i];
            third = bcArray[2][i];
            // if any of the three spaces are open, 
            // cannot be a winner
            if (first != BoardChoice.OPEN) {
                if (first == second && first == third) {
                    if (first == BoardChoice.X) {
                        gameState = GameState.X_WON;
                        return true;
                    } else if (first == BoardChoice.O) {
                        gameState = GameState.O_WON;
                        return true;
                    }
                }
            }
        }
        // checks diagonals
        first = bcArray[0][0];
        second = bcArray[1][1];
        third = bcArray[2][2];
        if (first != BoardChoice.OPEN) {
            if (first == second && first == third) {
                if (first == BoardChoice.X) {
                    gameState = GameState.X_WON;
                    return true;
                } else if (first == BoardChoice.O) {
                    gameState = GameState.O_WON;
                    return true;
                }
            }
        }

        first = bcArray[0][2];
        second = bcArray[1][1];
        third = bcArray[2][0];
        if (first != BoardChoice.OPEN) {
            if (first == second && first == third) {
                if (first == BoardChoice.X) {
                    gameState = GameState.X_WON;
                    return true;
                } else if (first == BoardChoice.O) {
                    gameState = GameState.O_WON;
                    return true;
                }
            }
        }

        if (this.turnNum > 8) {
            gameState = GameState.TIE;
            return true;
        }

        return false;
    }

    @Override
    /**
     * Returns the winner if the game is over, if the game is not over
     * returns IN_PROGRESS
     * @return the winner of the game or IN_PROGRESS
     */
    public TicTacToe.GameState getGameState() {
        return gameState;
    }

    @Override
    /**
     * Returns a deep copy of the game's current grid to enforce
     * encapsulation
     * @return 2D array of current game choices
     */
    public TicTacToe.BoardChoice[][] getGameGrid() {
        BoardChoice[][] newArray = new BoardChoice[3][3];
        // copy bcArray to newArray
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                newArray[i][j] = bcArray[i][j];
            }
        }
        return newArray;
    }

    @Override
    /**
     * returns an array of the moves taken in the current game
     * @return array of Points showing which positions taken and in which order
     */
    public Point[] getMoves() {
        // count the number of objects that are not null
        int count = 0;
        for (Point p : pointArray) {
            if (p != null) {
                count++;
            }
        }
        // length of new array is amount of objects in pointArray that are not null
        Point[] newArray = new Point[count];
        for (int i = 0; i < count; ++i) {
            newArray[i] = pointArray[i];
        }
        return newArray;
    }
    
}