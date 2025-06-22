package itis.animediary.data.repository;

import itis.animediary.data.entity.AnimeStudioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface AnimeStudioRepository extends JpaRepository<AnimeStudioEntity, Long> {
    Optional<AnimeStudioEntity> findByName(String name);
    Set<AnimeStudioEntity> findAllByNameIn(Set<String> names);
}
