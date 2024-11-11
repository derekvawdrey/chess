package ui;

import chess.ChessBoard;
import chess.ChessGame;
import chess.ChessPiece;
import chess.ChessPosition;

public class PrettyPrintChessBoard {

    public static String prettyPrint(ChessBoard board, ChessGame.TeamColor colorPerspective){
        StringBuilder sb = new StringBuilder();
        int start = colorPerspective == ChessGame.TeamColor.BLACK ? 0 : 7;
        int increment = colorPerspective == ChessGame.TeamColor.BLACK ? 1 : -1;
        int end = colorPerspective == ChessGame.TeamColor.BLACK ? 7 : 0;

        if(colorPerspective == ChessGame.TeamColor.BLACK){
            sb.append("   h  g  f  e  d  c  b  a     ");
        }else{
            sb.append("   a  b  c  d  e  f  g  h     ");
        }

        for(int i = start; i != end + increment; i += increment){
            sb.append(EscapeSequences.RESET_BG_COLOR);
            sb.append("\n");
            sb.append(i+1).append(" ");
            for(int j = start; j != end + increment; j += increment){
                boolean isLight = (i+j)%2 == 0;
                String backgroundColor = isLight ? EscapeSequences.SET_BG_COLOR_LIGHT_GREY : EscapeSequences.SET_BG_COLOR_DARK_GREY;
                sb.append(backgroundColor);
                sb.append(getPieceSymbol(board.getPiece(new ChessPosition(i+1,j+1))));
            }
            sb.append(EscapeSequences.RESET_BG_COLOR);
            sb.append(" ");
            sb.append(i+1).append(" ");
        }
        sb.append("\n");
        if(colorPerspective == ChessGame.TeamColor.BLACK){
            sb.append("   h  g  f  e  d  c  b  a     ");
        }else{
            sb.append("   a  b  c  d  e  f  g  h     ");
        }

        sb.append(EscapeSequences.RESET_BG_COLOR);
        sb.append("\n");

        return sb.toString();
    }

    public static String getPieceSymbol(ChessPiece piece){
        if(piece == null){
            return "   ";
        }
        boolean isWhite = piece.getTeamColor() == ChessGame.TeamColor.WHITE;

        String stringPiece = switch (piece.getPieceType()) {
            case KING -> isWhite ? EscapeSequences.WHITE_KING : EscapeSequences.BLACK_KING;
            case QUEEN -> isWhite ? EscapeSequences.WHITE_QUEEN : EscapeSequences.BLACK_QUEEN;
            case ROOK -> isWhite ? EscapeSequences.WHITE_ROOK : EscapeSequences.BLACK_ROOK;
            case BISHOP -> isWhite ? EscapeSequences.WHITE_BISHOP : EscapeSequences.BLACK_BISHOP;
            case KNIGHT -> isWhite ? EscapeSequences.WHITE_KNIGHT : EscapeSequences.BLACK_KNIGHT;
            case PAWN -> isWhite ? EscapeSequences.WHITE_PAWN : EscapeSequences.BLACK_PAWN;
        };

        return stringPiece;
    }

}
