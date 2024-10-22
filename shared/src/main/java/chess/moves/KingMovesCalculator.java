package chess.moves;

import chess.ChessBoard;
import chess.ChessMove;
import chess.ChessPiece;
import chess.ChessPosition;
import chess.moves.interfaces.PieceMovesCalculator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class KingMovesCalculator extends PieceMovesCalculator {
    /**
     * @param board
     * @param position
     * @param piece
     * @return A collection of valid chess moves (moves that would place the king in danger are not counted for)
     */
    @Override
    public Collection<ChessMove> calculatePieceMoves(ChessBoard board, ChessPosition position, ChessPiece piece, boolean ignoreColor) {
        List<ChessMove> moves = new ArrayList<>();
        List<ChessPosition> positions = new ArrayList<>();

        positions.add(new ChessPosition(position.getRow()+1, position.getColumn()));
        positions.add(new ChessPosition(position.getRow()+1, position.getColumn()+1));
        positions.add(new ChessPosition(position.getRow(), position.getColumn()+1));
        positions.add(new ChessPosition(position.getRow()+1, position.getColumn()-1));
        positions.add(new ChessPosition(position.getRow(), position.getColumn()-1));
        positions.add(new ChessPosition(position.getRow()-1, position.getColumn()-1));
        positions.add(new ChessPosition(position.getRow()-1, position.getColumn()));
        positions.add(new ChessPosition(position.getRow()-1, position.getColumn()+1));

        return getChessMoves(board, position, ignoreColor, moves, positions);
    }
}
