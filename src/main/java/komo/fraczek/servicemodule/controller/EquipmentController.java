package komo.fraczek.servicemodule.controller;


import komo.fraczek.servicemodule.domain.Equipment;
import komo.fraczek.servicemodule.domain.ServiceStatus;
import komo.fraczek.servicemodule.domain.dto.CommentsPayload;
import komo.fraczek.servicemodule.domain.dto.EquipmentPayload;
import komo.fraczek.servicemodule.domain.dto.EquipmentWrapper;
import komo.fraczek.servicemodule.exception.ValidationError;
import komo.fraczek.servicemodule.service.EquipmentService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.Valid;


@RestController
@RequestMapping("/equipments")
@RequiredArgsConstructor
public class EquipmentController {

    private static final Logger logger = LoggerFactory.getLogger(EquipmentController.class);

    private final EquipmentService equipmentService;

    @PostMapping(path = "/")
    private ResponseEntity<EquipmentWrapper> create(@RequestBody @Valid EquipmentPayload equipmentPayload){
        logger.trace(equipmentPayload.toString());

        Equipment equipment = equipmentService.registerEquipment(equipmentPayload);

        return new ResponseEntity<>(EquipmentWrapper.wrapEquipment(equipment), HttpStatus.CREATED);
    }

    @GetMapping("/{code}")
    public ResponseEntity<EquipmentWrapper> retrieve(@PathVariable final String code){
        Equipment equipment = equipmentService.fetchByCode(code);
        return new ResponseEntity<>(EquipmentWrapper.wrapEquipment(equipment), HttpStatus.OK);
    }

    @PutMapping("/{code}/{serviceStatus}")
    public ResponseEntity<EquipmentWrapper> changeStatus(@PathVariable final String code, @Valid @PathVariable ServiceStatus serviceStatus){
        Equipment equipment = equipmentService.changeStatus(code,serviceStatus);
        return new ResponseEntity<>(EquipmentWrapper.wrapEquipment(equipment), HttpStatus.OK);
    }

    @PutMapping(path = "/{code}/comment")
    public ResponseEntity<EquipmentWrapper> comment(@RequestBody CommentsPayload commentsPayload){
        Equipment equipment = equipmentService.appendComments(commentsPayload);
        return new ResponseEntity<>(EquipmentWrapper.wrapEquipment(equipment), HttpStatus.OK);
    }


    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ValidationError handleException(MethodArgumentNotValidException exception) {
        return ValidationError.createFromErrors(exception.getBindingResult());
    }


    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public String handleException2(MethodArgumentTypeMismatchException exception) {
        logger.trace(exception.toString());
        return "\n" + exception.getParameter() + "\n" + exception.getName();
//        return "xd";
    }
}