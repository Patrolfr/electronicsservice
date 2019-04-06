package komo.fraczek.servicemodule.controller;


import komo.fraczek.servicemodule.domain.Equipment;
import komo.fraczek.servicemodule.domain.ServiceStatus;
import komo.fraczek.servicemodule.domain.dto.CommentsPayload;
import komo.fraczek.servicemodule.domain.dto.EquipmentPayload;
import komo.fraczek.servicemodule.domain.dto.EquipmentWrapper;
import komo.fraczek.servicemodule.service.EquipmentService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/equipments")
@RequiredArgsConstructor
public class EquipmentController {

    private static final Logger logger = LoggerFactory.getLogger(EquipmentController.class);

    private final EquipmentService equipmentService;

    @PostMapping(path = "/")
    private ResponseEntity<EquipmentWrapper> create(@RequestBody @Valid EquipmentPayload equipmentPayload, HttpServletRequest request){
        logger.trace(equipmentPayload.toString());

        Equipment equipment = equipmentService.registerEquipment(equipmentPayload);

        return new ResponseEntity<>(EquipmentWrapper.wrapEquipment(equipment), HttpStatus.CREATED);
    }

    @GetMapping(path = "/")
    private ResponseEntity<List<EquipmentWrapper>> retrieveAll(){
        logger.trace("retrieveAll");
        List<EquipmentWrapper> equipmentWrappers = equipmentService.fetchAllAndWrap();
        return new ResponseEntity<>(equipmentWrappers, HttpStatus.OK);
    }

    @GetMapping(path = "/category/{category}")
    private ResponseEntity<List<EquipmentWrapper>> retrieveByCategory(@PathVariable String category){
        logger.trace("retrieveAll");
        List<EquipmentWrapper> equipmentWrappers = equipmentService.fetchByCategoryAndWrap(category);
        return new ResponseEntity<>(equipmentWrappers, HttpStatus.OK);
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
    public ResponseEntity<EquipmentWrapper> comment(@PathVariable final String code, @RequestBody CommentsPayload commentsPayload){
        Equipment equipment = equipmentService.appendComments(code, commentsPayload);
        return new ResponseEntity<>(EquipmentWrapper.wrapEquipment(equipment), HttpStatus.OK);
    }
}