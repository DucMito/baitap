package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "episodes")
public class Episode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "duration")
    private Integer duration; // seconds

    @Column(name = "display_order")
    private Integer displayOrder; // thu tu tap phim

    @Column(name = "status")
    private String status;

    @Column(name = "video_url")
    private String videoUrl;

    @Column(name = "movie_id", nullable = false)
    private Integer movieId; // Bạn sẽ cần thêm @ManyToOne ở đây nếu cần

    @Column(name = "published_at")
    private LocalDateTime publishedAt;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

}
