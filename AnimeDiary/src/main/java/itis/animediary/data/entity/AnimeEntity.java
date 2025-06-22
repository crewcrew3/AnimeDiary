package itis.animediary.data.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "animes")
public class AnimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "anime_id")
    private long id;

    @Column(name = "anime_name", nullable = false, columnDefinition = "text")
    private String name;

    @Builder.Default
    @Column(name = "anime_poster_img_path", nullable = false, columnDefinition = "text default 'default_poster.jpg'")
    private String posterImgPath = "default_poster.jpg";

    @Column(name = "anime_number_of_episodes", nullable = false)
    private short numberOfEpisodes;

    @Column(name = "anime_year")
    private Short year;

    @Builder.Default
    @Column(name = "anime_rating", nullable = false)
    private double rating = 0;

    @Column(name = "anime_synopsis", nullable = false, columnDefinition = "text")
    private String synopsis;

    @Column(name = "anime_link_to_watch", columnDefinition = "text")
    private String linkToWatch;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "anime_status", nullable = false)
    private AnimeStatusEntity status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "anime_type", nullable = false)
    private AnimeTypeEntity type;

    @Builder.Default
    @ManyToMany
    @JoinTable(
            name = "fact_anime_belongs_genre",
            joinColumns = @JoinColumn(name = "anime"),
            inverseJoinColumns = @JoinColumn(name = "genre")
    )
    private Set<AnimeGenreEntity> genres = new HashSet<>();

    @Builder.Default
    @ManyToMany
    @JoinTable(
            name = "fact_studio_working_on_anime",
            joinColumns = @JoinColumn(name = "anime"),
            inverseJoinColumns = @JoinColumn(name = "studio")
    )
    private Set<AnimeStudioEntity> studios = new HashSet<>();

    public void setStudios(Set<AnimeStudioEntity> studios) {
        this.studios = studios != null ? studios : new HashSet<>();
        for (AnimeStudioEntity studio : this.studios) {
            studio.getAnimes().add(this);
        }
    }
}

