package komo.fraczek.servicemodule.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class EquipmentExceptionHandler {

    @ExceptionHandler(CodeNotFoundException.class)
    public final ResponseEntity<String> handleCodeNotFoundException(CodeNotFoundException exception){
        return new ResponseEntity<String>("Service Code not found: " + exception.getCode(), HttpStatus.NOT_FOUND);
    }


}
