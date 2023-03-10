package com.williams.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CourseDTO {

    private Integer id;

    @NotBlank
    @Size(max = 60)
    private String name;

    @NotBlank
    @Size(min = 2, max = 3)
    private String acronym;

    @NotNull
    private boolean isActive;
}
