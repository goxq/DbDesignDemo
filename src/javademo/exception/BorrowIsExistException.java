package javademo.exception;

public class BorrowIsExistException extends Exception {
    public BorrowIsExistException() {
        super();
    }

    public BorrowIsExistException(String message) {
        super(message);
    }

    public BorrowIsExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public BorrowIsExistException(Throwable cause) {
        super(cause);
    }

    protected BorrowIsExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
