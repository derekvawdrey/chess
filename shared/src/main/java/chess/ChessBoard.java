package chess;

import java.util.Objects;

/**
 * A chessboard that can hold and rearrange chess pieces.
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessBoard {

    private final ChessPiece[][] board;

    public ChessBoard() {
        this.board = new ChessPiece[8][8];
    }

    /**
     * Adds a chess piece to the chessboard
     *
     * @param position where to add the piece to
     * @param piece    the piece to add
     */
    public void addPiece(ChessPosition position, ChessPiece piece) {
        // Does this need to throw an exception if a piece is added to a place where a piece already exists?
        this.board[position.getRow()-1][position.getColumn()-1] = piece;
    }

    /**
     * Gets a chess piece on the chessboard
     *
     * @param position The position to get the piece from
     * @return Either the piece at the position, or null if no piece is at that
     * position
     */
    public ChessPiece getPiece(ChessPosition position) {
        return this.board[position.getRow()-1][position.getColumn()-1];
    }

    /**
     * A helper function to determine if a piece and can take another piece
     * @param pos1
     * @param pos2
     * @return a boolean
     */
    public boolean isPieceSameColor(ChessPosition pos1, ChessPosition pos2) {
        ChessPiece piece1 = getPiece(pos1);
        ChessPiece piece2 = getPiece(pos2);
        return piece1.getTeamColor() == piece2.getTeamColor();
    }

    /**
     * Checks if there is a piece in a position, similar to getPiece.
     * @param position
     * @return
     */
    public boolean hasPiece(ChessPosition position) {
        return this.board[position.getRow()-1][position.getColumn()-1] != null;
    }

    /**
     * Sets the board to the default starting board
     * (How the game of chess normally starts)
     */
    public void resetBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                this.board[i][j] = null;
            }
        }
        this.placeStartingPieces(ChessGame.TeamColor.BLACK);
        this.placeStartingPieces(ChessGame.TeamColor.WHITE);
    }

    /**
     * Adds the starting pieces for the team specified
     * @param teamColor the color of the team
     */
    private void placeStartingPieces(ChessGame.TeamColor teamColor){
        int row = teamColor == ChessGame.TeamColor.WHITE ? 0 : 7;
        int pawnRow = teamColor == ChessGame.TeamColor.WHITE ? 1 : 6;
        this.board[row][0] = new ChessPiece(teamColor, ChessPiece.PieceType.ROOK);
        this.board[row][1] = new ChessPiece(teamColor, ChessPiece.PieceType.KNIGHT);
        this.board[row][2] = new ChessPiece(teamColor, ChessPiece.PieceType.BISHOP);
        this.board[row][3] = new ChessPiece(teamColor, ChessPiece.PieceType.QUEEN);
        this.board[row][4] = new ChessPiece(teamColor, ChessPiece.PieceType.KING);
        this.board[row][5] = new ChessPiece(teamColor, ChessPiece.PieceType.BISHOP);
        this.board[row][6] = new ChessPiece(teamColor, ChessPiece.PieceType.KNIGHT);
        this.board[row][7] = new ChessPiece(teamColor, ChessPiece.PieceType.ROOK);

        for(int col = 0; col < 8; col++) {
            this.board[pawnRow][col] = new ChessPiece(teamColor, ChessPiece.PieceType.PAWN);
        }
    }
    
    @Override
    public int hashCode() {


        // TODO: IMPLEMENT
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof ChessBoard comparingBoard)) return false;
        // Starts at 1 and goes to 8 because the tests are written in a way that don't necessarily work with Arrays
        for(int i = 1; i < 9; i++) {
            for(int j = 1; j < 9; j++) {
                ChessPosition position = new ChessPosition(i, j);
                ChessPiece thisPiece = this.getPiece(position);
                ChessPiece comparingPiece = comparingBoard.getPiece(position);

                if (!Objects.equals(thisPiece, comparingPiece)) return false;
            }
        }
        return true;
    }
}
