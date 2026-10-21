package exceptions;

public class FigureFormatException extends Exception {
    private final int lineNumber;

    public FigureFormatException(int lineNumber, String reason) {
        super("Строка " + lineNumber + ": " + reason);
        this.lineNumber = lineNumber;
    }

    public FigureFormatException(int lineNumber, String reason, Throwable cause) {
        super("Строка " + lineNumber + ": " + reason, cause);
        this.lineNumber = lineNumber;
    }

    public int getLineNumber() {
        return lineNumber;
    }
}