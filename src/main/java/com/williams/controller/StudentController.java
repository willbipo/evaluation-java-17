package com.williams.controller;

import com.williams.dto.StudentDTO;
import com.williams.model.Student;
import com.williams.service.impl.StudentServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/students")
@RequiredArgsConstructor
public class StudentController {
    @Qualifier("StudentMapper")
    private final ModelMapper mapper;

    private final StudentServiceImpl service;

    @GetMapping
    public ResponseEntity<List<StudentDTO>> getAll(@RequestParam(required = false, defaultValue = "ASC") String order) throws Exception {
        List<StudentDTO> studentDTOList = service.readAll().stream().map(this::convertToDTO).collect(Collectors.toList());
        if ("DESC".equals(order)) {
            studentDTOList.sort(Comparator.comparing(StudentDTO::getName).reversed());
        } else {
            studentDTOList.sort(Comparator.comparing(StudentDTO::getName));
        }
        return new ResponseEntity(studentDTOList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<StudentDTO> save(@Valid @RequestBody StudentDTO studentDTO) throws Exception {
        Student student = convertToEntity(studentDTO);
        return new ResponseEntity(convertToDTO(service.save(student)), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> update(@PathVariable("id") Integer id,@Valid @RequestBody StudentDTO studentDTO) throws Exception {
        Student student = convertToEntity(studentDTO);
        return new ResponseEntity(convertToDTO(service.update(student, id)), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> findById(@PathVariable("id") Integer id) throws Exception {
        return new ResponseEntity(convertToDTO(service.readById(id)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Integer id) throws Exception {
        service.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }


    private StudentDTO convertToDTO(Student student){
        return mapper.map(student, StudentDTO.class);
    }

    private Student convertToEntity(StudentDTO studentDTO){
        return mapper.map(studentDTO, Student.class);
    }
}
