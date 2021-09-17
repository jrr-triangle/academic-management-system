package com.jrrtriangle.ams.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role {
    @Id
    @SequenceGenerator(
            name = "role_sequence",
            sequenceName = "role_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "role_sequence"
    )
    private Long roleId;
    private String role;
//    @JsonIgnore
//    @ManyToMany(mappedBy = "roles", cascade = CascadeType.ALL)
//    private Set<UserEntity> users;

    @ManyToMany( cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(
            name = "roles_privileges",
            joinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "roleId"),
            inverseJoinColumns = @JoinColumn(
                    name = "privilegesId",
                    referencedColumnName = "id"
            )
         )
    private Set<Privilege> privileges;


}
