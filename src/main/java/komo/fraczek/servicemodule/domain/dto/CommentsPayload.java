package komo.fraczek.servicemodule.domain.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class CommentsPayload {

    private String serviceCode;

    private List<String> comments;

}
