package itis.animediary.data.repository;

import itis.animediary.data.entity.AnimeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface AnimeRepository extends JpaRepository<AnimeEntity, Long> {

    @Modifying
    @Transactional
    @Query("UPDATE AnimeEntity a SET a.rating = (SELECT COALESCE(AVG(r.resultScore), 0) FROM AnimeReviewEntity r WHERE r.anime = :anime) WHERE a = :anime")
    void updateAnimeRating(@Param("anime") AnimeEntity anime);

    List<AnimeEntity> findByNameContainingIgnoreCase(String mask);

    //count нужен чтобы мы выбирали аниме, у которых есть одновремнно ВСЕ заданные жанры. Иначе мы выбирали бы те, у кого есть хотябы 1 из заданных жанров.
    @Query("SELECT a FROM AnimeEntity a JOIN a.genres g WHERE g.id IN :genreIds GROUP BY a.id HAVING COUNT(g.id) = :genreCount")
    List<AnimeEntity> findAnimesByGenres(@Param("genreIds") List<Integer> genreIds, @Param("genreCount") long genreCount);
}
