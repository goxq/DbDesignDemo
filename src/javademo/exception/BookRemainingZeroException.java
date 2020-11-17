package javademo.exception;

public class BookRemainingZeroException extends Exception{
    public BookRemainingZeroException() {
        super();
    }

    public BookRemainingZeroException(String message) {
        super(message);
    }

    public BookRemainingZeroException(String message, Throwable cause) {
        super(message, cause);
    }

    public BookRemainingZeroException(Throwable cause) {
        super(cause);
    }

    protected BookRemainingZeroException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
