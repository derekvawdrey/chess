package chess.moves.interfaces;

import chess.ChessBoard;
import chess.ChessMove;
import chess.ChessPiece;
import chess.ChessPosition;

import java.util.Collection;

public abstract class PieceMovesCalculator {
    /// Will ignore color when determining possible moves.
    protected boolean fullCoverage = false;
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition position, ChessPiece piece) {
        return null;
    }

    public void setFullCoverage(boolean fullCoverage) {
        this.fullCoverage = fullCoverage;
    }
}
