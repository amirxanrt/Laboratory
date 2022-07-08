package org.example.app.exception;

public class UserLoginAlreadyRegisteredException  extends  RuntimeException{
    public UserLoginAlreadyRegisteredException() {
    }

    public UserLoginAlreadyRegisteredException(String message) {
        super(message);
    }

    public UserLoginAlreadyRegisteredException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserLoginAlreadyRegisteredException(Throwable cause) {
        super(cause);
    }

    public UserLoginAlreadyRegisteredException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
