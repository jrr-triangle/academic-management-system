package com.jrrtriangle.ams.dto;

import com.jrrtriangle.ams.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EndpointDTO {

    private Long endpointId;

    private String endpointname;

    private Set<Role> roles = new HashSet<>();
}
