package by.itacademy.accountschedulerservice.controller.advice;

public class ResponseError {
    private final String longref = "error";
    private String message;

    public ResponseError(String message) {
        this.message = message;
    }

    public String getLongref() {
        return longref;
    }

    public String getMessage() {
        return message;
    }
}
