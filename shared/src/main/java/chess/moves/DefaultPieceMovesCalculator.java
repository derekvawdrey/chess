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
        return switch (piece.getPieceType()) {
            case ChessPiece.PieceType.ROOK -> new RookMovesCalculator();
            case ChessPiece.PieceType.KNIGHT -> new KnightMovesCalculator();
            case ChessPiece.PieceType.BISHOP -> new BishopMovesCalculator();
            case ChessPiece.PieceType.QUEEN -> new QueenMovesCalculator();
            case ChessPiece.PieceType.KING -> new KingMovesCalculator();
            case ChessPiece.PieceType.PAWN -> new PawnMovesCalculator();
            default -> throw new IllegalArgumentException("Unknown piece type: " + piece.getPieceType());
        };
    }
}
