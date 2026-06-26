package com.lms.backend.progress.entity;

import java.time.LocalDateTime;

import com.lms.backend.lesson.entity.Lesson;
import com.lms.backend.user.entity.User;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        name = "progress",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {
                                "student_id",
                                "lesson_id"
                        })
        })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Progress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private User student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lesson_id", nullable = false)
    private Lesson lesson;

    @Column(nullable = false)
    @Builder.Default
    private Boolean completed = false;

    private LocalDateTime completedAt;
}
