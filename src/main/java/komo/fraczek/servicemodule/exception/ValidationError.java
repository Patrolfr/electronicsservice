package komo.fraczek.servicemodule.exception;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

@RequiredArgsConstructor
@Getter
public class ValidationError {

    @JsonInclude(NON_EMPTY)
    private final List<String> errors = new ArrayList<>();

    private final String errorMessage;

    public static ValidationError createFromErrors(Errors errors){
        ValidationError error = new ValidationError("Validation failed. " + errors.getErrorCount() + " error(s).");
        for (ObjectError err : errors.getAllErrors()) {
            error.errors.add(err.getDefaultMessage());
        }
        return error;
    }
}
