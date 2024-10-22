package chess;

import java.util.Objects;

/**
 * Represents moving a chess piece on a chessboard
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessMove {

    private final ChessPosition startPosition;
    private final ChessPosition endPosition;
    private final ChessPiece.PieceType promotionPiece;

    public ChessMove(ChessPosition startPosition, ChessPosition endPosition,
                     ChessPiece.PieceType promotionPiece) {
        this.startPosition = startPosition;
        this.endPosition = endPosition;
        this.promotionPiece = promotionPiece;
    }

    /**
     * @return ChessPosition of starting location
     */
    public ChessPosition getStartPosition() {
        return this.startPosition;
    }

    /**
     * @return ChessPosition of ending location
     */
    public ChessPosition getEndPosition() {
        return this.endPosition;
    }

    /**
     * Gets the type of piece to promote a pawn to if pawn promotion is part of this
     * chess move
     *
     * @return Type of piece to promote a pawn to, or null if no promotion
     */
    public ChessPiece.PieceType getPromotionPiece() {
        return this.promotionPiece;
    }

    @Override
    public boolean equals(Object obj) {
        boolean result = true;
        if (!(obj instanceof ChessMove other)){
            return false;
        }

        result = this.startPosition.equals(other.startPosition);
        result = result && this.endPosition.equals(other.endPosition);
        result = result && Objects.equals(this.promotionPiece, other.promotionPiece);
        return result;
    }

    @Override
    public int hashCode() {
        int result = 7;
        result = 31 * result + this.startPosition.hashCode();
        result = 31 * result + this.endPosition.hashCode();
        result = 31 * result + (this.promotionPiece != null ? this.promotionPiece.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        String start = startPosition.toString();
        String end = endPosition.toString();
        String promotion = (promotionPiece != null) ? "=" + promotionPiece.toString().charAt(0) : "";
        return start + " to " + end + promotion;
    }
}
