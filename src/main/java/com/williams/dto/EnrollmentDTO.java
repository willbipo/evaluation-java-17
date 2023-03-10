package com.williams.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.williams.model.EnrollmentDetail;
import com.williams.model.Student;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EnrollmentDTO {

    private Integer id;

    @NotNull
    private LocalDateTime dateTime;


    @NotNull
    private StudentDTO student;


    @JsonManagedReference
    private List<EnrollmentDetailDTO> enrollmentDetails;

    @NotNull
    private boolean isActive;
}
