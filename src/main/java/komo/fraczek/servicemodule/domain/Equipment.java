package komo.fraczek.servicemodule.domain;

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
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GeneratedValue
    private Long Id;

    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    private Category category;

    @OneToMany(cascade = CascadeType.ALL)
    @Getter
    @Setter
    private List<Parameter> parameters;

    public static Equipment ofNameAndCategoryAndParamsHashMap(String name, Category category, HashMap<String, String> params){
        Equipment equipment = new Equipment();
        equipment.category = category;
        equipment.name = name;
        equipment.parameters = params.entrySet().stream().map(entry -> new Parameter(entry.getKey(), entry.getValue())).collect(Collectors.toList());
        return equipment;
    }
}
