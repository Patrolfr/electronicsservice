package komo.fraczek.servicemodule.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
public class CodeNotFoundException extends RuntimeException {

    @Getter
    private String code;

//    public CodeNotFoundException(String code) {
//        super();
//        this.code = code;
//    }
}
