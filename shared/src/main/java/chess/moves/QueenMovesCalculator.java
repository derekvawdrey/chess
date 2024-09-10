package chess.moves;

import chess.ChessBoard;
import chess.ChessMove;
import chess.ChessPiece;
import chess.ChessPosition;
import chess.moves.interfaces.PieceMovesCalculator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class QueenMovesCalculator extends PieceMovesCalculator {
    /**
     * @param board
     * @param position
     * @param piece
     * @return A collection of valid chess moves (moves that would place the king in danger are not counted for)
     */
    @Override
    public Collection<ChessMove> calculatePieceMoves(ChessBoard board, ChessPosition position, ChessPiece piece, boolean ignoreColor) {
        // This is ltierally a combination of Rook and Bishop moves, we can just combine them here.
        Collection<ChessMove> moves = new ArrayList<>();
        moves.addAll(new BishopMovesCalculator().calculatePieceMoves(board, position, piece, ignoreColor));
        moves.addAll(new RookMovesCalculator().calculatePieceMoves(board, position, piece, ignoreColor));
        return moves;
    }

}
