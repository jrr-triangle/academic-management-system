package com.jrrtriangle.ams.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Endpoint {

    @Id
    @SequenceGenerator(
            name = "endpoint_sequence",
            sequenceName = "endpoint_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "endpoint_sequence"
    )
    private Long endpointId;

    private String endpointname;


    @ManyToMany
    @JoinTable(
            name = "endpoint_role",
            joinColumns = {@JoinColumn(name = "endpointId", referencedColumnName = "endpointId")},
            inverseJoinColumns = {@JoinColumn(name = "role_name", referencedColumnName = "roleId")})
    private Set<Role> roles = new HashSet<>();
}
