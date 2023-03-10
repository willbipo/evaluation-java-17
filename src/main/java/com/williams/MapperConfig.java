package com.williams;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean("StudentMapper")
    public ModelMapper mapperStudent(){
        return new ModelMapper();
    }

    @Bean("CourseMapper")
    public ModelMapper mapperCourse(){
        return new ModelMapper();
    }

    @Bean("EnrollmentMapper")
    public ModelMapper mapperEnrollment(){
        return new ModelMapper();
    }
}
