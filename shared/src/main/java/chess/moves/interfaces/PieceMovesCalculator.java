package chess.moves.interfaces;

import chess.ChessBoard;
import chess.ChessMove;
import chess.ChessPiece;
import chess.ChessPosition;

import java.util.ArrayList;
import java.util.Collection;

public abstract class PieceMovesCalculator {

    /**
     *
     * @param board
     * @param position
     * @param piece
     * @param ignoreColor
     * @return
     */
    public Collection<ChessMove> calculatePieceMoves(ChessBoard board, ChessPosition position, ChessPiece piece, boolean ignoreColor){
        return null;
    }

    /**
     * Returns a collection of possible moves the piece can do, excluding other color pieces, and validity of moves.
     * @param board
     * @param position
     * @param piece
     * @return
     */
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition position, ChessPiece piece) {
        return calculatePieceMoves(board, position, piece, false);
    }

    /**
     * Returns a collection of possible moves the piece can take or move
     * @param board
     * @param position
     * @param piece
     * @return
     */
    public Collection<ChessMove> pieceMovesIgnoreColor(ChessBoard board, ChessPosition position, ChessPiece piece) {
        return calculatePieceMoves(board, position, piece, true);
    }

    /**
     * Calculates the chess positions that the piece covers
     * @param board
     * @param position
     * @param piece
     * @return A collection of chess positions
     */
    public Collection<ChessPosition> pieceCoverage(ChessBoard board, ChessPosition position, ChessPiece piece) {
        Collection<ChessMove> moves = pieceMovesIgnoreColor(board, position, piece);
        Collection<ChessPosition> coverage = new ArrayList<>();
        for (ChessMove move : moves) {
            coverage.add(move.getEndPosition());
        }
        return coverage;
    }

}
