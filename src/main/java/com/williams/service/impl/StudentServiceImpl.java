package com.williams.service.impl;

import com.williams.model.Student;
import com.williams.repository.IGenericRepo;
import com.williams.repository.IStudentRepo;
import com.williams.service.IStudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl extends CRUDImpl<Student, Integer>{

    private final IStudentRepo repo;

    @Override
    protected IGenericRepo<Student, Integer> getRepo() {
        return this.repo;
    }
}
