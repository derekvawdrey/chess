package chess.moves;

import chess.ChessBoard;
import chess.ChessMove;
import chess.ChessPiece;
import chess.ChessPosition;
import chess.moves.interfaces.PieceMovesCalculator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RookMovesCalculator extends PieceMovesCalculator {
    /**
     * @param board
     * @param position
     * @param piece
     * @return A collection of valid chess moves (moves that would place the king in danger are not counted for)
     */
    @Override
    public Collection<ChessMove> calculatePieceMoves(
            ChessBoard board, ChessPosition position, ChessPiece piece, boolean ignoreColor) {
        List<ChessMove> moves = new ArrayList<>();

        // Left to Right, top to bottom. Do each 4 and then when they detect a piece, those are the moves it can do up to that point
        moves.addAll(calculateSingleDirection(board, position, piece, Direction.UP, ignoreColor));
        moves.addAll(calculateSingleDirection(board, position, piece, Direction.DOWN, ignoreColor));
        moves.addAll(calculateSingleDirection(board, position, piece, Direction.LEFT, ignoreColor));
        moves.addAll(calculateSingleDirection(board, position, piece, Direction.RIGHT, ignoreColor));

        return moves;
    }

    private enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    /**
     * Calculates the moves for this piece in a single direction
     * @param board
     * @param position
     * @param piece
     * @param direction
     * @return a set of moves
     */
    private Collection<ChessMove> calculateSingleDirection(
            ChessBoard board, ChessPosition position, ChessPiece piece, Direction direction, boolean ignoreColor) {
        List<ChessMove> moves = new ArrayList<>();
        int incrementer = (direction == Direction.UP || direction == Direction.RIGHT) ? 1 : -1;
        int anchorPoint = (direction == Direction.UP || direction == Direction.DOWN)
                ? position.getRow() + incrementer: position.getColumn() + incrementer;
        int endPoint = (direction == Direction.UP || direction == Direction.RIGHT) ? 9 : 0;

        for(int i = anchorPoint; direction == Direction.DOWN || direction == Direction.LEFT ? i > endPoint : i < endPoint; i+=incrementer) {
            ChessPosition endPosition;
            if(direction == Direction.UP || direction == Direction.DOWN) {
                endPosition = new ChessPosition(i, position.getColumn());
            }else{
                endPosition = new ChessPosition(position.getRow(), i);
            }

            if(!board.hasPiece(endPosition)) {
                moves.add(new ChessMove(position, endPosition,null));
            }else{
                // If the piece is of the opposite color, we can take it
                if(!board.isPieceSameColor(position, endPosition)){
                    moves.add(new ChessMove(position, endPosition,null));
                }else if(ignoreColor){
                    moves.add(new ChessMove(position, endPosition,null));
                }
                break;
            }
        }
        return moves;
    }
}
