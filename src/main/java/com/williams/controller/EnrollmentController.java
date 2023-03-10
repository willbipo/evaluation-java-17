package com.williams.controller;

import com.williams.dto.CourseDTO;
import com.williams.dto.EnrollmentDTO;
import com.williams.dto.EnrollmentDetailDTO;
import com.williams.dto.StudentDTO;
import com.williams.model.Course;
import com.williams.model.Enrollment;
import com.williams.model.EnrollmentDetail;
import com.williams.service.impl.EnrollmentServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/enrollments")
@RequiredArgsConstructor
public class EnrollmentController {
    @Qualifier("EnrollmentMapper")
    private final ModelMapper mapper;

    private final EnrollmentServiceImpl service;

    @GetMapping
    public ResponseEntity<List<EnrollmentDTO>> getAll() throws Exception {
        List<EnrollmentDTO> enrollmentDTOList = service.readAll().stream().map(this::convertToDTO).collect(Collectors.toList());
        return new ResponseEntity(enrollmentDTOList, HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<?> getAllEnrollmets() throws Exception {
        List<EnrollmentDTO> enrollmentDTOList = service.readAll().stream().map(this::convertToDTO).collect(Collectors.toList());

        Map<String, List<String>> enrollmentDetailsMap = enrollmentDTOList
                .stream()
                .collect(Collectors.groupingBy(e -> e.getEnrollmentDetails().stream().map(course->course.getCourse().getName()).findFirst().get(),
                        Collectors.mapping(enrollmentDetailDTO -> enrollmentDetailDTO.getStudent().getName(),
                                Collectors.toList())));
        return new ResponseEntity(enrollmentDetailsMap, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EnrollmentDTO> save(@Valid @RequestBody EnrollmentDTO enrollmentDTO) throws Exception {
        Enrollment enrollment = convertToEntity(enrollmentDTO);
        return new ResponseEntity(convertToDTO(service.save(enrollment)), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnrollmentDTO> update(@PathVariable("id") Integer id,@Valid @RequestBody EnrollmentDTO enrollmentDTO) throws Exception {
        Enrollment enrollment = convertToEntity(enrollmentDTO);
        return new ResponseEntity(convertToDTO(service.update(enrollment, id)), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnrollmentDTO> findById(@PathVariable("id") Integer id) throws Exception {
        return new ResponseEntity(convertToDTO(service.readById(id)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Integer id) throws Exception {
        service.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }


    private EnrollmentDTO convertToDTO(Enrollment enrollment){
        return mapper.map(enrollment, EnrollmentDTO.class);
    }

    private Enrollment convertToEntity(EnrollmentDTO enrollmentDTO){
        return mapper.map(enrollmentDTO, Enrollment.class);
    }
}
