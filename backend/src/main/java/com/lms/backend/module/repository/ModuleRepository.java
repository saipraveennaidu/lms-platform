package com.lms.backend.module.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.backend.course.entity.Course;
import com.lms.backend.module.entity.Module;

public interface ModuleRepository extends JpaRepository<Module, Long>{
    List<Module> findByCourse(Course course);
}
