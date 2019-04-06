package komo.fraczek.servicemodule.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import komo.fraczek.servicemodule.domain.dto.EquipmentPayload;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "equipments")
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
    @CollectionTable(name="comments", joinColumns=@JoinColumn(name="equipment_id"))
    @Embedded
    @Column(name="comments")
    private List<String> comments;

    @Enumerated(EnumType.STRING)
    private ServiceStatus serviceStatus;


    public static Equipment fromPayloadAndCategory(EquipmentPayload payload, Category category){
        Equipment equipment = new Equipment();
        equipment.category = category;
        equipment.name = payload.getName();
        equipment.comments = payload.getComments();
        equipment.serviceStatus = payload.getServiceStatus();
        equipment.parameters = payload.getParameters().entrySet().stream().map(entry -> new Parameter(entry.getKey(), entry.getValue())).collect(Collectors.toList());
        equipment.serviceStatus = payload.getServiceStatus() == null ? ServiceStatus.UNKNOWN : payload.getServiceStatus();
        return equipment;
    }

    public void changeStatus(ServiceStatus serviceStatus){
        this.serviceStatus=serviceStatus;
    }

    public void addComments(List<String> newComments){
        comments.addAll(newComments);
    }
}
