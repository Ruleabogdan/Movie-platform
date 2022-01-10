package com.streamingplatform.moviestreamingplatform.exceptions;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorDetails {
    private String message;
    private String details;
    private Date timestamp;

}
