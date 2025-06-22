package itis.animediary.data.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "anime_genres")
public class AnimeGenreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "genre_id")
    private int id;

    @Column(name = "genre_name", nullable = false, unique = true, length = 30)
    private String name;
}

