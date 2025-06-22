package itis.animediary.service;

import itis.animediary.data.repository.AnimeGenreRepository;
import itis.animediary.service.dto.AnimeGenreDto;
import itis.animediary.service.mappers.genre.AnimeGenreListMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimeGenreService {

    private final AnimeGenreRepository animeGenreRepository;

    private final AnimeGenreListMapper animeGenreListMapper;

    public List<AnimeGenreDto> getAllGenres() {
        return animeGenreListMapper.toDtoList(animeGenreRepository.findAll());
    }
}
