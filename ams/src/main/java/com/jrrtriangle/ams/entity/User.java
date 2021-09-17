package com.jrrtriangle.ams.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements Serializable {
    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private Long userId;

    private String firstname;

    private String lastname;

    private  String email;

    private String password;

//    @ManyToMany(
//
//            cascade = CascadeType.MERGE
//
//    )
//    @JoinTable(
//            name = "user_role",
//            joinColumns = @JoinColumn(
//                    name = "UserId",
//                    referencedColumnName = "UserId"
//            ),
//            inverseJoinColumns = @JoinColumn(
//                    name = "roleId",
//                    referencedColumnName = "roleId"
//            )
//
//    )
//    private Set<Role> roles;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "user_role",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "UserId")},
            inverseJoinColumns = {@JoinColumn(name = "role_name", referencedColumnName = "roleId")})
    private Set<Role> roles = new HashSet<>();



    @OneToOne(mappedBy = "user")
    private Student student;

    @OneToOne(mappedBy = "user")
    private Staff staff;

    @OneToOne(mappedBy = "user")
    private Teacher teacher;

    @Override
    public String toString() {
        return "User{}";
    }
}
