package chess.moves;

import chess.ChessBoard;
import chess.ChessMove;
import chess.ChessPiece;
import chess.ChessPosition;
import chess.moves.interfaces.PieceMovesCalculator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RookMovesCalculator implements PieceMovesCalculator {
    /**
     * @param board
     * @param position
     * @param piece
     * @return A collection of valid chess moves (moves that would place the king in danger are not counted for)
     */
    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition position, ChessPiece piece) {
        List<ChessMove> moves = new ArrayList<>();

        // Left to Right, top to bottom. Do each 4 and then when they detect a piece, those are the moves it can do up to that point

        for(int colLeft = position.getColumn() - 1; colLeft < 0; colLeft++) {
            ChessPosition end_position = new ChessPosition(colLeft, position.getRow()));
            if(!board.hasPiece(end_position)) {
                moves.add(new ChessMove(position, end_position,null));
            }else{

                break;
            }
        }

        for(int colRight = position.getColumn() + 1; colRight < 9; colRight++) {

        }

        for(int rowTop = position.getRow() + 1; rowTop < 9; rowTop++) {

        }

        for(int rowBottom = position.getRow() - 1; rowBottom < 0; rowBottom++) {

        }

        return moves;
    }
}
