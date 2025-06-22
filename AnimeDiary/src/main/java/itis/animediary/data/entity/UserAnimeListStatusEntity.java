package itis.animediary.data.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "user_anime_list_status")
public class UserAnimeListStatusEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uals_id")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "anime", nullable = false)
    private AnimeEntity anime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "\"user\"", nullable = false)
    private UserEntity user;

    @Column(name = "is_in_plans", nullable = false)
    private boolean inPlans;

    @Column(name = "is_watching", nullable = false)
    private boolean watching;

    @Column(name = "is_watched", nullable = false)
    private boolean watched;

    @Column(name = "is_dropped", nullable = false)
    private boolean dropped;

    @Column(name = "is_liked", nullable = false)
    private boolean liked;

    @Column(name = "is_disliked", nullable = false)
    private boolean disliked;
}

