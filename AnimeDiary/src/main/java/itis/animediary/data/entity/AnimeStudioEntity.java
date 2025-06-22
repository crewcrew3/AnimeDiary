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
@Table(name = "anime_studios")
public class AnimeStudioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "studio_id")
    private long id;

    @Column(name = "studio_name", nullable = false, unique = true, length = 50)
    private String name;

    @Column(name = "studio_foundation_year")
    private Short foundationYear;

    @Column(name = "studio_founder_name", length = 30)
    private String founderName;

    @Column(name = "studio_official_site", length = 50, unique = true)
    private String officialSite;

    @Column(name = "studio_number_of_employees")
    private Integer numberOfEmployees;

    @Builder.Default
    @ManyToMany(mappedBy = "studios")
    private Set<AnimeEntity> animes = new HashSet<>();
}

