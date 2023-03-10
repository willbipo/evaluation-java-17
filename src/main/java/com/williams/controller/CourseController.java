package com.williams.controller;

import com.williams.dto.CourseDTO;
import com.williams.model.Course;
import com.williams.service.impl.CourseServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/courses")
@RequiredArgsConstructor
public class CourseController {

    @Qualifier("CourseMapper")
    private final ModelMapper mapper;

    private final CourseServiceImpl service;

    @GetMapping
    public ResponseEntity<List<CourseDTO>> getAll() throws Exception {
        List<CourseDTO> courseDTOList = service.readAll().stream().map(this::convertToDTO).collect(Collectors.toList());
        return new ResponseEntity(courseDTOList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CourseDTO> save(@Valid @RequestBody CourseDTO courseDTO) throws Exception {
        Course course = convertToEntity(courseDTO);
        return new ResponseEntity(convertToDTO(service.save(course)), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseDTO> update(@PathVariable("id") Integer id,@Valid @RequestBody CourseDTO courseDTO) throws Exception {
        Course course = convertToEntity(courseDTO);
        return new ResponseEntity(convertToDTO(service.save(course)), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> findById(@PathVariable("id") Integer id) throws Exception {
        return new ResponseEntity(convertToDTO(service.readById(id)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Integer id) throws Exception {
        service.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }


    private CourseDTO convertToDTO(Course course){
        return mapper.map(course, CourseDTO.class);
    }

    private Course convertToEntity(CourseDTO courseDTO){
        return mapper.map(courseDTO, Course.class);
    }
}
