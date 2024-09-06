package chess.moves;

import chess.ChessBoard;
import chess.ChessMove;
import chess.ChessPiece;
import chess.ChessPosition;
import chess.moves.interfaces.PieceMovesCalculator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class QueenMovesCalculator implements PieceMovesCalculator {
    /**
     * @param board
     * @param position
     * @param piece
     * @return A collection of valid chess moves (moves that would place the king in danger are not counted for)
     */
    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition position, ChessPiece piece) {
        // This is ltierally a combination of Rook and Bishop moves, we can just combine them here.
        Collection<ChessMove> moves = new ArrayList<>();
        moves.addAll(new BishopMovesCalculator().pieceMoves(board, position, piece));
        moves.addAll(new RookMovesCalculator().pieceMoves(board, position, piece));
        return moves;
    }

}
