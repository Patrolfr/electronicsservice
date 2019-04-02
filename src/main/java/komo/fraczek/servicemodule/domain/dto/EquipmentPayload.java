package komo.fraczek.servicemodule.domain.dto;

import komo.fraczek.servicemodule.domain.ServiceStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
@Getter
public class EquipmentPayload {
    @NotBlank(message = "Name should not be empty.")
    private String name;

    @NotBlank(message = "Category should not be empty.")
    private String category;

    private HashMap<String, String> parameters;

    private List<String> comments;

    @Enumerated(EnumType.STRING)
    private ServiceStatus serviceStatus;
}
