package com.contact.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
public class ValidationFailedResponse {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    @Builder.Default
    private final LocalDateTime timestamp = LocalDateTime.now();

    private final String status;

    private final Map<String, String> hints;

    private final String stackTrace;

    private final String path;
}
