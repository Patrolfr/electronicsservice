package komo.fraczek.servicemodule.controller;


import komo.fraczek.servicemodule.domain.Equipment;
import komo.fraczek.servicemodule.domain.ServiceStatus;
import komo.fraczek.servicemodule.domain.dto.CommentsPayload;
import komo.fraczek.servicemodule.domain.dto.EquipmentPayload;
import komo.fraczek.servicemodule.domain.dto.EquipmentResponse;
import komo.fraczek.servicemodule.service.EquipmentService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
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
    @ResponseStatus(value = HttpStatus.CREATED)
    private EquipmentResponse create(@RequestBody @Valid EquipmentPayload equipmentPayload, HttpServletRequest request){
        Equipment equipment = equipmentService.registerEquipment(equipmentPayload);
        return EquipmentResponse.wrapEquipment(equipment);
    }

    @GetMapping(path = "/")
    private List<EquipmentResponse> retrieveAll(){
        return equipmentService.fetchAllAndWrap();
    }

    @DeleteMapping("/{code}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable final String code){
        equipmentService.deleteEquipment(code);
    }

    @GetMapping("/{code}")
    public EquipmentResponse retrieve(@PathVariable final String code){
        Equipment equipment = equipmentService.fetchByCode(code);
        return EquipmentResponse.wrapEquipment(equipment);
    }

    @PutMapping("/{code}/{serviceStatus}")
    public EquipmentResponse changeStatus(@PathVariable final String code, @Valid @PathVariable ServiceStatus serviceStatus){
        Equipment equipment = equipmentService.changeStatus(code,serviceStatus);
        return EquipmentResponse.wrapEquipment(equipment);
    }

    @PutMapping(path = "/{code}/comment")
    public EquipmentResponse comment(@PathVariable final String code, @RequestBody CommentsPayload commentsPayload){
        Equipment equipment = equipmentService.appendComments(code, commentsPayload);
        return EquipmentResponse.wrapEquipment(equipment);
    }

    @GetMapping(path = "/category/{category}")
    private List<EquipmentResponse> retrieveByCategory(@PathVariable String category){
        return equipmentService.fetchByCategoryAndWrap(category);
    }
}