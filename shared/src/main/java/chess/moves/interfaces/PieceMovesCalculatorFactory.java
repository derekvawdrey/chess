package chess.moves.interfaces;

import chess.ChessPiece;

public interface PieceMovesCalculatorFactory {
    PieceMovesCalculator createCalculator(ChessPiece piece);
}
