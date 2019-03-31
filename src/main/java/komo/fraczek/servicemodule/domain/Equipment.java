package komo.fraczek.servicemodule.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Equipment {

    @Id
    @GeneratedValue
    private Long Id;

    private String name;

    @ManyToOne
    private Category category;

    @OneToMany
    private List<Parameter> parameters;
}
