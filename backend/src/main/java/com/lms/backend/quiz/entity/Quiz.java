package com.lms.backend.quiz.entity;

import java.util.ArrayList;
import java.util.List;

import com.lms.backend.course.entity.Course;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "quizzes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @OneToMany(
            mappedBy = "quiz",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @Builder.Default
    private List<Question> questions = new ArrayList<>();
}
