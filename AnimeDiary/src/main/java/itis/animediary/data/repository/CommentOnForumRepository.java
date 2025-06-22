package itis.animediary.data.repository;

import itis.animediary.data.entity.AnimeEntity;
import itis.animediary.data.entity.CommentOnForumEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentOnForumRepository extends JpaRepository<CommentOnForumEntity, Long> {

    List<CommentOnForumEntity> getAllByAnime(AnimeEntity anime);
}
