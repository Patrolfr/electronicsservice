package komo.fraczek.servicemodule.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import komo.fraczek.servicemodule.domain.dto.EquipmentPayload;
import lombok.*;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Equipment {

    @Id
    @GeneratedValue
    private Long Id;

    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonProperty
    private Category category;

    @Setter
    private String serviceCode;

    @OneToMany(cascade = CascadeType.ALL)
    @Getter
    @Setter
    private List<Parameter> parameters;

    @ElementCollection
    @CollectionTable(name="comments", joinColumns=@JoinColumn(name="user_id"))
    @Column(name="comments")
    private List<String> comments;

    @Enumerated(EnumType.STRING)
    private ServiceStatus serviceStatus;

    public static Equipment ofNameAndCategoryAndParamsHashMap(String name, Category category, HashMap<String, String> params){
        Equipment equipmentOld = new Equipment();
        equipmentOld.category = category;
        equipmentOld.name = name;
        equipmentOld.parameters = params.entrySet().stream().map(entry -> new Parameter(entry.getKey(), entry.getValue())).collect(Collectors.toList());
        return equipmentOld;
    }

    public static Equipment fromPayloadAndCategory(EquipmentPayload payload, Category category){
        Equipment equipment = new Equipment();
        equipment.category = category;
        equipment.name = payload.getName();
        equipment.comments = payload.getComments();
        equipment.serviceStatus = payload.getServiceStatus();
        equipment.parameters = payload.getParameters().entrySet().stream().map(entry -> new Parameter(entry.getKey(), entry.getValue())).collect(Collectors.toList());
        return equipment;
    }

    public void changeStatus(ServiceStatus serviceStatus){
        this.serviceStatus=serviceStatus;
    }

}
