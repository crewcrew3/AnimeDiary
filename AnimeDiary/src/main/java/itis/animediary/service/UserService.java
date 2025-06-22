package itis.animediary.service;

import itis.animediary.data.entity.AuthorityEntity;
import itis.animediary.data.repository.AuthorityRepository;
import itis.animediary.service.dto.UserDto;
import itis.animediary.data.entity.UserEntity;
import itis.animediary.service.exceptions.FileException;
import itis.animediary.service.mappers.user.UserListMapper;
import itis.animediary.data.repository.UserRepository;
import itis.animediary.service.mappers.user.UserMapper;
import itis.animediary.service.requestdto.ProfileForm;
import itis.animediary.service.exceptions.DuplicateEmailException;
import itis.animediary.utils.properties.ExceptionMessages;
import itis.animediary.utils.properties.OtherProperties;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;

    private final UserListMapper userListMapper;
    private final UserMapper userMapper;

    private final SessionRegistry sessionRegistry;
    private final PasswordEncoder passwordEncoder;

    public Optional<UserDto> getUserByEmail(String email) {
        return userRepository.findByEmail(email).map(userMapper::toDTO);
    }

    public List<UserDto> getAllUsers() {
        List<UserEntity> users = userRepository.findAll();
        return userListMapper.toDtoList(users);
    }

    public boolean changeMemberBlockStatus(long userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(ExceptionMessages.USER_NOT_FOUND_ERROR));
        if (user.getIsNonLocked()) {
            sessionRegistry.getAllPrincipals().stream()
                    .filter(principal -> ((UserDetails) principal).getUsername().equals(user.getEmail()))
                    //находим все активные сессии по principal, который соответсвует заблоканому юзеру и завершаем их
                    //principal - объект, который идентифицирует субъекта, прошедшего аутентификацию
                    .flatMap(principal -> sessionRegistry.getAllSessions(principal, false).stream())
                    .forEach(SessionInformation::expireNow);
        }
        user.setIsNonLocked(!user.getIsNonLocked());
        userRepository.save(user);
        return user.getIsNonLocked();
    }

    public void changeMemberRole(long userId, String role, Boolean isAssignRole) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(ExceptionMessages.USER_NOT_FOUND_ERROR));
        AuthorityEntity authority = authorityRepository.findByName(role)
                .orElseThrow(() -> new EntityNotFoundException(ExceptionMessages.AUTHORITY_NOT_FOUND_ERROR));
        if (isAssignRole) {
            user.addAuthority(authority);
        } else {
            user.getAuthorities().remove(authority);
        }
        userRepository.save(user);
    }

    public void updateUserProfile(long userId, ProfileForm profileForm) {
        Optional<UserEntity> foundUser = userRepository.findByEmail(profileForm.getEmail());
        if (foundUser.isPresent() && foundUser.get().getId() != userId) {
            throw DuplicateEmailException.forProfileEdit();
        }
        
        UserEntity user;
        user = foundUser.orElseGet(() -> userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(ExceptionMessages.USER_NOT_FOUND_ERROR)));

        String filename;
        MultipartFile profilePhoto = profileForm.getProfilePhoto();
        if (profilePhoto != null && !profilePhoto.isEmpty()) {
            filename = System.nanoTime() + profilePhoto.getOriginalFilename();
            File fileToSave = new File(OtherProperties.IMAGE_ROOT_PATH + filename);
            try {
                profilePhoto.transferTo(fileToSave);
            } catch (IOException e) {
                throw FileException.forProfileEdit();
            }
        } else {
            filename = "default_avatar.jpg";
        }
        
        user.setNickname(profileForm.getNickname());
        user.setEmail(profileForm.getEmail());
        user.setPassword(passwordEncoder.encode(profileForm.getPassword()));
        user.setAge(profileForm.getAge());
        user.setPhotoURL(filename);

        userRepository.save(user);
    }
}

