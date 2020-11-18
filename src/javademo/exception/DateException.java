package javademo.exception;

public class DateException extends Exception{
    public DateException() {
        super();
    }

    public DateException(String message) {
        super(message);
    }

    public DateException(String message, Throwable cause) {
        super(message, cause);
    }

    public DateException(Throwable cause) {
        super(cause);
    }

    protected DateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
