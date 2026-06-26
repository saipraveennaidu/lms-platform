package com.lms.backend.assignment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lms.backend.assignment.entity.Assignment;
import com.lms.backend.assignment.entity.Submission;
import com.lms.backend.user.entity.User;

public interface SubmissionRepository extends JpaRepository<Submission, Long>  {
    List<Submission> findByStudent(User student);

    List<Submission> findByAssignment(
            Assignment assignment);
}
