package komo.fraczek.servicemodule.domain.dto;

import komo.fraczek.servicemodule.domain.Equipment;
import komo.fraczek.servicemodule.domain.Parameter;
import komo.fraczek.servicemodule.domain.ServiceStatus;
import lombok.Getter;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class EquipmentWrapper {

    private String name;

    private String category;

    private String serviceCode;

    private HashMap<String, String> parameters;

    private List<String> comments;

    private ServiceStatus serviceStatus;


    public static EquipmentWrapper wrapEquipment(final Equipment equipment){
        EquipmentWrapper equipmentWrapper = new EquipmentWrapper();
        equipmentWrapper.name = equipment.getName();
        equipmentWrapper.category = equipment.getCategory().getName();
        equipmentWrapper.serviceStatus = equipment.getServiceStatus();
        equipmentWrapper.comments = equipment.getComments();
        equipmentWrapper.parameters = (HashMap<String,String>) equipment.getParameters().stream().collect(Collectors.toMap(Parameter::getKey,Parameter::getValue));
//        equipmentOld.getParameters().stream().collect(Collectors.toCollection( Parameter::getKey, Parameter::getValue, (p1, p2) -> p1, HashMap<String,String>::new));
        equipmentWrapper.serviceCode = equipment.getServiceCode();
        return equipmentWrapper;
    }
}