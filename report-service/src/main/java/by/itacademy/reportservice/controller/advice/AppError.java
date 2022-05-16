package by.itacademy.reportservice.controller.advice;

public class AppError {
    private String field;
    private String message;

    public AppError(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public String getMessage() {
        return message;
    }
}
