package by.itacademy.accountservice.controller.advice;

import java.util.ArrayList;
import java.util.List;

public class MultiplyResponseError {
    private String logref = "structured_error";
    private List<ValidationError> errors = new ArrayList<>();

    public String getLogref() {
        return logref;
    }


    public List<ValidationError> getErrors() {
        return errors;
    }

    public void add(ValidationError error) {
        this.errors.add(error);
    }

    public MultiplyResponseError(List<ValidationError> errors) {
        this.errors = errors;
    }
}
