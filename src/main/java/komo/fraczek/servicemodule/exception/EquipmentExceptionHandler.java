package komo.fraczek.servicemodule.exception;

import komo.fraczek.servicemodule.domain.ServiceStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Arrays;

@ControllerAdvice
@RestController
public class EquipmentExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(EquipmentExceptionHandler.class);

    @ExceptionHandler(CodeNotFoundException.class)
    public final ResponseEntity<ExceptionResponse> handleCodeNotFoundException(CodeNotFoundException exception){
        ExceptionResponse exceptionResponse = new ExceptionResponse("Service code not found.","No equipment of service code " + exception.getCode() + " found.");
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public final ResponseEntity<ExceptionResponse> handleCategoryNotFoundException(CategoryNotFoundException exception){
        ExceptionResponse exceptionResponse = new ExceptionResponse("Category not found.","Category " + exception.getCategory() + " does not exists.");
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ValidationError handleException(MethodArgumentNotValidException exception) {
        return ValidationError.createFromErrors(exception.getBindingResult());
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionResponse> handleException2(MethodArgumentTypeMismatchException exception) {
        logger.debug(exception.toString());
        ExceptionResponse exceptionResponse = new ExceptionResponse("Invalid servce status.","Allowed values are: "  + Arrays.asList(ServiceStatus.values()) + ".");
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ExceptionResponse> HttpMessageNotReadableException(HttpMessageNotReadableException exception) {
        ExceptionResponse exceptionResponse = new ExceptionResponse("Invalid servce status.","Allowed values are: " +
                Arrays.asList(ServiceStatus.values()) + ".");
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }
}