package chess;

/**
 * Represents a single square position on a chess board
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessPosition {

    private final int row;
    private final int col;

    public ChessPosition(int row, int col) {
        this.row = row;
        this.col = col;
    }

    /**
     * @return which row this position is in
     * 1 codes for the bottom row
     */
    public int getRow() {
        return this.row;
    }

    /**
     * @return which column this position is in
     * 1 codes for the left row
     */
    public int getColumn() {
        return this.col;
    }

    @Override
    public int hashCode() {
        // Stack overflow mentions that you want to start with a prime number, and then follow this pattern for int values
        int result = 5;
        result = 31 * result + this.row;
        result = 31 * result + this.col;
        // I dont think we need to handle null values here.
        return result;
    }

    /**
    * @return boolean if same position (row,col)
    *
    * */
    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof ChessPosition comparingPosition)) return false;
        return (comparingPosition.row == this.row) && (comparingPosition.col == this.col);
    }
}
