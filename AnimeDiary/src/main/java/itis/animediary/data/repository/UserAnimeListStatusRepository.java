package itis.animediary.data.repository;

import itis.animediary.data.entity.AnimeEntity;
import itis.animediary.data.entity.UserAnimeListStatusEntity;
import itis.animediary.data.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserAnimeListStatusRepository extends JpaRepository<UserAnimeListStatusEntity, Long> {

    Optional<UserAnimeListStatusEntity> findByAnimeAndUser(AnimeEntity anime, UserEntity user);

    List<UserAnimeListStatusEntity> findByUser(UserEntity user);

    List<UserAnimeListStatusEntity> findByUserAndInPlansTrue(UserEntity user);
    List<UserAnimeListStatusEntity> findByUserAndWatchingTrue(UserEntity user);
    List<UserAnimeListStatusEntity> findByUserAndWatchedTrue(UserEntity user);
    List<UserAnimeListStatusEntity> findByUserAndDroppedTrue(UserEntity user);
    List<UserAnimeListStatusEntity> findByUserAndLikedTrue(UserEntity user);
    List<UserAnimeListStatusEntity> findByUserAndDislikedTrue(UserEntity user);
}
