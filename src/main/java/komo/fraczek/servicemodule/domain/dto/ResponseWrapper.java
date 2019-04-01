package komo.fraczek.servicemodule.domain.dto;

import lombok.Setter;

import java.util.HashMap;


public class ResponseWrapper {

    @Setter
    public HashMap<String,String> map = new HashMap<>();

}
