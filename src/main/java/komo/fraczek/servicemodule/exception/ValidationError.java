package komo.fraczek.servicemodule.exception;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Getter
public class ValidationError {

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private final List<String> errors = new ArrayList<>();

    private final String errorMessage;

    public static final ValidationError createFromErrors(Errors errors){
        ValidationError error = new ValidationError("Validation failed. " + errors.getErrorCount() + " error(s)");
        for (ObjectError objectError : errors.getAllErrors()) {
            error.errors.add(objectError.getDefaultMessage());
        }
        return error;
    }
}
