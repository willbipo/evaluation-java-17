package com.williams.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentDTO {

    private Integer id;

    @NotBlank
    @Size(min=3,max = 150)
    private String name;

    @NotBlank
    @Size(min=3,max = 150)
    private String lastName;

    @NotBlank
    @Size(min=3,max = 150)
    private String dni;

    @NotNull
    @Max(110)
    @Min(15)
    private int age;
}
