package komo.fraczek.servicemodule.domain;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

import static komo.fraczek.servicemodule.domain.ServiceStatus.ACCEPTED;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EquipServiceRequest {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GeneratedValue
    private Long Id;

    private String ownerPhone;

    @Setter
    private String serviceCode;

    @OneToOne(cascade = CascadeType.ALL)
    private Equipment equipment;

    @Enumerated(EnumType.STRING)
    private ServiceStatus serviceStatus;

    @CreationTimestamp
    private LocalDateTime dateTime;

    public static EquipServiceRequest ofEquipmentAndOwner(Equipment equipment, String ownerPhone){
        EquipServiceRequest equipServiceRequest = new EquipServiceRequest();
        equipServiceRequest.ownerPhone = ownerPhone;
        equipServiceRequest.equipment = equipment;
        equipServiceRequest.serviceStatus = ACCEPTED;
        return equipServiceRequest;
    }

}
