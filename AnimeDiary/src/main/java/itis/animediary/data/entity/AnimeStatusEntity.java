package itis.animediary.data.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "anime_statuses")
public class AnimeStatusEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "status_id")
    private short id;

    @Column(name = "status_name", nullable = false, unique = true, length = 30)
    private String name;
}

