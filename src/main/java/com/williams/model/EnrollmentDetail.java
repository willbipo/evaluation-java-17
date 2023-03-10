package com.williams.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class EnrollmentDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idEnrollmentDetail;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false, foreignKey = @ForeignKey(name = "FK_EnrollmentDetail_Course"))
    private Course course;

    private String classroom;

    @ManyToOne
    @JoinColumn(name = "enrollment_id", nullable = false, foreignKey = @ForeignKey(name = "FK_EnrollmentDetail_Enrollment"))
    private Enrollment enrollment;
}
