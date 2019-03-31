package komo.fraczek.servicemodule.controller;


import komo.fraczek.servicemodule.domain.EquipmentServiceRequest;
import komo.fraczek.servicemodule.exception.ValidationError;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequiredArgsConstructor
public class Controller {

    private static final Logger logger = LoggerFactory.getLogger(Controller.class);

    @PostMapping(path = "equipmentServiceRequests")
    String  postmapeq(@RequestBody @Valid EquipmentServiceRequest equipmentServiceRequest){
        logger.trace(equipmentServiceRequest.toString());
        return "Response body of service request { date.. etc{} }";
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ValidationError handleException(MethodArgumentNotValidException exception) {
        return ValidationError.createFromErrors(exception.getBindingResult());
    }

}