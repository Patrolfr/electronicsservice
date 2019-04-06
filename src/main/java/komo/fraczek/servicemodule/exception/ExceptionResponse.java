package komo.fraczek.servicemodule.exception;

import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
public class ExceptionResponse {

    @CreationTimestamp
    private LocalDateTime timestamp;

    private String message;

    private String details;


    public ExceptionResponse(String message,  String details) {
        this.timestamp = LocalDateTime.now();
        this.message = message;
        this.details = details;
    }
}
