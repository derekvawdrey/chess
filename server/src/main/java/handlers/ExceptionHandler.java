package handlers;

public class ExceptionHandler extends Exception {
    private final int statusCode;
    public ExceptionHandler(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return this.statusCode;
    }
}
