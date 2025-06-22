package itis.animediary.data.repository;

import itis.animediary.data.entity.AnimeEntity;
import itis.animediary.data.entity.AnimeReviewEntity;
import itis.animediary.data.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<AnimeReviewEntity, Long> {

    List<AnimeReviewEntity> findAllByAnime(AnimeEntity anime);

    Optional<AnimeReviewEntity> findByAnimeAndUser(AnimeEntity anime, UserEntity user);
}
