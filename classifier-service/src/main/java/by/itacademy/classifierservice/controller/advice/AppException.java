package by.itacademy.classifierservice.controller.advice;

import java.util.ArrayList;
import java.util.List;

public class AppException extends IllegalStateException{
    private List<AppError> errors = new ArrayList<>();

    public AppException(String s, List<AppError> errors) {
        super(s);
        this.errors = errors;
    }

    public AppException() {
    }

    public AppException(String s) {
        super(s);
    }

    public AppException(String message, Throwable cause) {
        super(message, cause);
    }

    public AppException(Throwable cause) {
        super(cause);
    }

    public void add(AppError e) {
        this.errors.add(e);
    }
}
