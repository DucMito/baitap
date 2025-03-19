package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id", nullable = false)
    private Integer userId; // Bạn sẽ cần thêm @ManyToOne ở đây nếu cần

    @Column(name = "movie_id", nullable = false)
    private Integer movieId; // Bạn sẽ cần thêm @ManyToOne ở đây nếu cần

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @Column(name = "rating")
    private Integer rating;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Integer createdAt;

    @Column(name = "updated_at", nullable = false)
    private Integer updatedAt;

}
