package chess.moves;

import chess.ChessBoard;
import chess.ChessMove;
import chess.ChessPiece;
import chess.ChessPosition;
import chess.moves.interfaces.PieceMovesCalculator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BishopMovesCalculator extends PieceMovesCalculator {
    /**
     * @param board
     * @param position
     * @param piece
     * @return A collection of valid chess moves (moves that would place the king in danger are not counted for)
     */
    @Override
    public Collection<ChessMove> calculatePieceMoves(ChessBoard board, ChessPosition position, ChessPiece piece, boolean ignoreColor) {
        Collection<ChessMove> moves = new ArrayList<>();

        moves.addAll(calculateMoveByDirection(board, position, piece, Direction.topLeft, ignoreColor));
        moves.addAll(calculateMoveByDirection(board, position, piece, Direction.topRight, ignoreColor));
        moves.addAll(calculateMoveByDirection(board, position, piece, Direction.bottomLeft, ignoreColor));
        moves.addAll(calculateMoveByDirection(board, position, piece, Direction.bottomRight, ignoreColor));

        return moves;
    }

    private enum Direction {
        topRight,
        topLeft,
        bottomLeft,
        bottomRight
    }

    private Collection<ChessMove> calculateMoveByDirection(ChessBoard board, ChessPosition position, ChessPiece piece, Direction direction, boolean ignoreColor) {
        Collection<ChessMove> moves = new ArrayList<>();
        int rowIncrement = (direction == Direction.topLeft || direction == Direction.topRight) ? 1 : -1;
        int colIncrement = (direction == Direction.topRight || direction == Direction.bottomRight) ? 1 : -1;

        for(int i = 1; i < 9; i ++){
            int newRow = position.getRow() + rowIncrement*i;
            int newCol = position.getColumn() + colIncrement*i;
            if(newRow > 8 || newCol > 8 || newRow < 1 || newCol < 1){
                break;
            }
            ChessPosition newPosition = new ChessPosition(newRow, newCol);
            if(board.hasPiece(newPosition) && !board.isPieceSameColor(position, newPosition)){
                moves.add(new ChessMove(position, newPosition, null));
                break;
            } else if(board.hasPiece(newPosition)){
                if(ignoreColor){
                    moves.add(new ChessMove(position, newPosition, null));
                }
                break;
            }
            moves.add(new ChessMove(position, newPosition, null));
        }
        return moves;
    }

}
