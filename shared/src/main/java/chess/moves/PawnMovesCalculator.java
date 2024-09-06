package chess.moves;

import chess.*;
import chess.moves.interfaces.PieceMovesCalculator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PawnMovesCalculator implements PieceMovesCalculator {
    /**
     * @param board
     * @param position
     * @param piece
     * @return A collection of valid chess moves (moves that would place the king in danger are not counted for)
     */
    @Override
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition position, ChessPiece piece) {
        // Can move forward 2 if in its original spot (6 for black, 1 for white)
        // Can move forward 1 or backwards 1 (forward for white, backward for black)
        // Can move diagonally 1 if there is a piece in that spot

        if(piece.getTeamColor() == ChessGame.TeamColor.WHITE){
            return getWhiteMoves(board, position, piece);
        }else{
            return getBlackMoves(board, position, piece);
        }
    }

    private boolean isInOriginalPosition(ChessPosition position, ChessPiece piece) {
        if(piece.getTeamColor() == ChessGame.TeamColor.WHITE){
            return position.getRow() == 2;
        }
        return position.getRow() == 7;
    }

    private Collection<ChessMove> getWhiteMoves(ChessBoard board, ChessPosition position, ChessPiece piece){
        List<ChessMove> moves = new ArrayList<>();

        return moves;
    }

    private Collection<ChessMove> getBlackMoves(ChessBoard board, ChessPosition position, ChessPiece piece){
        List<ChessMove> moves = new ArrayList<>();
        return moves;
    }
}
