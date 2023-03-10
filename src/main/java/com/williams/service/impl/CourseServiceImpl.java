package com.williams.service.impl;

import com.williams.model.Course;
import com.williams.repository.ICouseRepo;
import com.williams.repository.IGenericRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl extends CRUDImpl<Course, Integer> {

    private final ICouseRepo repo;


    @Override
    protected IGenericRepo<Course, Integer> getRepo() {
        return this.repo;
    }
}
