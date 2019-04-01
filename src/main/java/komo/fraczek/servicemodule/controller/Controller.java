package komo.fraczek.servicemodule.controller;


import komo.fraczek.servicemodule.domain.EquipServiceRequest;
import komo.fraczek.servicemodule.domain.dto.Payload;
import komo.fraczek.servicemodule.domain.dto.ResponseWrapper;
import komo.fraczek.servicemodule.exception.ValidationError;
import komo.fraczek.servicemodule.service.EquipmentService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequiredArgsConstructor
public class Controller {

    private static final Logger logger = LoggerFactory.getLogger(Controller.class);

    private final EquipmentService equipmentService;

    @PostMapping(path = "equipmentServiceRequests")
    private ResponseEntity<?> postmapeq(@RequestBody @Valid Payload equipmentPayload){
        logger.trace(equipmentPayload.toString());
//        return new ResponseEntity(equipmentService.registerServiceRequest(equipmentPayload), HttpStatus.CREATED);
        ResponseWrapper wrapper = new ResponseWrapper();
        wrapper.map.put("k1","v1");
        wrapper.map.put("k2","v2");
        return new ResponseEntity<ResponseWrapper>(wrapper, HttpStatus.CREATED);
    }


    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ValidationError handleException(MethodArgumentNotValidException exception) {
        return ValidationError.createFromErrors(exception.getBindingResult());
    }

}