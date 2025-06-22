package itis.animediary.service;

import itis.animediary.data.entity.AnimeStudioEntity;
import itis.animediary.data.repository.AnimeStudioRepository;
import itis.animediary.service.dto.studio.AnimeStudioDto;
import itis.animediary.service.dto.studio.AnimeStudioShortDto;
import itis.animediary.service.mappers.studio.AnimeStudioListMapper;
import itis.animediary.service.mappers.studio.AnimeStudioMapper;
import itis.animediary.service.mappers.studio.AnimeStudioShortListMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnimeStudioService {

    private final AnimeStudioRepository animeStudioRepository;

    private final AnimeStudioMapper animeStudioMapper;
    private final AnimeStudioListMapper animeStudioListMapper;
    private final AnimeStudioShortListMapper animeStudioShortListMapper;

    public Optional<AnimeStudioDto> getStudioById(long id) {
        return animeStudioRepository.findById(id).map(animeStudioMapper::toDTO);
    }

    public List<AnimeStudioDto> getAllStudios() {
        List<AnimeStudioEntity> studios = animeStudioRepository.findAll();
        return animeStudioListMapper.toDtoList(studios);
    }

    public List<AnimeStudioShortDto> getAllStudiosShortDto() {
        List<AnimeStudioEntity> studios = animeStudioRepository.findAll();
        return animeStudioShortListMapper.toDtoList(studios);
    }
}
