package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "histories")
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id", nullable = false)
    private Integer userId; // Bạn sẽ cần thêm @ManyToOne ở đây nếu cần

    @Column(name = "movie_id", nullable = false)
    private Integer movieId; // Bạn sẽ cần thêm @ManyToOne ở đây nếu cần

    @Column(name = "duration")
    private Long duration;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

}
