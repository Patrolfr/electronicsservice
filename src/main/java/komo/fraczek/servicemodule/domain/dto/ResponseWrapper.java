package komo.fraczek.servicemodule.domain.dto;

import komo.fraczek.servicemodule.domain.Equipment;
import komo.fraczek.servicemodule.domain.ServiceStatus;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.CascadeType;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;
import java.util.HashMap;


public class ResponseWrapper {


    private String ownerPhone;

    private String serviceCode;

    private Equipment equipment;

    @Enumerated(EnumType.STRING)
    private ServiceStatus serviceStatus;

    private LocalDateTime dateTime;

    public HashMap<String,String> parameters = new HashMap<>();

}
