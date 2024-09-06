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
        int colorMod = piece.getTeamColor() == ChessGame.TeamColor.BLACK ? -1 : 1;
        List<ChessMove> moves = new ArrayList<>();

        ChessPosition oneAhead = new ChessPosition(position.getRow() + (colorMod), position.getColumn());
        ChessPosition twoAhead = new ChessPosition(position.getRow() + (colorMod*2), position.getColumn());
        ChessPosition diagonal1 = new ChessPosition(position.getRow() + colorMod, position.getColumn() - colorMod);
        ChessPosition diagonal2 = new ChessPosition(position.getRow() + colorMod, position.getColumn() + colorMod);

        // For jumping one ahead
        ChessMove jumpOneAhead = new ChessMove(
                position,
                oneAhead,
                null
        );



        if(!board.hasPiece(jumpOneAhead.getEndPosition())){
            moves.add(jumpOneAhead);
            moves.addAll(movesIfPawnNearPromotion(position, oneAhead, piece));
        }

        // For jumping two ahead
        if(isInOriginalPosition(position, piece)){
            ChessMove jumpTwoAhead = new ChessMove(position, twoAhead, null);
            if(!board.hasPiece(jumpTwoAhead.getEndPosition())) moves.add(jumpTwoAhead);
        }

        // For capturing an enemy piece (non en passant)
        ChessMove d = new ChessMove(position, diagonal1,null);
        ChessMove d2 = new ChessMove(position, diagonal2, null);
        if(board.hasPiece(d.getEndPosition())) {
            moves.add(d);
            moves.addAll(movesIfPawnNearPromotion(position,diagonal1, piece));
        }
        if(board.hasPiece(d2.getEndPosition())) {
            moves.add(d2);
            moves.addAll(movesIfPawnNearPromotion(position,diagonal2, piece));
        }


        return moves;
    }

    /**
     * Determines if the pawn is in its starting position
     * @param position
     * @param piece
     * @return true/false if the pawn is in its original position
     */
    private boolean isInOriginalPosition(ChessPosition position, ChessPiece piece) {
        if(piece.getTeamColor() == ChessGame.TeamColor.WHITE){
            return position.getRow() == 2;
        }
        return position.getRow() == 7;
    }

    /**
     * Determines if the move is a promotion move, and then adds the moves
     * @param startPosition
     * @param endPosition
     * @param piece
     * @return a collection of chess moves
     */
    private Collection<ChessMove> movesIfPawnNearPromotion(ChessPosition startPosition, ChessPosition endPosition, ChessPiece piece) {
        List<ChessMove> moves = new ArrayList<>();
        // If they are near promotion
        if((piece.getTeamColor() == ChessGame.TeamColor.WHITE && startPosition.getRow() == 7)
                || (piece.getTeamColor() == ChessGame.TeamColor.BLACK && startPosition.getRow() == 2)){
            ChessMove promoteQueen  = new ChessMove(startPosition, endPosition, ChessPiece.PieceType.QUEEN);
            ChessMove promoteKnight  = new ChessMove(startPosition, endPosition, ChessPiece.PieceType.KNIGHT);
            ChessMove promoteBishop  = new ChessMove(startPosition, endPosition, ChessPiece.PieceType.BISHOP);
            ChessMove promoteRook  = new ChessMove(startPosition, endPosition, ChessPiece.PieceType.ROOK);

            moves.add(promoteQueen);
            moves.add(promoteKnight);
            moves.add(promoteBishop);
            moves.add(promoteRook);
        }
        return moves;
    }
}
