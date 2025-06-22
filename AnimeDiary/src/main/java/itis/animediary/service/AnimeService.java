package itis.animediary.service;

import itis.animediary.data.entity.*;
import itis.animediary.data.repository.*;
import itis.animediary.service.dto.anime.AnimeDto;
import itis.animediary.service.dto.anime.AnimeShortDto;
import itis.animediary.service.exceptions.FileException;
import itis.animediary.service.mappers.anime.AnimeListMapper;
import itis.animediary.service.mappers.anime.AnimeMapper;
import itis.animediary.service.mappers.anime.AnimeShortListMapper;
import itis.animediary.service.mappers.anime.AnimeShortMapper;
import itis.animediary.service.requestdto.CreateAnimeForm;
import itis.animediary.utils.properties.ExceptionMessages;
import itis.animediary.utils.properties.OtherProperties;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AnimeService {

    private final AnimeRepository animeRepository;
    private final AnimeStatusRepository animeStatusRepository;
    private final AnimeTypeRepository animeTypeRepository;
    private final AnimeGenreRepository animeGenreRepository;
    private final AnimeStudioRepository animeStudioRepository;
    private final UserAnimeListStatusRepository userAnimeListStatusRepository;
    private final UserRepository userRepository;

    private final AnimeShortListMapper animeShortListMapper;
    private final AnimeMapper animeMapper;
    private final AnimeShortMapper animeShortMapper;
    private final AnimeListMapper animeListMapper;

    @PersistenceContext
    private EntityManager entityManager;

    public Optional<AnimeDto> getAnimeById(long id) {
        return animeRepository.findById(id).map(animeMapper::toDTO);
    }

    public Optional<AnimeShortDto> getAnimeShortById(long id) {
        return animeRepository.findById(id).map(animeShortMapper::toDTO);
    }

    public List<AnimeShortDto> getAllAnimesShortDto() {
        List<AnimeEntity> animes = animeRepository.findAll();
        return animeShortListMapper.toDtoList(animes);
    }

    public List<AnimeShortDto> getAnimesByMask(String mask) {
        List<AnimeEntity> animes = animeRepository.findByNameContainingIgnoreCase(mask);
        return animeShortListMapper.toDtoList(animes);
    }

    //все аниме, которые есть у юзера в каком-либо списке
    public List<AnimeDto> getUsersAnimes(long userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(ExceptionMessages.USER_NOT_FOUND_ERROR));
        List<UserAnimeListStatusEntity> statusList = userAnimeListStatusRepository.findByUser(user);
        List<AnimeEntity> animeEntities = statusList.stream()
                .map(UserAnimeListStatusEntity::getAnime)
                .toList();
        return animeListMapper.toDtoList(animeEntities);
    }

    //аниме по спискам
    public List<AnimeDto> getAnimesByListName(String listName, long userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(ExceptionMessages.USER_NOT_FOUND_ERROR));
        List<UserAnimeListStatusEntity> statusList;
        switch (listName) {
            case "Все" ->
                statusList = userAnimeListStatusRepository.findByUser(user);
            case "В планах" ->
                statusList = userAnimeListStatusRepository.findByUserAndInPlansTrue(user);
            case "Смотрю" ->
                statusList = userAnimeListStatusRepository.findByUserAndWatchingTrue(user);
            case "Просмотрено" ->
                statusList = userAnimeListStatusRepository.findByUserAndWatchedTrue(user);
            case "Брошено" ->
                statusList = userAnimeListStatusRepository.findByUserAndDroppedTrue(user);
            case "Любимое" ->
                statusList = userAnimeListStatusRepository.findByUserAndLikedTrue(user);
            case "Плохое" ->
                statusList = userAnimeListStatusRepository.findByUserAndDislikedTrue(user);
            default ->
                throw new IllegalArgumentException("Такого списка не существует");
        }
        List<AnimeEntity> animeEntities = statusList.stream()
                .map(UserAnimeListStatusEntity::getAnime)
                .toList();
        return animeListMapper.toDtoList(animeEntities);
    }

    public double updateAnimeRatingById(long id) {
        AnimeEntity anime = animeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ExceptionMessages.ANIME_NOT_FOUND_ERROR));
        animeRepository.updateAnimeRating(anime);

        entityManager.clear(); //очищаем кэш что бы вернуть новую сущность с обновленным рейтингом

        AnimeEntity updatedAnime = animeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ExceptionMessages.ANIME_NOT_FOUND_ERROR));
        return updatedAnime.getRating();
    }

    public AnimeDto createAnime(CreateAnimeForm animeForm) {
        AnimeStatusEntity status = animeStatusRepository.findByName(animeForm.getAnimeStatus())
                .orElseThrow(() -> new EntityNotFoundException(ExceptionMessages.ANIME_STATUS_NOT_FOUND_ERROR));
        AnimeTypeEntity type = animeTypeRepository.findByName(animeForm.getAnimeType())
                .orElseThrow(() -> new EntityNotFoundException(ExceptionMessages.ANIME_TYPE_NOT_FOUND_ERROR));

        String filename;
        MultipartFile posterPhoto = animeForm.getPosterPhoto();
        if (posterPhoto != null && !posterPhoto.isEmpty()) {
            filename = System.nanoTime() + posterPhoto.getOriginalFilename();
            File fileToSave = new File(OtherProperties.IMAGE_ROOT_PATH + filename);
            try {
                posterPhoto.transferTo(fileToSave);
            } catch (IOException e) {
                throw FileException.forCreateAnime();
            }
        } else {
            filename = "default_poster.jpg";
        }

        AnimeEntity anime = AnimeEntity.builder()
                .name(animeForm.getName())
                .posterImgPath(filename)
                .numberOfEpisodes(animeForm.getNumberOfEpisodes())
                .year(animeForm.getYear())
                .synopsis(animeForm.getSynopsis())
                .linkToWatch(animeForm.getLinkToWatch())
                .status(status)
                .type(type)
                .build();

        Set<AnimeGenreEntity> genreEntitySet = animeGenreRepository.findAllByNameIn(animeForm.getGenres());
        anime.setGenres(genreEntitySet);

        Set<AnimeStudioEntity> studioEntitySet = animeStudioRepository.findAllByNameIn(animeForm.getStudios());
        anime.setStudios(studioEntitySet);
        AnimeEntity savedAnime = animeRepository.save(anime);
        return animeMapper.toDTO(savedAnime);
    }

    public List<AnimeShortDto> findAnimesByGenres(List<Integer> genreIds) {
        if (genreIds == null || genreIds.isEmpty()) {
            return Collections.emptyList();
        }
        List<AnimeEntity> animes = animeRepository.findAnimesByGenres(genreIds, genreIds.size());
        return animeShortListMapper.toDtoList(animes);
    }
}
