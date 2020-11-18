package javademo.exception;

public class ReturnIsExistException extends Exception{
    public ReturnIsExistException() {
        super();
    }

    public ReturnIsExistException(String message) {
        super(message);
    }

    public ReturnIsExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public ReturnIsExistException(Throwable cause) {
        super(cause);
    }

    protected ReturnIsExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
