package komo.fraczek.servicemodule.domain.dto;

import lombok.*;

import java.util.List;

@Getter
@RequiredArgsConstructor
@ToString
public class CommentsPayload {

    @Setter
    private List<String> comments;

}
