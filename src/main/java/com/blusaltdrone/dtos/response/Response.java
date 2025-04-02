package com.blusaltdrone.dtos.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@ToString
@Builder
public class Response {
    private int code;
    private String message;
    private Object data;
}

