package itis.animediary.data.repository;

import itis.animediary.data.entity.AnimeGenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface AnimeGenreRepository extends JpaRepository<AnimeGenreEntity, Integer> {
    Optional<AnimeGenreEntity> findByName(String name);
    Set<AnimeGenreEntity> findAllByNameIn(Set<String> names);
}
