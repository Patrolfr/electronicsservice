package komo.fraczek.servicemodule.domain.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

import java.util.HashMap;

@RequiredArgsConstructor
@Getter
@ToString
public class Payload {
    @NotBlank(message = "Name should not be empty.")
    private String name;

    @NotBlank(message = "Category should not be empty.")
    private String category;

    private HashMap<String, String> parameters;

    @NotBlank(message = "Phone should not be empty.")
    private String phone;
}
