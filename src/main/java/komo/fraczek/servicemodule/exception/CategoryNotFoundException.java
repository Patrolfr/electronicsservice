package komo.fraczek.servicemodule.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class CategoryNotFoundException extends RuntimeException {

    @Getter
    private String category;

}
