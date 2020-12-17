package com.rooms.occupancy.manager.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Throw ErrorResponse when the following exception raisers.
 */
@Data
public class ErrorResponse implements Serializable {
    private static final long serialVersionUID = 1L;
    private HttpStatus status;
    private String errorCode;
    private String description;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
}
