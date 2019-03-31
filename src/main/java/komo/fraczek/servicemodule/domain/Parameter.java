package komo.fraczek.servicemodule.domain;

import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Setter
@NoArgsConstructor
public class Parameter {

    @Id
    @GeneratedValue
    private Long id;

    private String key;

    private String value;

}
