package itis.animediary.data.repository;

import itis.animediary.data.entity.AnimeTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AnimeTypeRepository extends JpaRepository<AnimeTypeEntity, Short> {
    Optional<AnimeTypeEntity> findByName(String name);
}
