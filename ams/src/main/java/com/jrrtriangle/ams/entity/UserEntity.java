package com.jrrtriangle.ams.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity(name="user")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity implements Serializable {
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

    @ManyToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(
                    name = "UserId",
                    referencedColumnName = "UserId"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "roleId",
                    referencedColumnName = "roleId"
            )

    )
    private Set<Role> roles;


    @OneToOne(mappedBy = "user")
    private Student student;

    @OneToOne(mappedBy = "user")
    private Staff staff;

    @OneToOne(mappedBy = "user")
    private Teacher teacher;

}
