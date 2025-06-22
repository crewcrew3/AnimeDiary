package itis.animediary.data.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "anime_reviews")
public class AnimeReviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "anime", nullable = false)
    private AnimeEntity anime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "\"user\"", nullable = false)
    private UserEntity user;

    @Builder.Default
    @Column(name = "review_date", nullable = false, columnDefinition = "timestamp with time zone default now()")
    private Date reviewDate = new Date();

    @Column(name = "rate_drawing", nullable = false)
    private short rateDrawing;

    @Column(name = "rate_plot", nullable = false)
    private short ratePlot;

    @Column(name = "rate_characters", nullable = false)
    private short rateCharacters;

    @Column(name = "rate_opening", nullable = false)
    private short rateOpening;

    @Column(name = "result_score", nullable = false)
    private double resultScore;

    @Column(name = "review_text", length = 3500)
    private String reviewText;
}

