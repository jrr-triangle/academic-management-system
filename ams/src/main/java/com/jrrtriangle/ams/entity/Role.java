package com.jrrtriangle.ams.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role implements Serializable {
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

    private String rolename;

//    @ManyToMany(
//    )
//    @JoinTable(
//            name = "user_role",
//            joinColumns = @JoinColumn(
//                    name = "roleId",
//                    referencedColumnName = "roleId"
//            ),
//            inverseJoinColumns = @JoinColumn(
//                    name = "UserId",
//                    referencedColumnName = "UserId"
//            )
//
//    )
//    private Set<User> users;


    @Override
    public String toString() {
        return "Role{}";
    }
}
