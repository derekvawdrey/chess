package chess.moves;

import chess.ChessBoard;
import chess.ChessMove;
import chess.ChessPiece;
import chess.ChessPosition;
import chess.moves.interfaces.PieceMovesCalculator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class KnightMovesCalculator extends PieceMovesCalculator {
    /**
     * @param board
     * @param position
     * @param piece
     * @return A collection of valid chess moves (moves that would place the king in danger are not counted for)
     */
    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition position, ChessPiece piece) {
        List<ChessMove> moves = new ArrayList<>();
        List<ChessPosition> positions = new ArrayList<>();

        positions.add(new ChessPosition(position.getRow()+1, position.getColumn()-2));
        positions.add(new ChessPosition(position.getRow()+2, position.getColumn()-1));
        positions.add(new ChessPosition(position.getRow()+1, position.getColumn()+2));
        positions.add(new ChessPosition(position.getRow()+2, position.getColumn()+1));
        positions.add(new ChessPosition(position.getRow()-1, position.getColumn()-2));
        positions.add(new ChessPosition(position.getRow()-2, position.getColumn()-1));
        positions.add(new ChessPosition(position.getRow()-1, position.getColumn()+2));
        positions.add(new ChessPosition(position.getRow()-2, position.getColumn()+1));

        positions.forEach(new_position -> {

            if((new_position.getColumn() > 0 && new_position.getColumn() < 9) && (new_position.getRow() > 0 && new_position.getRow() < 9)) {
                if(board.hasPiece(new_position) && !board.isPieceSameColor(new_position,position)){
                    moves.add(new ChessMove(position,new_position,null));
                } else if(fullCoverage && board.hasPiece(new_position)){
                    moves.add(new ChessMove(position, new_position,null));
                } else if(!board.hasPiece(new_position)){
                    moves.add(new ChessMove(position,new_position,null));
                }
            }
        });


        return moves;
    }
}
