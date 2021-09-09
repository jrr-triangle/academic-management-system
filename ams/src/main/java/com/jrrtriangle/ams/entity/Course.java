package com.jrrtriangle.ams.entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Course {
    @Id
    @SequenceGenerator(
            name = "course_sequence",
            sequenceName = "course_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "course_sequence"
    )
    private Long courseId;

    private String title;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(
            name="teacherId",
            referencedColumnName = "teacherId"
    )
    private Teacher teacher;

//reference
    @OneToOne(
            mappedBy = "course"
    )
    private CourseMaterials courseMaterials;


    @ManyToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    @JoinTable(
            name = "student_course",
            joinColumns = @JoinColumn(
                    name = "courseId",
                    referencedColumnName = "courseId"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "studentId",
                    referencedColumnName = "studentId"
            )

    )
    private Set<Student> students;


    @ManyToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    @JoinTable(
            name = "department_course",
            joinColumns = @JoinColumn(
                    name = "courseId",
                    referencedColumnName = "courseId"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "departmentId",
                    referencedColumnName = "departmentId"
            )

    )
    private List<Department> departments;
    
}
