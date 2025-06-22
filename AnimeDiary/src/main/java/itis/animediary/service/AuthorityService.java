package itis.animediary.service;

import itis.animediary.data.repository.AuthorityRepository;
import itis.animediary.service.dto.AuthorityDto;
import itis.animediary.service.mappers.AuthorityMapper;
import lombok.*;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorityService {

    private final AuthorityRepository authorityRepository;

    private final AuthorityMapper authorityMapper;

    public Optional<AuthorityDto> findByName(String name) {
        return authorityRepository.findByName(name).map(authorityMapper::toDTO);
    }
}
