package komo.fraczek.servicemodule.domain.dto;

import komo.fraczek.servicemodule.domain.Equipment;
import komo.fraczek.servicemodule.domain.ServiceStatus;
import lombok.Getter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@Getter
public class ResponseWrapper {

    private String serviceCode;

    @Enumerated(EnumType.STRING)
    private ServiceStatus serviceStatus;

    private LocalDateTime dateTime;

    public static ResponseWrapper wrapEquipServiceRequest(Equipment equipment){
        ResponseWrapper responsewrapper = new ResponseWrapper();
        responsewrapper.serviceCode = equipment.getServiceCode();
        responsewrapper.serviceStatus = equipment.getServiceStatus();
//        responseWrapperOld.dateTime = equipment.getDateTime();
        return responsewrapper;
    }
}
