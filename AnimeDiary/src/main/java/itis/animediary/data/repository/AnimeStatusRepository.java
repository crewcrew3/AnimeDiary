package itis.animediary.data.repository;

import itis.animediary.data.entity.AnimeStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AnimeStatusRepository extends JpaRepository<AnimeStatusEntity, Short> {
    Optional<AnimeStatusEntity> findByName(String name);
}
