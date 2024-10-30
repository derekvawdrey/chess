package chess;

import chess.moves.DefaultPieceMovesCalculator;
import chess.moves.interfaces.PieceMovesCalculator;
import chess.moves.interfaces.PieceMovesCalculatorFactory;

import java.util.Collection;

/**
 * Represents a single chess piece
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessPiece {

    private final ChessPiece.PieceType pieceType;
    private final ChessGame.TeamColor teamColor;
    private final DefaultPieceMovesCalculator pieceMovesCalculatorFactory;

    public ChessPiece(ChessGame.TeamColor pieceColor, ChessPiece.PieceType type) {
        this.teamColor = pieceColor;
        this.pieceType = type;
        this.pieceMovesCalculatorFactory = new DefaultPieceMovesCalculator();
    }

    /**
     * The various different chess piece options
     */
    public enum PieceType {
        KING,
        QUEEN,
        BISHOP,
        KNIGHT,
        ROOK,
        PAWN
    }

    /**
     * @return Which team this chess piece belongs to
     */
    public ChessGame.TeamColor getTeamColor() {
        return this.teamColor;
    }

    /**
     * @return which type of chess piece this piece is
     */
    public PieceType getPieceType() {
        return this.pieceType;
    }

    /**
     * Calculates all the positions a chess piece can move to
     * Does not take into account moves that are illegal due to leaving the king in
     * danger
     *
     * @return Collection of valid moves
     */
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {
        return pieceMovesCalculatorFactory.createCalculator(this).pieceMoves(board, myPosition, this);
    }

    /**
     * Determines the squares the piece covers
     *
     * @param board
     * @param myPosition
     * @return grabs all the positions the piece covers
     */
    public Collection<ChessPosition> pieceCoverage(ChessBoard board, ChessPosition myPosition) {
        PieceMovesCalculator calculator = pieceMovesCalculatorFactory.createCalculator(this);
        return calculator.pieceCoverage(board, myPosition, this);
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof ChessPiece comparingPiece)){
            return false;
        }
        return (this.pieceType.equals(comparingPiece.pieceType)) && (this.teamColor.equals(comparingPiece.teamColor));
    }

    @Override
    public int hashCode() {
        int result = 7;
        result = 31 * result + this.pieceType.hashCode();
        result = 31 * result + this.teamColor.hashCode();
        return result;
    }
}
