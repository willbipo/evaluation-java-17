package com.williams.service.impl;

import com.williams.model.Enrollment;
import com.williams.model.Student;
import com.williams.repository.IEnrollmentRepo;
import com.williams.repository.IGenericRepo;
import com.williams.repository.IStudentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EnrollmentServiceImpl extends CRUDImpl<Enrollment, Integer>{

    private final IEnrollmentRepo repo;

    @Override
    protected IGenericRepo<Enrollment, Integer> getRepo() {
        return this.repo;
    }
}
