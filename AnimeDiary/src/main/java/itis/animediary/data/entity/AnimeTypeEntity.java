package itis.animediary.data.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "anime_types")
public class AnimeTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_id")
    private short id;

    @Column(name = "type_name", nullable = false, unique = true, length = 30)
    private String name;
}

