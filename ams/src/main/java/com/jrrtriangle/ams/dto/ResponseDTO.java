package com.jrrtriangle.ams.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseDTO {

    private boolean isSuccess;
    private String message;
    private Object data;
    private String val;
    private String jwt;

}

