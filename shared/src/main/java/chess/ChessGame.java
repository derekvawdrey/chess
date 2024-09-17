package chess;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Collection;

/**
 * For a class that can manage a chess game, making moves on a board
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessGame {

    private TeamColor currentTeam;
    private ChessBoard chessBoard;

    public ChessGame() {
        this.currentTeam = TeamColor.WHITE;
        chessBoard = new ChessBoard();
        chessBoard.resetBoard();
    }

    /**
     * @return Which team's turn it is
     */
    public TeamColor getTeamTurn() {
        return this.currentTeam;
    }

    /**
     * Set's which teams turn it is
     *
     * @param team the team whose turn it is
     */
    public void setTeamTurn(TeamColor team) {
        this.currentTeam = team;
    }

    /**
     * Enum identifying the 2 possible teams in a chess game
     */
    public enum TeamColor {
        WHITE,
        BLACK
    }

    /**
     * Gets a valid moves for a piece at the given location
     *
     * @param startPosition the piece to get valid moves for
     * @return Set of valid moves for requested piece, or null if no piece at
     *
     */
    public Collection<ChessMove> validMoves(ChessPosition startPosition) {
        ChessPiece movingPiece = this.chessBoard.getPiece(startPosition);
        if(movingPiece == null) return null;

        Collection<ChessMove> moves = new ArrayList<>(movingPiece.pieceMoves(chessBoard, startPosition));
        Collection<ChessMove> validMoves = new ArrayList<>();
        TeamColor oppositeTeamColor = movingPiece.getTeamColor() == TeamColor.BLACK ? TeamColor.WHITE : TeamColor.BLACK;

        if(movingPiece.getPieceType() == ChessPiece.PieceType.KING){
            // For king, determine if piece would be in danger, and then remove those positions from the moves
            Collection<ChessPosition> enemyCoverage = this.chessBoard.grabTeamColorCoverage(oppositeTeamColor);
            for(ChessMove move : moves){
                if(!enemyCoverage.contains(move.getEndPosition())){
                    validMoves.add(move);
                }
            }
            return validMoves;
        }

        ChessBoard tempBoard = new ChessBoard(this.chessBoard.getBoard());
        // If the king is in check, determine if you can move to put the king out of check.
        // This means you can either block the move, or take the piece
        for(ChessMove move : moves) {
            ChessPiece capturedPiece = tempBoard.setPiece(move.getEndPosition(), movingPiece);
            tempBoard.setPiece(move.getStartPosition(), null);
            if(!this.isInCheck(movingPiece.getTeamColor(), tempBoard)){
                validMoves.add(move);
            }
            // Undo the move
            tempBoard.setPiece(move.getStartPosition(), movingPiece);
            tempBoard.setPiece(move.getEndPosition(), capturedPiece);

        }

        return validMoves;
    }

    /**
     * Makes a move in a chess game
     *
     * @param move chess move to preform
     * @throws InvalidMoveException if move is invalid
     */
    public void makeMove(ChessMove move) throws InvalidMoveException {
        ChessPiece piece = this.chessBoard.getPiece(move.getStartPosition());
        if(piece == null) throw new InvalidMoveException();
        if(this.getTeamTurn() != piece.getTeamColor()){
            throw new InvalidMoveException();
        }


        if(this.validMoves(move.getStartPosition()).contains(move)){

            if(move.getPromotionPiece() != null && piece.getPieceType() == ChessPiece.PieceType.PAWN){
                this.chessBoard.setPiece(move.getEndPosition(), new ChessPiece(piece.getTeamColor(), move.getPromotionPiece()));
            }else{
                this.chessBoard.setPiece(move.getEndPosition(), piece);
            }


            this.chessBoard.setPiece(move.getStartPosition(),null);
            this.setTeamTurn(this.getTeamTurn() == TeamColor.BLACK ? TeamColor.WHITE : TeamColor.BLACK);
        }else{
            throw new InvalidMoveException();
        }
    }

    /**
     * Determines if the given team is in check using the current chess board
     *
     * @param teamColor which team to check for check
     * @return True if the specified team is in check
     */
    public boolean isInCheck(TeamColor teamColor) {
        return isInCheck(teamColor, this.chessBoard);
    }

    /**
     * Determines if a given team is in check, for any given chessboard
     * @param teamColor
     * @param chessBoard
     * @return True if the specified team is in check
     */
    private boolean isInCheck(TeamColor teamColor, ChessBoard chessBoard){
        TeamColor oppositeTeamColor = teamColor == TeamColor.BLACK ? TeamColor.WHITE : TeamColor.BLACK;

        // If the king is included in the enemy coverage
        Collection<ChessPosition> enemyCoverage = chessBoard.grabTeamColorCoverage(oppositeTeamColor);
        ChessPosition kingPosition = chessBoard.findKing(teamColor);

        return enemyCoverage.contains(kingPosition);
    }

    /**
     * Determines if the given team is in checkmate
     *
     * @param teamColor which team to check for checkmate
     * @return True if the specified team is in checkmate
     */
    public boolean isInCheckmate(TeamColor teamColor) {
        if(!this.isInCheck(teamColor)){
            return false;
        }

        Collection<ChessMove> chessMoves = new ArrayList<>();
        Collection<ChessPosition> chessPositions = this.chessBoard.getTeamColorPiecePositions(teamColor);
        for(ChessPosition position : chessPositions){
            Collection<ChessMove> validMovesForPiece = this.validMoves(position);
            if(!validMovesForPiece.isEmpty()){
                return false;
            }
        }

        return true;
    }

    /**
     * Determines if the given team is in stalemate, which here is defined as having
     * no valid moves
     *
     * @param teamColor which team to check for stalemate
     * @return True if the specified team is in stalemate, otherwise false
     */
    public boolean isInStalemate(TeamColor teamColor) {
        ChessPosition kingPosition = chessBoard.findKing(teamColor);
        if(kingPosition == null) return false;

        if(this.isInCheck(teamColor)){
            return false;
        }




        Collection<ChessMove> chessMoves = new ArrayList<>();
        Collection<ChessPosition> chessPositions = this.chessBoard.getTeamColorPiecePositions(teamColor);
        for(ChessPosition position : chessPositions){
            chessMoves.addAll(this.validMoves(position));
        }

        return chessMoves.isEmpty();
    }

    /**
     * Sets this game's chessboard with a given board
     *
     * @param board the new board to use
     */
    public void setBoard(ChessBoard board) {
        this.chessBoard = board;
    }

    /**
     * Gets the current chessboard
     *
     * @return the chessboard
     */
    public ChessBoard getBoard() {
        return chessBoard;
    }
}
