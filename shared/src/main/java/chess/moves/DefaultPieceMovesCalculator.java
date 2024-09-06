package chess.moves;

import chess.ChessPiece;
import chess.moves.interfaces.PieceMovesCalculator;
import chess.moves.interfaces.PieceMovesCalculatorFactory;

public class DefaultPieceMovesCalculator implements PieceMovesCalculatorFactory {
    /**
     * @param piece
     * @return PieceMovesCalculator
     */
    @Override
    public PieceMovesCalculator createCalculator(ChessPiece piece) {
        switch (piece.getPieceType()) {
            case ChessPiece.PieceType.ROOK:
                return new RookMovesCalculator();
            case ChessPiece.PieceType.KNIGHT:
                return new KnightMovesCalculator();
            case ChessPiece.PieceType.BISHOP:
                return new BishopMovesCalculator();
            case ChessPiece.PieceType.QUEEN:
                return new QueenMovesCalculator();
            case ChessPiece.PieceType.KING:
                return new KingMovesCalculator();
            case ChessPiece.PieceType.PAWN:
                return new PawnMovesCalculator();
            default:
                throw new IllegalArgumentException("Unknown piece type: " + piece.getPieceType());
        }
    }
}
